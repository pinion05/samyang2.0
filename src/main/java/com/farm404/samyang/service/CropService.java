package com.farm404.samyang.service;

import com.farm404.samyang.entity.Crop;
import com.farm404.samyang.mapper.CropMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CropService {
    
    private final CropMapper cropMapper;
    
    // 생성자 주입
    public CropService(CropMapper cropMapper) {
        this.cropMapper = cropMapper;
    }
    
    // ID로 작물 찾기
    public Crop findById(Integer cropID) {
        if (cropID == null) {
            return null;
        }
        return cropMapper.selectById(cropID);
    }
    
    // 모든 작물 조회
    public List<Crop> findAll() {
        return cropMapper.selectAll();
    }
    
    // 카테고리로 작물 찾기
    public List<Crop> findByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            return cropMapper.selectAll();
        }
        return cropMapper.selectByCategory(category);
    }
    
    // 작물 생성
    public Crop create(Crop crop) {
        if (crop == null) {
            throw new IllegalArgumentException("작물 정보가 없습니다.");
        }
        
        // 필수 정보 확인
        if (crop.getCropName() == null || crop.getCropName().trim().isEmpty()) {
            throw new IllegalArgumentException("작물명이 없습니다.");
        }
        
        // 저장
        cropMapper.insert(crop);
        return crop;
    }
    
    // 작물 수정
    public Crop update(Integer cropID, Crop crop) {
        if (cropID == null || crop == null) {
            throw new IllegalArgumentException("수정할 정보가 없습니다.");
        }
        
        // 존재 확인
        Crop existingCrop = cropMapper.selectById(cropID);
        if (existingCrop == null) {
            throw new IllegalArgumentException("작물을 찾을 수 없습니다.");
        }
        
        // ID 설정
        crop.setCropID(cropID);
        
        // 수정
        cropMapper.update(crop);
        return crop;
    }
    
    // 작물 삭제
    public void delete(Integer cropID) {
        if (cropID == null) {
            throw new IllegalArgumentException("삭제할 작물 ID가 없습니다.");
        }
        
        // 존재 확인
        Crop existingCrop = cropMapper.selectById(cropID);
        if (existingCrop == null) {
            throw new IllegalArgumentException("작물을 찾을 수 없습니다.");
        }
        
        // 삭제
        cropMapper.delete(cropID);
    }
}