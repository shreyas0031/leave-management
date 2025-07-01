package com.LeaveManagement.controller;

import com.LeaveManagement.requestDto.DepartmentRequestDto;
import com.LeaveManagement.responseDto.DepartmentResponseDto;
import com.LeaveManagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/addDepartment")
    public DepartmentResponseDto createDepartment(@RequestBody DepartmentRequestDto dto) {
        return departmentService.createDepartment(dto);
    }

    @GetMapping("/getAll")
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/getById/{id}")
    public DepartmentResponseDto getById(@PathVariable UUID id) {
        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/update/{id}")
    public DepartmentResponseDto update(@PathVariable UUID id, @RequestBody DepartmentRequestDto dto) {
        return departmentService.updateDepartment(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        return departmentService.deleteDepartment(id);
    }

    @GetMapping("/getAllWithUsers")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartmentsWithUsers() {
        List<DepartmentResponseDto> list = departmentService.getAllDepartmentsWithUsers();
        return ResponseEntity.ok(list);
    }

}

