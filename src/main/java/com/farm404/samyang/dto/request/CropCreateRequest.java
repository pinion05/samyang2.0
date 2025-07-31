package com.farm404.samyang.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CropCreateRequest {
    @NotBlank(message = "Crop name is required")
    @Size(max = 100, message = "Crop name must not exceed 100 characters")
    private String cropName;
    
    @Size(max = 200, message = "Scientific name must not exceed 200 characters")
    private String scientificName;
    
    @Size(max = 50, message = "Category must not exceed 50 characters")
    private String category;
    
    private String description;
}