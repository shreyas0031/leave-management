package com.LeaveManagement.serviceImpl;

import com.LeaveManagement.enums.Role;
import com.LeaveManagement.model.Department;
import com.LeaveManagement.model.User;
import com.LeaveManagement.repository.DepartmentRepository;
import com.LeaveManagement.repository.UserRepository;
import com.LeaveManagement.requestDto.UserRequestDto;
import com.LeaveManagement.responseDto.UserResponseDto;
import com.LeaveManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto dto) {
        Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow();
        User user = new User(
                UUID.randomUUID(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getMobile(),
                Role.USER, // default role
                department,
                new ArrayList<>()
        );

        user = userRepository.save(user);
        return mapToResponse(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponseDto login(String email, String password) {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        return mapToResponse(user);
    }

    @Override
    public UserResponseDto getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return mapToResponse(user);
    }

    @Override
    public UserResponseDto updateUser(UUID id, UserRequestDto dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setMobile(dto.getMobile());
        user.setDepartment(departmentRepository.findById(dto.getDepartmentId()).orElseThrow());
        return mapToResponse(userRepository.save(user));
    }

    @Override
    public String deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return "User deleted successfully";
    }

    private UserResponseDto mapToResponse(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .department(user.getDepartment().getName())
                .role(user.getRole())
                .build();
    }

}
