package com.farm404.samyang.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmingDiaryUpdateRequest {
    private Integer cropID;
    
    @Size(max = 200, message = "Title must not exceed 200 characters")
    private String title;
    
    private String content;
    
    @Size(max = 50, message = "Weather condition must not exceed 50 characters")
    private String weatherCondition;
    
    private BigDecimal temperature;
    
    private BigDecimal humidity;
    
    private LocalDate workDate;
}