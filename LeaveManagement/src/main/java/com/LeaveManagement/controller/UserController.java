package com.LeaveManagement.controller;

import com.LeaveManagement.requestDto.UserRequestDto;
import com.LeaveManagement.responseDto.UserResponseDto;
import com.LeaveManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponseDto createUser(@RequestBody UserRequestDto dto) {
        return userService.createUser(dto);
    }

    @PostMapping("/login")
    public UserResponseDto login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        return userService.login(email, password);
    }

    @GetMapping("/getAll")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getById/{id}")
    public UserResponseDto getById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public UserResponseDto update(@PathVariable UUID id, @RequestBody UserRequestDto dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }
}
