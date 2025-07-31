package com.farm404.samyang.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class FarmingDiaryCreateRequest {
    @NotNull(message = "User ID is required")
    private Integer userID;
    
    @NotNull(message = "Crop ID is required")
    private Integer cropID;
    
    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    private String title;
    
    @NotBlank(message = "Content is required")
    private String content;
    
    @Size(max = 50, message = "Weather condition must not exceed 50 characters")
    private String weatherCondition;
    
    private BigDecimal temperature;
    
    private BigDecimal humidity;
    
    @NotNull(message = "Work date is required")
    private LocalDate workDate;
}