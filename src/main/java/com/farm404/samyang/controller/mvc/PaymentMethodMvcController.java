package com.farm404.samyang.controller.mvc;

import com.farm404.samyang.entity.PaymentMethod;
import com.farm404.samyang.entity.User;
import com.farm404.samyang.service.PaymentMethodService;
import com.farm404.samyang.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/payment-methods")
public class PaymentMethodMvcController {
    
    private final PaymentMethodService paymentMethodService;
    private final UserService userService;
    
    // 생성자 주입
    public PaymentMethodMvcController(PaymentMethodService paymentMethodService, UserService userService) {
        this.paymentMethodService = paymentMethodService;
        this.userService = userService;
    }
    
    // 결제수단 목록 조회
    @GetMapping
    public String list(Model model) {
        List<PaymentMethod> paymentMethods = paymentMethodService.findAll();
        
        // 각 결제수단에 사용자명 추가
        for (PaymentMethod paymentMethod : paymentMethods) {
            User user = userService.findById(paymentMethod.getUserID());
            if (user != null) {
                paymentMethod.setUserName(user.getFullName());
            }
        }
        
        model.addAttribute("paymentMethods", paymentMethods);
        return "payment-method/list";
    }
    
    // 결제수단 상세 조회
    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        PaymentMethod paymentMethod = paymentMethodService.findById(id);
        if (paymentMethod == null) {
            return "redirect:/payment-methods";
        }
        
        // 사용자명 추가
        User user = userService.findById(paymentMethod.getUserID());
        if (user != null) {
            paymentMethod.setUserName(user.getFullName());
        }
        
        model.addAttribute("paymentMethod", paymentMethod);
        return "payment-method/detail";
    }
    
    // 결제수단 생성 폼
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("paymentMethod", new PaymentMethod());
        model.addAttribute("users", userService.findAll());
        return "payment-method/form";
    }
    
    // 결제수단 수정 폼
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        PaymentMethod paymentMethod = paymentMethodService.findById(id);
        if (paymentMethod == null) {
            return "redirect:/payment-methods";
        }
        model.addAttribute("paymentMethod", paymentMethod);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("isEdit", true);
        return "payment-method/form";
    }
    
    // 결제수단 생성 처리
    @PostMapping
    public String create(@ModelAttribute PaymentMethod paymentMethod, RedirectAttributes redirectAttributes) {
        try {
            paymentMethodService.create(paymentMethod);
            redirectAttributes.addFlashAttribute("message", "결제수단이 등록되었습니다.");
            return "redirect:/payment-methods";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "결제수단 등록에 실패했습니다: " + e.getMessage());
            return "redirect:/payment-methods/new";
        }
    }
    
    // 결제수단 수정 처리
    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute PaymentMethod paymentMethod, RedirectAttributes redirectAttributes) {
        try {
            paymentMethodService.update(id, paymentMethod);
            redirectAttributes.addFlashAttribute("message", "결제수단이 수정되었습니다.");
            return "redirect:/payment-methods/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "결제수단 수정에 실패했습니다: " + e.getMessage());
            return "redirect:/payment-methods/" + id + "/edit";
        }
    }
    
    // 결제수단 삭제 처리
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            paymentMethodService.delete(id);
            redirectAttributes.addFlashAttribute("message", "결제수단이 삭제되었습니다.");
            return "redirect:/payment-methods";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "결제수단 삭제에 실패했습니다: " + e.getMessage());
            return "redirect:/payment-methods";
        }
    }
}