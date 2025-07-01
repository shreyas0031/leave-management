package com.LeaveManagement.model;

import com.LeaveManagement.enums.LeaveStatus;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "leave_request")
public class Leave {
    @Id
    private UUID id = UUID.randomUUID();

    private String reason;
    private Date startDate;
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status = LeaveStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
