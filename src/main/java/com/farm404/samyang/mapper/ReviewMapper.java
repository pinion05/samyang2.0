package com.farm404.samyang.mapper;

import com.farm404.samyang.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    
    // 리뷰 하나 찾기
    Review selectById(@Param("ReviewID") Integer reviewID);
    
    // 모든 리뷰 찾기
    List<Review> selectAll();
    
    // 특정 사용자의 리뷰 찾기
    List<Review> selectByUserID(@Param("UserID") Integer userID);
    
    // 특정 작물의 리뷰 찾기
    List<Review> selectByCropID(@Param("CropID") Integer cropID);
    
    // 리뷰 추가
    int insert(Review review);
    
    // 리뷰 수정
    int update(Review review);
    
    // 리뷰 삭제
    int delete(@Param("ReviewID") Integer reviewID);
}