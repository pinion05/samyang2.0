package com.farm404.samyang.mapper.dto;

import com.farm404.samyang.dto.request.UserCreateRequest;
import com.farm404.samyang.dto.request.UserUpdateRequest;
import com.farm404.samyang.dto.response.UserResponse;
import com.farm404.samyang.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDtoMapper {
    
    // Entity → Response DTO 변환
    public static UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }
        
        return UserResponse.builder()
                .userID(user.getUserID())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
    
    // Entity List → Response DTO List 변환
    public static List<UserResponse> toResponseList(List<User> users) {
        return users.stream()
                .map(UserDtoMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    // Create Request → Entity 변환
    public static User toEntity(UserCreateRequest request) {
        if (request == null) {
            return null;
        }
        
        return User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .build();
    }
    
    // Update Request → Entity 변환
    public static User toEntity(UserUpdateRequest request) {
        if (request == null) {
            return null;
        }
        
        return User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .build();
    }
}