package com.farm404.samyang.controller;

import com.farm404.samyang.dto.request.FarmingDiaryCreateRequest;
import com.farm404.samyang.dto.request.FarmingDiaryUpdateRequest;
import com.farm404.samyang.dto.response.FarmingDiaryResponse;
import com.farm404.samyang.entity.Crop;
import com.farm404.samyang.entity.FarmingDiary;
import com.farm404.samyang.entity.User;
import com.farm404.samyang.service.CropService;
import com.farm404.samyang.service.FarmingDiaryService;
import com.farm404.samyang.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/farming-diaries")
public class FarmingDiaryController {
    
    private final FarmingDiaryService farmingDiaryService;
    private final UserService userService;
    private final CropService cropService;
    
    // 생성자 주입
    public FarmingDiaryController(FarmingDiaryService farmingDiaryService,
                                  UserService userService,
                                  CropService cropService) {
        this.farmingDiaryService = farmingDiaryService;
        this.userService = userService;
        this.cropService = cropService;
    }
    
    // 모든 일지 조회
    @GetMapping
    public ResponseEntity<List<FarmingDiaryResponse>> getAllDiaries(
            @RequestParam(required = false) Integer userID,
            @RequestParam(required = false) Integer cropID) {
        
        List<FarmingDiary> diaries;
        if (userID != null) {
            diaries = farmingDiaryService.findByUserID(userID);
        } else if (cropID != null) {
            diaries = farmingDiaryService.findByCropID(cropID);
        } else {
            diaries = farmingDiaryService.findAll();
        }
        
        List<FarmingDiaryResponse> responses = new ArrayList<>();
        for (FarmingDiary diary : diaries) {
            FarmingDiaryResponse response = convertToResponse(diary);
            responses.add(response);
        }
        
        return ResponseEntity.ok(responses);
    }
    
    // ID로 일지 조회
    @GetMapping("/{id}")
    public ResponseEntity<FarmingDiaryResponse> getDiaryById(@PathVariable Integer id) {
        FarmingDiary diary = farmingDiaryService.findById(id);
        if (diary == null) {
            return ResponseEntity.notFound().build();
        }
        
        FarmingDiaryResponse response = convertToResponse(diary);
        return ResponseEntity.ok(response);
    }
    
    // 일지 생성
    @PostMapping
    public ResponseEntity<FarmingDiaryResponse> createDiary(@Valid @RequestBody FarmingDiaryCreateRequest request) {
        FarmingDiary diary = new FarmingDiary();
        diary.setUserID(request.getUserID());
        diary.setCropID(request.getCropID());
        diary.setTitle(request.getTitle());
        diary.setContent(request.getContent());
        diary.setWeatherCondition(request.getWeatherCondition());
        diary.setTemperature(request.getTemperature());
        diary.setHumidity(request.getHumidity());
        diary.setWorkDate(request.getWorkDate());
        
        FarmingDiary createdDiary = farmingDiaryService.create(diary);
        FarmingDiaryResponse response = convertToResponse(createdDiary);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    // 일지 수정
    @PutMapping("/{id}")
    public ResponseEntity<FarmingDiaryResponse> updateDiary(
            @PathVariable Integer id,
            @Valid @RequestBody FarmingDiaryUpdateRequest request) {
        
        FarmingDiary diary = new FarmingDiary();
        diary.setUserID(request.getUserID());
        diary.setCropID(request.getCropID());
        diary.setTitle(request.getTitle());
        diary.setContent(request.getContent());
        diary.setWeatherCondition(request.getWeatherCondition());
        diary.setTemperature(request.getTemperature());
        diary.setHumidity(request.getHumidity());
        diary.setWorkDate(request.getWorkDate());
        
        FarmingDiary updatedDiary = farmingDiaryService.update(id, diary);
        FarmingDiaryResponse response = convertToResponse(updatedDiary);
        
        return ResponseEntity.ok(response);
    }
    
    // 일지 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiary(@PathVariable Integer id) {
        farmingDiaryService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    // Entity를 Response로 변환하는 헬퍼 메소드
    private FarmingDiaryResponse convertToResponse(FarmingDiary diary) {
        FarmingDiaryResponse response = new FarmingDiaryResponse();
        response.setDiaryID(diary.getDiaryID());
        response.setUserID(diary.getUserID());
        response.setCropID(diary.getCropID());
        response.setTitle(diary.getTitle());
        response.setContent(diary.getContent());
        response.setWeatherCondition(diary.getWeatherCondition());
        response.setTemperature(diary.getTemperature());
        response.setHumidity(diary.getHumidity());
        response.setWorkDate(diary.getWorkDate());
        response.setCreatedAt(diary.getCreatedAt());
        response.setUpdatedAt(diary.getUpdatedAt());
        
        // 사용자 이름 추가
        User user = userService.findById(diary.getUserID());
        if (user != null) {
            response.setUserName(user.getFullName());
        }
        
        // 작물 이름 추가
        Crop crop = cropService.findById(diary.getCropID());
        if (crop != null) {
            response.setCropName(crop.getCropName());
        }
        
        return response;
    }
}