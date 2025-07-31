package com.farm404.samyang.controller.mvc;

import com.farm404.samyang.entity.Report;
import com.farm404.samyang.entity.User;
import com.farm404.samyang.service.ReportService;
import com.farm404.samyang.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/reports")
public class ReportMvcController {
    
    private final ReportService reportService;
    private final UserService userService;
    
    // 생성자 주입
    public ReportMvcController(ReportService reportService, UserService userService) {
        this.reportService = reportService;
        this.userService = userService;
    }
    
    // 신고 목록 조회
    @GetMapping
    public String list(Model model) {
        List<Report> reports = reportService.findAll();
        
        // 각 신고에 신고자명 추가
        for (Report report : reports) {
            User reporter = userService.findById(report.getReporterID());
            if (reporter != null) {
                report.setReporterName(reporter.getFullName());
            }
        }
        
        model.addAttribute("reports", reports);
        return "report/list";
    }
    
    // 신고 상세 조회
    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Report report = reportService.findById(id);
        if (report == null) {
            return "redirect:/reports";
        }
        
        // 신고자명 추가
        User reporter = userService.findById(report.getReporterID());
        if (reporter != null) {
            report.setReporterName(reporter.getFullName());
        }
        
        model.addAttribute("report", report);
        return "report/detail";
    }
    
    // 신고 생성 폼
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("report", new Report());
        model.addAttribute("users", userService.findAll());
        return "report/form";
    }
    
    // 신고 수정 폼
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        Report report = reportService.findById(id);
        if (report == null) {
            return "redirect:/reports";
        }
        model.addAttribute("report", report);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("isEdit", true);
        return "report/form";
    }
    
    // 신고 생성 처리
    @PostMapping
    public String create(@ModelAttribute Report report, RedirectAttributes redirectAttributes) {
        try {
            // 기본 상태 설정
            if (report.getStatus() == null || report.getStatus().isEmpty()) {
                report.setStatus("pending");
            }
            reportService.create(report);
            redirectAttributes.addFlashAttribute("message", "신고가 접수되었습니다.");
            return "redirect:/reports";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "신고 접수에 실패했습니다: " + e.getMessage());
            return "redirect:/reports/new";
        }
    }
    
    // 신고 수정 처리
    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Report report, RedirectAttributes redirectAttributes) {
        try {
            reportService.update(id, report);
            redirectAttributes.addFlashAttribute("message", "신고가 수정되었습니다.");
            return "redirect:/reports/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "신고 수정에 실패했습니다: " + e.getMessage());
            return "redirect:/reports/" + id + "/edit";
        }
    }
    
    // 신고 삭제 처리
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            reportService.delete(id);
            redirectAttributes.addFlashAttribute("message", "신고가 삭제되었습니다.");
            return "redirect:/reports";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "신고 삭제에 실패했습니다: " + e.getMessage());
            return "redirect:/reports";
        }
    }
}