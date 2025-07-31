package com.farm404.samyang.mapper.dto;

import com.farm404.samyang.dto.request.CropCreateRequest;
import com.farm404.samyang.dto.request.CropUpdateRequest;
import com.farm404.samyang.dto.response.CropResponse;
import com.farm404.samyang.entity.Crop;

import java.util.List;
import java.util.stream.Collectors;

public class CropDtoMapper {
    
    // Entity → Response DTO 변환
    public static CropResponse toResponse(Crop crop) {
        if (crop == null) {
            return null;
        }
        
        return CropResponse.builder()
                .cropID(crop.getCropID())
                .cropName(crop.getCropName())
                .scientificName(crop.getScientificName())
                .category(crop.getCategory())
                .description(crop.getDescription())
                .createdAt(crop.getCreatedAt())
                .updatedAt(crop.getUpdatedAt())
                .build();
    }
    
    // Entity List → Response DTO List 변환
    public static List<CropResponse> toResponseList(List<Crop> crops) {
        return crops.stream()
                .map(CropDtoMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    // Create Request → Entity 변환
    public static Crop toEntity(CropCreateRequest request) {
        if (request == null) {
            return null;
        }
        
        return Crop.builder()
                .cropName(request.getCropName())
                .scientificName(request.getScientificName())
                .category(request.getCategory())
                .description(request.getDescription())
                .build();
    }
    
    // Update Request → Entity 변환
    public static Crop toEntity(CropUpdateRequest request) {
        if (request == null) {
            return null;
        }
        
        return Crop.builder()
                .cropName(request.getCropName())
                .scientificName(request.getScientificName())
                .category(request.getCategory())
                .description(request.getDescription())
                .build();
    }
}