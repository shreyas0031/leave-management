package com.LeaveManagement.controller;

import com.LeaveManagement.requestDto.LeaveRequestDto;
import com.LeaveManagement.responseDto.LeaveResponseDto;
import com.LeaveManagement.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/applyLeave")
    public LeaveResponseDto applyLeave(@RequestBody LeaveRequestDto dto) {
        return leaveService.applyLeave(dto);
    }

    @GetMapping("/getAll")
    public List<LeaveResponseDto> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @GetMapping("/user/{userId}")
    public List<LeaveResponseDto> getUserLeaves(@PathVariable UUID userId) {
        return leaveService.getLeavesByUser(userId);
    }

    @GetMapping("/getById/{leaveId}")
    public LeaveResponseDto getLeaveById(@PathVariable UUID leaveId) {
        return leaveService.getLeaveById(leaveId);
    }

    @PutMapping("/update/{leaveId}")
    public LeaveResponseDto updateLeave(@PathVariable UUID leaveId, @RequestBody LeaveRequestDto dto) {
        return leaveService.updateLeave(leaveId, dto);
    }

    @DeleteMapping("/delete/{leaveId}")
    public String deleteLeave(@PathVariable UUID leaveId) {
        return leaveService.deleteLeave(leaveId);
    }

    @PatchMapping("/{leaveId}/status")
    public LeaveResponseDto updateStatus(@PathVariable UUID leaveId, @RequestParam String status) {
        return leaveService.updateLeaveStatus(leaveId, status);
    }
}
