package com.farm404.samyang.controller.mvc;

import com.farm404.samyang.entity.User;
import com.farm404.samyang.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserMvcController {
    
    private final UserService userService;
    
    // 생성자 주입
    public UserMvcController(UserService userService) {
        this.userService = userService;
    }
    
    // 사용자 목록 조회
    @GetMapping
    public String list(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }
    
    // 사용자 상세 조회
    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "user/detail";
    }
    
    // 사용자 생성 폼
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        return "user/form";
    }
    
    // 사용자 수정 폼
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        model.addAttribute("isEdit", true);
        return "user/form";
    }
    
    // 사용자 생성 처리
    @PostMapping
    public String create(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.create(user);
            redirectAttributes.addFlashAttribute("message", "사용자가 생성되었습니다.");
            return "redirect:/users";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "사용자 생성에 실패했습니다: " + e.getMessage());
            return "redirect:/users/new";
        }
    }
    
    // 사용자 수정 처리
    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.update(id, user);
            redirectAttributes.addFlashAttribute("message", "사용자가 수정되었습니다.");
            return "redirect:/users/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "사용자 수정에 실패했습니다: " + e.getMessage());
            return "redirect:/users/" + id + "/edit";
        }
    }
    
    // 사용자 삭제 처리
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.delete(id);
            redirectAttributes.addFlashAttribute("message", "사용자가 삭제되었습니다.");
            return "redirect:/users";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "사용자 삭제에 실패했습니다: " + e.getMessage());
            return "redirect:/users";
        }
    }
}