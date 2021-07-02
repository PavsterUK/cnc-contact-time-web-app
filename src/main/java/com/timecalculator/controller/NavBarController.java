package com.timecalculator.controller;

import org.springframework.stereotype.Controller;
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

    @RequestMapping("/about")
    public String about() {
        return "calculator";
    }
}
