package com.farm404.samyang.controller.mvc;

import com.farm404.samyang.entity.FarmingDiary;
import com.farm404.samyang.entity.User;
import com.farm404.samyang.entity.Crop;
import com.farm404.samyang.service.FarmingDiaryService;
import com.farm404.samyang.service.UserService;
import com.farm404.samyang.service.CropService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/diaries")
public class FarmingDiaryMvcController {
    
    private final FarmingDiaryService farmingDiaryService;
    private final UserService userService;
    private final CropService cropService;
    
    // 생성자 주입
    public FarmingDiaryMvcController(FarmingDiaryService farmingDiaryService, 
                                   UserService userService, 
                                   CropService cropService) {
        this.farmingDiaryService = farmingDiaryService;
        this.userService = userService;
        this.cropService = cropService;
    }
    
    // 영농일지 목록 조회
    @GetMapping
    public String list(Model model) {
        List<FarmingDiary> diaries = farmingDiaryService.findAll();
        
        // 각 일지에 사용자명과 작물명 추가
        for (FarmingDiary diary : diaries) {
            User user = userService.findById(diary.getUserID());
            if (user != null) {
                diary.setUserName(user.getFullName());
            }
            
            Crop crop = cropService.findById(diary.getCropID());
            if (crop != null) {
                diary.setCropName(crop.getCropName());
            }
        }
        
        model.addAttribute("diaries", diaries);
        return "diary/list";
    }
    
    // 영농일지 상세 조회
    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        FarmingDiary diary = farmingDiaryService.findById(id);
        if (diary == null) {
            return "redirect:/diaries";
        }
        
        // 사용자명과 작물명 추가
        User user = userService.findById(diary.getUserID());
        if (user != null) {
            diary.setUserName(user.getFullName());
        }
        
        Crop crop = cropService.findById(diary.getCropID());
        if (crop != null) {
            diary.setCropName(crop.getCropName());
        }
        
        model.addAttribute("diary", diary);
        return "diary/detail";
    }
    
    // 영농일지 생성 폼
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("diary", new FarmingDiary());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("crops", cropService.findAll());
        return "diary/form";
    }
    
    // 영농일지 수정 폼
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        FarmingDiary diary = farmingDiaryService.findById(id);
        if (diary == null) {
            return "redirect:/diaries";
        }
        model.addAttribute("diary", diary);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("crops", cropService.findAll());
        model.addAttribute("isEdit", true);
        return "diary/form";
    }
    
    // 영농일지 생성 처리
    @PostMapping
    public String create(@ModelAttribute FarmingDiary diary, RedirectAttributes redirectAttributes) {
        try {
            farmingDiaryService.create(diary);
            redirectAttributes.addFlashAttribute("message", "영농일지가 등록되었습니다.");
            return "redirect:/diaries";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "영농일지 등록에 실패했습니다: " + e.getMessage());
            return "redirect:/diaries/new";
        }
    }
    
    // 영농일지 수정 처리
    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute FarmingDiary diary, RedirectAttributes redirectAttributes) {
        try {
            farmingDiaryService.update(id, diary);
            redirectAttributes.addFlashAttribute("message", "영농일지가 수정되었습니다.");
            return "redirect:/diaries/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "영농일지 수정에 실패했습니다: " + e.getMessage());
            return "redirect:/diaries/" + id + "/edit";
        }
    }
    
    // 영농일지 삭제 처리
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            farmingDiaryService.delete(id);
            redirectAttributes.addFlashAttribute("message", "영농일지가 삭제되었습니다.");
            return "redirect:/diaries";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "영농일지 삭제에 실패했습니다: " + e.getMessage());
            return "redirect:/diaries";
        }
    }
}