package com.LeaveManagement.serviceImpl;

import com.LeaveManagement.model.Department;
import com.LeaveManagement.model.User;
import com.LeaveManagement.repository.DepartmentRepository;
import com.LeaveManagement.repository.UserRepository;
import com.LeaveManagement.requestDto.DepartmentRequestDto;
import com.LeaveManagement.responseDto.DepartmentResponseDto;
import com.LeaveManagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto dto) {
        Department department = new Department(UUID.randomUUID(), dto.getName(), null);
        return mapToResponse(departmentRepository.save(department));
    }

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponseDto getDepartmentById(UUID id) {
        Department department = departmentRepository.findById(id).orElseThrow();
        return mapToResponse(department);
    }

    @Override
    public DepartmentResponseDto updateDepartment(UUID id, DepartmentRequestDto dto) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
        department.setName(dto.getName());
        return mapToResponse(departmentRepository.save(department));
    }

    @Override
    public String deleteDepartment(UUID id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
        departmentRepository.delete(department);
        return "Department deleted successfully";
    }

    public List<DepartmentResponseDto> getAllDepartmentsWithUsers() {
        List<Department> departments = departmentRepository.findAll();
        List<User> allUsers = userRepository.findAll();

        // Group users by department ID
        Map<UUID, List<DepartmentResponseDto.UserInfo>> departmentUserMap = allUsers.stream()
                .filter(user -> user.getDepartment() != null)
                .collect(Collectors.groupingBy(
                        user -> user.getDepartment().getId(),
                        Collectors.mapping(user -> new DepartmentResponseDto.UserInfo(
                                user.getName(),
                                user.getEmail(),
                                user.getMobile()
                        ), Collectors.toList())
                ));

        // Map to DepartmentResponseDto
        return departments.stream()
                .map(dept -> DepartmentResponseDto.builder()
                        .id(dept.getId())
                        .name(dept.getName())
                        .users(departmentUserMap.getOrDefault(dept.getId(), List.of()))
                        .build())
                .collect(Collectors.toList());
    }

    private DepartmentResponseDto mapToResponse(Department department) {
        return DepartmentResponseDto.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }
}

