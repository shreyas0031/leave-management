package com.LeaveManagement.service;

import com.LeaveManagement.requestDto.DepartmentRequestDto;
import com.LeaveManagement.responseDto.DepartmentResponseDto;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    DepartmentResponseDto createDepartment(DepartmentRequestDto dto);
    List<DepartmentResponseDto> getAllDepartments();
    DepartmentResponseDto getDepartmentById(UUID id);
    DepartmentResponseDto updateDepartment(UUID id, DepartmentRequestDto dto);
    String deleteDepartment(UUID id);
    public List<DepartmentResponseDto> getAllDepartmentsWithUsers();
}
