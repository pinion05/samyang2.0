package com.farm404.samyang.mapper;

import com.farm404.samyang.entity.Crop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CropMapper {
    
    // 작물 하나 찾기
    Crop selectById(@Param("CropID") Integer cropID);
    
    // 모든 작물 찾기
    List<Crop> selectAll();
    
    // 카테고리로 작물 찾기
    List<Crop> selectByCategory(@Param("Category") String category);
    
    // 작물 추가
    int insert(Crop crop);
    
    // 작물 수정
    int update(Crop crop);
    
    // 작물 삭제
    int delete(@Param("CropID") Integer cropID);
}