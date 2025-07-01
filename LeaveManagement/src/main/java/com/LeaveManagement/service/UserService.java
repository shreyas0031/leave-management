package com.LeaveManagement.service;

import com.LeaveManagement.requestDto.UserRequestDto;
import com.LeaveManagement.responseDto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);
    UserResponseDto login(String email, String password);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(UUID id);
    UserResponseDto updateUser(UUID id, UserRequestDto dto);
    String deleteUser(UUID id);
}
