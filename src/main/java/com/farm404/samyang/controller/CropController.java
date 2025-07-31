package com.farm404.samyang.controller;

import com.farm404.samyang.dto.request.CropCreateRequest;
import com.farm404.samyang.dto.request.CropUpdateRequest;
import com.farm404.samyang.dto.response.CropResponse;
import com.farm404.samyang.entity.Crop;
import com.farm404.samyang.service.CropService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/crops")
public class CropController {
    
    private final CropService cropService;
    
    // 생성자 주입
    public CropController(CropService cropService) {
        this.cropService = cropService;
    }
    
    // 모든 작물 조회
    @GetMapping
    public ResponseEntity<List<CropResponse>> getAllCrops(
            @RequestParam(required = false) String category) {
        
        List<Crop> crops;
        if (category != null && !category.isEmpty()) {
            crops = cropService.findByCategory(category);
        } else {
            crops = cropService.findAll();
        }
        
        List<CropResponse> responses = new ArrayList<>();
        for (Crop crop : crops) {
            CropResponse response = new CropResponse();
            response.setCropID(crop.getCropID());
            response.setCropName(crop.getCropName());
            response.setScientificName(crop.getScientificName());
            response.setCategory(crop.getCategory());
            response.setDescription(crop.getDescription());
            response.setCreatedAt(crop.getCreatedAt());
            response.setUpdatedAt(crop.getUpdatedAt());
            responses.add(response);
        }
        
        return ResponseEntity.ok(responses);
    }
    
    // ID로 작물 조회
    @GetMapping("/{id}")
    public ResponseEntity<CropResponse> getCropById(@PathVariable Integer id) {
        Crop crop = cropService.findById(id);
        if (crop == null) {
            return ResponseEntity.notFound().build();
        }
        
        CropResponse response = new CropResponse();
        response.setCropID(crop.getCropID());
        response.setCropName(crop.getCropName());
        response.setScientificName(crop.getScientificName());
        response.setCategory(crop.getCategory());
        response.setDescription(crop.getDescription());
        response.setCreatedAt(crop.getCreatedAt());
        response.setUpdatedAt(crop.getUpdatedAt());
        
        return ResponseEntity.ok(response);
    }
    
    // 작물 생성
    @PostMapping
    public ResponseEntity<CropResponse> createCrop(@Valid @RequestBody CropCreateRequest request) {
        Crop crop = new Crop();
        crop.setCropName(request.getCropName());
        crop.setScientificName(request.getScientificName());
        crop.setCategory(request.getCategory());
        crop.setDescription(request.getDescription());
        
        Crop createdCrop = cropService.create(crop);
        
        CropResponse response = new CropResponse();
        response.setCropID(createdCrop.getCropID());
        response.setCropName(createdCrop.getCropName());
        response.setScientificName(createdCrop.getScientificName());
        response.setCategory(createdCrop.getCategory());
        response.setDescription(createdCrop.getDescription());
        response.setCreatedAt(createdCrop.getCreatedAt());
        response.setUpdatedAt(createdCrop.getUpdatedAt());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    // 작물 수정
    @PutMapping("/{id}")
    public ResponseEntity<CropResponse> updateCrop(
            @PathVariable Integer id,
            @Valid @RequestBody CropUpdateRequest request) {
        
        Crop crop = new Crop();
        crop.setCropName(request.getCropName());
        crop.setScientificName(request.getScientificName());
        crop.setCategory(request.getCategory());
        crop.setDescription(request.getDescription());
        
        Crop updatedCrop = cropService.update(id, crop);
        
        CropResponse response = new CropResponse();
        response.setCropID(updatedCrop.getCropID());
        response.setCropName(updatedCrop.getCropName());
        response.setScientificName(updatedCrop.getScientificName());
        response.setCategory(updatedCrop.getCategory());
        response.setDescription(updatedCrop.getDescription());
        response.setCreatedAt(updatedCrop.getCreatedAt());
        response.setUpdatedAt(updatedCrop.getUpdatedAt());
        
        return ResponseEntity.ok(response);
    }
    
    // 작물 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrop(@PathVariable Integer id) {
        cropService.delete(id);
        return ResponseEntity.noContent().build();
    }
}