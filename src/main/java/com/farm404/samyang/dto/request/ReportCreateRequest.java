package com.farm404.samyang.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportCreateRequest {
    
    @NotNull(message = "신고자 ID는 필수입니다.")
    private Integer reporterID;
    
    @NotBlank(message = "신고 대상 타입은 필수입니다.")
    @Pattern(regexp = "diary|comment|review", message = "신고 대상 타입은 diary, comment, review 중 하나여야 합니다.")
    private String targetType;
    
    @NotNull(message = "신고 대상 ID는 필수입니다.")
    private Integer targetID;
    
    @NotBlank(message = "신고 사유는 필수입니다.")
    @Size(max = 200, message = "신고 사유는 200자 이하여야 합니다.")
    private String reason;
    
    private String description;
    
    @Pattern(regexp = "pending|resolved|rejected", message = "상태는 pending, resolved, rejected 중 하나여야 합니다.")
    private String status;
}