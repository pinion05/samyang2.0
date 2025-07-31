package com.farm404.samyang.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    // 홈 페이지
    @GetMapping("/")
    public String home() {
        return "home";
    }
}