package com.farm404.samyang.controller.mvc;

import com.farm404.samyang.entity.Crop;
import com.farm404.samyang.service.CropService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/crops")
public class CropMvcController {
    
    private final CropService cropService;
    
    // 생성자 주입
    public CropMvcController(CropService cropService) {
        this.cropService = cropService;
    }
    
    // 작물 목록 조회
    @GetMapping
    public String list(Model model) {
        List<Crop> crops = cropService.findAll();
        model.addAttribute("crops", crops);
        return "crop/list";
    }
    
    // 작물 상세 조회
    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Crop crop = cropService.findById(id);
        if (crop == null) {
            return "redirect:/crops";
        }
        model.addAttribute("crop", crop);
        return "crop/detail";
    }
    
    // 작물 생성 폼
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("crop", new Crop());
        return "crop/form";
    }
    
    // 작물 수정 폼
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        Crop crop = cropService.findById(id);
        if (crop == null) {
            return "redirect:/crops";
        }
        model.addAttribute("crop", crop);
        model.addAttribute("isEdit", true);
        return "crop/form";
    }
    
    // 작물 생성 처리
    @PostMapping
    public String create(@ModelAttribute Crop crop, RedirectAttributes redirectAttributes) {
        try {
            cropService.create(crop);
            redirectAttributes.addFlashAttribute("message", "작물이 등록되었습니다.");
            return "redirect:/crops";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "작물 등록에 실패했습니다: " + e.getMessage());
            return "redirect:/crops/new";
        }
    }
    
    // 작물 수정 처리
    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Crop crop, RedirectAttributes redirectAttributes) {
        try {
            cropService.update(id, crop);
            redirectAttributes.addFlashAttribute("message", "작물이 수정되었습니다.");
            return "redirect:/crops/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "작물 수정에 실패했습니다: " + e.getMessage());
            return "redirect:/crops/" + id + "/edit";
        }
    }
    
    // 작물 삭제 처리
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            cropService.delete(id);
            redirectAttributes.addFlashAttribute("message", "작물이 삭제되었습니다.");
            return "redirect:/crops";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "작물 삭제에 실패했습니다: " + e.getMessage());
            return "redirect:/crops";
        }
    }
}