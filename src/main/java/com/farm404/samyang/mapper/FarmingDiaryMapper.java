package com.farm404.samyang.mapper;

import com.farm404.samyang.entity.FarmingDiary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FarmingDiaryMapper {
    
    // 일지 하나 찾기
    FarmingDiary selectById(@Param("DiaryID") Integer diaryID);
    
    // 모든 일지 찾기
    List<FarmingDiary> selectAll();
    
    // 특정 사용자의 일지 찾기
    List<FarmingDiary> selectByUserID(@Param("UserID") Integer userID);
    
    // 특정 작물의 일지 찾기
    List<FarmingDiary> selectByCropID(@Param("CropID") Integer cropID);
    
    // 일지 추가
    int insert(FarmingDiary farmingDiary);
    
    // 일지 수정
    int update(FarmingDiary farmingDiary);
    
    // 일지 삭제
    int delete(@Param("DiaryID") Integer diaryID);
}