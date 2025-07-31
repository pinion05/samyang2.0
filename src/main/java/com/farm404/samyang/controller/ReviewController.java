package com.farm404.samyang.controller;

import com.farm404.samyang.dto.request.ReviewCreateRequest;
import com.farm404.samyang.dto.response.ReviewResponse;
import com.farm404.samyang.entity.Crop;
import com.farm404.samyang.entity.Review;
import com.farm404.samyang.entity.User;
import com.farm404.samyang.service.CropService;
import com.farm404.samyang.service.ReviewService;
import com.farm404.samyang.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    
    private final ReviewService reviewService;
    private final UserService userService;
    private final CropService cropService;
    
    // 생성자 주입
    public ReviewController(ReviewService reviewService, 
                           UserService userService, 
                           CropService cropService) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.cropService = cropService;
    }
    
    // 모든 리뷰 조회
    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAllReviews(
            @RequestParam(required = false) Integer userID,
            @RequestParam(required = false) Integer cropID) {
        
        List<Review> reviews;
        if (userID != null) {
            reviews = reviewService.findByUserID(userID);
        } else if (cropID != null) {
            reviews = reviewService.findByCropID(cropID);
        } else {
            reviews = reviewService.findAll();
        }
        
        List<ReviewResponse> responses = new ArrayList<>();
        for (Review review : reviews) {
            ReviewResponse response = convertToResponse(review);
            responses.add(response);
        }
        
        return ResponseEntity.ok(responses);
    }
    
    // ID로 리뷰 조회
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable Integer id) {
        Review review = reviewService.findById(id);
        if (review == null) {
            return ResponseEntity.notFound().build();
        }
        
        ReviewResponse response = convertToResponse(review);
        return ResponseEntity.ok(response);
    }
    
    // 리뷰 생성
    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@Valid @RequestBody ReviewCreateRequest request) {
        Review review = new Review();
        review.setUserID(request.getUserID());
        review.setCropID(request.getCropID());
        review.setRating(request.getRating());
        review.setTitle(request.getTitle());
        review.setContent(request.getContent());
        
        Review createdReview = reviewService.create(review);
        ReviewResponse response = convertToResponse(createdReview);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    // 리뷰 수정
    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable Integer id,
            @Valid @RequestBody ReviewCreateRequest request) {
        
        Review review = new Review();
        review.setUserID(request.getUserID());
        review.setCropID(request.getCropID());
        review.setRating(request.getRating());
        review.setTitle(request.getTitle());
        review.setContent(request.getContent());
        
        Review updatedReview = reviewService.update(id, review);
        ReviewResponse response = convertToResponse(updatedReview);
        
        return ResponseEntity.ok(response);
    }
    
    // 리뷰 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    // Entity를 Response로 변환하는 헬퍼 메소드
    private ReviewResponse convertToResponse(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.setReviewID(review.getReviewID());
        response.setUserID(review.getUserID());
        response.setCropID(review.getCropID());
        response.setRating(review.getRating());
        response.setTitle(review.getTitle());
        response.setContent(review.getContent());
        response.setCreatedAt(review.getCreatedAt());
        response.setUpdatedAt(review.getUpdatedAt());
        
        // 사용자 이름 추가
        User user = userService.findById(review.getUserID());
        if (user != null) {
            response.setUserName(user.getFullName());
        }
        
        // 작물 이름 추가
        Crop crop = cropService.findById(review.getCropID());
        if (crop != null) {
            response.setCropName(crop.getCropName());
        }
        
        return response;
    }
}