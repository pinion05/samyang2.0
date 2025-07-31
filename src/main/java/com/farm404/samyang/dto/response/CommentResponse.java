package com.farm404.samyang.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Integer commentID;
    private Integer postID;
    private Integer userID;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // 관련 정보
    private String userName;
}