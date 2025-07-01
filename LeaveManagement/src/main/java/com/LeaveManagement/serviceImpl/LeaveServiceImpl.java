package com.LeaveManagement.serviceImpl;

import com.LeaveManagement.enums.LeaveStatus;
import com.LeaveManagement.model.Leave;
import com.LeaveManagement.model.User;
import com.LeaveManagement.repository.LeaveRepository;
import com.LeaveManagement.repository.UserRepository;
import com.LeaveManagement.requestDto.LeaveRequestDto;
import com.LeaveManagement.responseDto.LeaveResponseDto;
import com.LeaveManagement.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public LeaveResponseDto applyLeave(LeaveRequestDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Leave leave = new Leave(UUID.randomUUID(), dto.getReason(), dto.getStartDate(), dto.getEndDate(), LeaveStatus.PENDING, user);
        leave = leaveRepository.save(leave);
        return mapToResponse(leave);
    }

    @Override
    public List<LeaveResponseDto> getLeavesByUser(UUID userId) {
        return leaveRepository.findByUserId(userId).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public List<LeaveResponseDto> getAllLeaves() {
        return leaveRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public LeaveResponseDto updateLeaveStatus(UUID leaveId, String status) {
        Leave leave = leaveRepository.findById(leaveId).orElseThrow();
        leave.setStatus(LeaveStatus.valueOf(status.toUpperCase()));
        leave = leaveRepository.save(leave);
        return mapToResponse(leave);
    }

    @Override
    public LeaveResponseDto getLeaveById(UUID leaveId) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        return mapToResponse(leave);
    }

    @Override
    public LeaveResponseDto updateLeave(UUID leaveId, LeaveRequestDto dto) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setReason(dto.getReason());
        leave.setUser(user);

        return mapToResponse(leaveRepository.save(leave));
    }

    @Override
    public String deleteLeave(UUID leaveId) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        leaveRepository.delete(leave);
        return "Leave deleted successfully";
    }

    private LeaveResponseDto mapToResponse(Leave leave) {
        return LeaveResponseDto.builder()
                .id(leave.getId())
                .reason(leave.getReason())
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .status(leave.getStatus())
                .userName(leave.getUser().getName())
                .department(leave.getUser().getDepartment() != null
                        ? leave.getUser().getDepartment().getName()
                        : "N/A")
                .build();
    }

}

