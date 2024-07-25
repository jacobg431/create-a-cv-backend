package com.cvbackend.springboot.maven;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class FrontendController {
    private static final Logger logger = LoggerFactory.getLogger(FrontendController.class);

    @GetMapping("/")
    public String index(Model model) {
        logger.info("Index endpoint hit");
        return "index";
    }
}