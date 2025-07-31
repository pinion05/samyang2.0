package com.farm404.samyang.controller.mvc;

import com.farm404.samyang.entity.Review;
import com.farm404.samyang.entity.User;
import com.farm404.samyang.entity.Crop;
import com.farm404.samyang.service.ReviewService;
import com.farm404.samyang.service.UserService;
import com.farm404.samyang.service.CropService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewMvcController {
    
    private final ReviewService reviewService;
    private final UserService userService;
    private final CropService cropService;
    
    // 생성자 주입
    public ReviewMvcController(ReviewService reviewService, 
                             UserService userService, 
                             CropService cropService) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.cropService = cropService;
    }
    
    // 리뷰 목록 조회
    @GetMapping
    public String list(Model model) {
        List<Review> reviews = reviewService.findAll();
        
        // 각 리뷰에 사용자명과 작물명 추가
        for (Review review : reviews) {
            User user = userService.findById(review.getUserID());
            if (user != null) {
                review.setUserName(user.getFullName());
            }
            
            Crop crop = cropService.findById(review.getCropID());
            if (crop != null) {
                review.setCropName(crop.getCropName());
            }
        }
        
        model.addAttribute("reviews", reviews);
        return "review/list";
    }
    
    // 리뷰 상세 조회
    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Review review = reviewService.findById(id);
        if (review == null) {
            return "redirect:/reviews";
        }
        
        // 사용자명과 작물명 추가
        User user = userService.findById(review.getUserID());
        if (user != null) {
            review.setUserName(user.getFullName());
        }
        
        Crop crop = cropService.findById(review.getCropID());
        if (crop != null) {
            review.setCropName(crop.getCropName());
        }
        
        model.addAttribute("review", review);
        return "review/detail";
    }
    
    // 리뷰 생성 폼
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("crops", cropService.findAll());
        return "review/form";
    }
    
    // 리뷰 수정 폼
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        Review review = reviewService.findById(id);
        if (review == null) {
            return "redirect:/reviews";
        }
        model.addAttribute("review", review);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("crops", cropService.findAll());
        model.addAttribute("isEdit", true);
        return "review/form";
    }
    
    // 리뷰 생성 처리
    @PostMapping
    public String create(@ModelAttribute Review review, RedirectAttributes redirectAttributes) {
        try {
            reviewService.create(review);
            redirectAttributes.addFlashAttribute("message", "리뷰가 등록되었습니다.");
            return "redirect:/reviews";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "리뷰 등록에 실패했습니다: " + e.getMessage());
            return "redirect:/reviews/new";
        }
    }
    
    // 리뷰 수정 처리
    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Review review, RedirectAttributes redirectAttributes) {
        try {
            reviewService.update(id, review);
            redirectAttributes.addFlashAttribute("message", "리뷰가 수정되었습니다.");
            return "redirect:/reviews/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "리뷰 수정에 실패했습니다: " + e.getMessage());
            return "redirect:/reviews/" + id + "/edit";
        }
    }
    
    // 리뷰 삭제 처리
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            reviewService.delete(id);
            redirectAttributes.addFlashAttribute("message", "리뷰가 삭제되었습니다.");
            return "redirect:/reviews";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "리뷰 삭제에 실패했습니다: " + e.getMessage());
            return "redirect:/reviews";
        }
    }
}