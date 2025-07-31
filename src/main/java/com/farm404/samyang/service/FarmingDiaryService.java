package com.farm404.samyang.service;

import com.farm404.samyang.entity.FarmingDiary;
import com.farm404.samyang.mapper.FarmingDiaryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FarmingDiaryService {
    
    private final FarmingDiaryMapper farmingDiaryMapper;
    
    // 생성자 주입
    public FarmingDiaryService(FarmingDiaryMapper farmingDiaryMapper) {
        this.farmingDiaryMapper = farmingDiaryMapper;
    }
    
    // ID로 일지 찾기
    public FarmingDiary findById(Integer diaryID) {
        if (diaryID == null) {
            return null;
        }
        return farmingDiaryMapper.selectById(diaryID);
    }
    
    // 모든 일지 조회
    public List<FarmingDiary> findAll() {
        return farmingDiaryMapper.selectAll();
    }
    
    // 특정 사용자의 일지 찾기
    public List<FarmingDiary> findByUserID(Integer userID) {
        if (userID == null) {
            return farmingDiaryMapper.selectAll();
        }
        return farmingDiaryMapper.selectByUserID(userID);
    }
    
    // 특정 작물의 일지 찾기
    public List<FarmingDiary> findByCropID(Integer cropID) {
        if (cropID == null) {
            return farmingDiaryMapper.selectAll();
        }
        return farmingDiaryMapper.selectByCropID(cropID);
    }
    
    // 일지 생성
    public FarmingDiary create(FarmingDiary farmingDiary) {
        if (farmingDiary == null) {
            throw new IllegalArgumentException("일지 정보가 없습니다.");
        }
        
        // 필수 정보 확인
        if (farmingDiary.getUserID() == null) {
            throw new IllegalArgumentException("사용자 ID가 없습니다.");
        }
        if (farmingDiary.getCropID() == null) {
            throw new IllegalArgumentException("작물 ID가 없습니다.");
        }
        if (farmingDiary.getTitle() == null || farmingDiary.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("제목이 없습니다.");
        }
        if (farmingDiary.getContent() == null || farmingDiary.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("내용이 없습니다.");
        }
        if (farmingDiary.getWorkDate() == null) {
            throw new IllegalArgumentException("작업일자가 없습니다.");
        }
        
        // 저장
        farmingDiaryMapper.insert(farmingDiary);
        return farmingDiary;
    }
    
    // 일지 수정
    public FarmingDiary update(Integer diaryID, FarmingDiary farmingDiary) {
        if (diaryID == null || farmingDiary == null) {
            throw new IllegalArgumentException("수정할 정보가 없습니다.");
        }
        
        // 존재 확인
        FarmingDiary existingDiary = farmingDiaryMapper.selectById(diaryID);
        if (existingDiary == null) {
            throw new IllegalArgumentException("일지를 찾을 수 없습니다.");
        }
        
        // ID 설정
        farmingDiary.setDiaryID(diaryID);
        
        // 수정
        farmingDiaryMapper.update(farmingDiary);
        return farmingDiary;
    }
    
    // 일지 삭제
    public void delete(Integer diaryID) {
        if (diaryID == null) {
            throw new IllegalArgumentException("삭제할 일지 ID가 없습니다.");
        }
        
        // 존재 확인
        FarmingDiary existingDiary = farmingDiaryMapper.selectById(diaryID);
        if (existingDiary == null) {
            throw new IllegalArgumentException("일지를 찾을 수 없습니다.");
        }
        
        // 삭제
        farmingDiaryMapper.delete(diaryID);
    }
}