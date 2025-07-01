package com.LeaveManagement.service;

import com.LeaveManagement.requestDto.LeaveRequestDto;
import com.LeaveManagement.responseDto.LeaveResponseDto;

import java.util.List;
import java.util.UUID;

public interface LeaveService {
    LeaveResponseDto applyLeave(LeaveRequestDto dto);
    List<LeaveResponseDto> getLeavesByUser(UUID userId);
    List<LeaveResponseDto> getAllLeaves();
    LeaveResponseDto getLeaveById(UUID leaveId);
    LeaveResponseDto updateLeave(UUID leaveId, LeaveRequestDto dto);
    String deleteLeave(UUID leaveId);
    LeaveResponseDto updateLeaveStatus(UUID leaveId, String status);
}
