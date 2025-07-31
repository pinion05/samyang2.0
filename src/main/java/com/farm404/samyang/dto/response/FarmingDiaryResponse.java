package com.farm404.samyang.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmingDiaryResponse {
    private Integer diaryID;
    private Integer userID;
    private Integer cropID;
    private String title;
    private String content;
    private String weatherCondition;
    private BigDecimal temperature;
    private BigDecimal humidity;
    private LocalDate workDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // 관련 정보
    private String userName;
    private String cropName;
}