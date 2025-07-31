package com.farm404.samyang.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateRequest {
    @NotNull(message = "Post ID is required")
    private Integer postID;
    
    @NotNull(message = "User ID is required")
    private Integer userID;
    
    @NotBlank(message = "Content is required")
    private String content;
}