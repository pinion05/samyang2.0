package com.farm404.samyang.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateRequest {
    
    @NotNull(message = "사용자 ID는 필수입니다.")
    private Integer userID;
    
    @NotNull(message = "작물 ID는 필수입니다.")
    private Integer cropID;
    
    @NotNull(message = "평점은 필수입니다.")
    @Min(value = 1, message = "평점은 1 이상이어야 합니다.")
    @Max(value = 5, message = "평점은 5 이하여야 합니다.")
    private Integer rating;
    
    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 200, message = "제목은 200자 이하여야 합니다.")
    private String title;
    
    @NotBlank(message = "내용은 필수입니다.")
    private String content;
}