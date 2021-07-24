package com.timecalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavBarController {

    @RequestMapping("/")
    public String getHome() {
        return "index";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/calculator")
    public String about(Model model) {
        model.addAttribute("gCode", "Please add fucking G-code File");
        return "calculator";
    }
}
