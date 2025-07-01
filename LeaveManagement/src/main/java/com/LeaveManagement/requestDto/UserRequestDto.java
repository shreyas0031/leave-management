package com.LeaveManagement.requestDto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
    private String mobile;
    private UUID departmentId;
}
