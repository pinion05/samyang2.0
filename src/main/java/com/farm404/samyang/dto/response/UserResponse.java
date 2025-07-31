package com.farm404.samyang.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Integer userID;
    private String username;
    private String email;
    private String fullName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}