package com.farm404.samyang.service;

import com.farm404.samyang.entity.Review;
import com.farm404.samyang.mapper.ReviewMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewService {
    
    private final ReviewMapper reviewMapper;
    
    // 생성자 주입
    public ReviewService(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }
    
    // ID로 리뷰 찾기
    public Review findById(Integer reviewID) {
        if (reviewID == null) {
            return null;
        }
        return reviewMapper.selectById(reviewID);
    }
    
    // 모든 리뷰 조회
    public List<Review> findAll() {
        return reviewMapper.selectAll();
    }
    
    // 특정 사용자의 리뷰 찾기
    public List<Review> findByUserID(Integer userID) {
        if (userID == null) {
            return reviewMapper.selectAll();
        }
        return reviewMapper.selectByUserID(userID);
    }
    
    // 특정 작물의 리뷰 찾기
    public List<Review> findByCropID(Integer cropID) {
        if (cropID == null) {
            return reviewMapper.selectAll();
        }
        return reviewMapper.selectByCropID(cropID);
    }
    
    // 리뷰 생성
    public Review create(Review review) {
        if (review == null) {
            throw new IllegalArgumentException("리뷰 정보가 없습니다.");
        }
        
        // 필수 정보 확인
        if (review.getUserID() == null) {
            throw new IllegalArgumentException("사용자 ID가 없습니다.");
        }
        if (review.getCropID() == null) {
            throw new IllegalArgumentException("작물 ID가 없습니다.");
        }
        if (review.getRating() == null) {
            throw new IllegalArgumentException("평점이 없습니다.");
        }
        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("평점은 1부터 5사이여야 합니다.");
        }
        if (review.getTitle() == null || review.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("제목이 없습니다.");
        }
        if (review.getContent() == null || review.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("내용이 없습니다.");
        }
        
        // 저장
        reviewMapper.insert(review);
        return review;
    }
    
    // 리뷰 수정
    public Review update(Integer reviewID, Review review) {
        if (reviewID == null || review == null) {
            throw new IllegalArgumentException("수정할 정보가 없습니다.");
        }
        
        // 존재 확인
        Review existingReview = reviewMapper.selectById(reviewID);
        if (existingReview == null) {
            throw new IllegalArgumentException("리뷰를 찾을 수 없습니다.");
        }
        
        // ID 설정
        review.setReviewID(reviewID);
        
        // 수정
        reviewMapper.update(review);
        return review;
    }
    
    // 리뷰 삭제
    public void delete(Integer reviewID) {
        if (reviewID == null) {
            throw new IllegalArgumentException("삭제할 리뷰 ID가 없습니다.");
        }
        
        // 존재 확인
        Review existingReview = reviewMapper.selectById(reviewID);
        if (existingReview == null) {
            throw new IllegalArgumentException("리뷰를 찾을 수 없습니다.");
        }
        
        // 삭제
        reviewMapper.delete(reviewID);
    }
}