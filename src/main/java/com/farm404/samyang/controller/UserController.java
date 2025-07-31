package com.farm404.samyang.controller;

import com.farm404.samyang.dto.request.UserCreateRequest;
import com.farm404.samyang.dto.request.UserUpdateRequest;
import com.farm404.samyang.dto.response.UserResponse;
import com.farm404.samyang.entity.User;
import com.farm404.samyang.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        List<User> users = userService.findAll();
        List<UserResponse> responses = new ArrayList<>();
        
        for (User user : users) {
            UserResponse response = new UserResponse();
            response.setUserID(user.getUserID());
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setFullName(user.getFullName());
            response.setCreatedAt(user.getCreatedAt());
            response.setUpdatedAt(user.getUpdatedAt());
            responses.add(response);
        }
        
        return ResponseEntity.ok(responses);
    }
    
    // ID로 사용자 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        
        UserResponse response = new UserResponse();
        response.setUserID(user.getUserID());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        
        return ResponseEntity.ok(response);
    }
    
    // 사용자 생성
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        
        User createdUser = userService.create(user);
        
        UserResponse response = new UserResponse();
        response.setUserID(createdUser.getUserID());
        response.setUsername(createdUser.getUsername());
        response.setEmail(createdUser.getEmail());
        response.setFullName(createdUser.getFullName());
        response.setCreatedAt(createdUser.getCreatedAt());
        response.setUpdatedAt(createdUser.getUpdatedAt());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    // 사용자 수정
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UserUpdateRequest request) {
        
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        
        User updatedUser = userService.update(id, user);
        
        UserResponse response = new UserResponse();
        response.setUserID(updatedUser.getUserID());
        response.setUsername(updatedUser.getUsername());
        response.setEmail(updatedUser.getEmail());
        response.setFullName(updatedUser.getFullName());
        response.setCreatedAt(updatedUser.getCreatedAt());
        response.setUpdatedAt(updatedUser.getUpdatedAt());
        
        return ResponseEntity.ok(response);
    }
    
    // 사용자 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}