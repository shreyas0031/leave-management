package com.LeaveManagement.responseDto;

import com.LeaveManagement.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResponseDto {
    private UUID id;
    private String name;
    private String email;
    private String mobile;
    private String department;
    private Role role;
}
