package com.LeaveManagement.requestDto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class LeaveRequestDto {
    private String reason;
    private Date startDate;
    private Date endDate;
    private UUID userId;
}
