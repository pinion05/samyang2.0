package com.farm404.samyang.dto.common;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    @Min(1)
    @Builder.Default
    private int page = 1;
    
    @Min(1)
    @Max(100)
    @Builder.Default
    private int size = 10;
    
    @Builder.Default
    private String sortBy = "CreatedAt";
    
    @Builder.Default
    private String sortDirection = "DESC";
    
    public int getOffset() {
        return (page - 1) * size;
    }
}