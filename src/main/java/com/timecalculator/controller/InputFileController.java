package com.timecalculator.controller;

import com.timecalculator.domain.InputFileManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/InputFileController")
@Controller
public class InputFileController {

    @GetMapping("/chooseFile")
    public void openChooseFileDialog() {

    }

}
