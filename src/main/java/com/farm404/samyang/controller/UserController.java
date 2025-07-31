package com.farm404.samyang.controller;

import com.farm404.samyang.dto.request.UserCreateRequest;
import com.farm404.samyang.dto.request.UserUpdateRequest;
import com.farm404.samyang.dto.response.UserResponse;
import com.farm404.samyang.entity.User;
import com.farm404.samyang.mapper.dto.UserDtoMapper;
import com.farm404.samyang.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;
    
    // 생성자 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    // 모든 사용자 조회
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(UserDtoMapper.toResponseList(userService.findAll()));
    }
    
    // ID로 사용자 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UserDtoMapper.toResponse(user));
    }
    
    // 사용자 생성
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        User user = UserDtoMapper.toEntity(request);
        User createdUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDtoMapper.toResponse(createdUser));
    }
    
    // 사용자 수정
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UserUpdateRequest request) {
        User user = UserDtoMapper.toEntity(request);
        User updatedUser = userService.update(id, user);
        return ResponseEntity.ok(UserDtoMapper.toResponse(updatedUser));
    }
    
    // 사용자 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}