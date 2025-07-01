package com.LeaveManagement.responseDto;

import com.LeaveManagement.enums.LeaveStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class LeaveResponseDto {
    private UUID id;
    private String reason;
    private Date startDate;
    private Date endDate;
    private LeaveStatus status;
    private String userName;
    private String department;
}
