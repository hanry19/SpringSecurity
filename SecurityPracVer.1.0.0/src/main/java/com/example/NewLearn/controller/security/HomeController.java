package com.example.NewLearn.controller.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/main")
    public String home() {

        return "main/home";
    }

    @RequestMapping("admin/test")
    public String adminTest() {
        return "main/adminPage";
    }

    @RequestMapping("user/test")
    public String userTest() {
        return "main/UserPge";
    }

    @RequestMapping("/manager")
    public String managerTest() {
        return "main/MamagerPage";
    }
    @RequestMapping("/all")
    public String allTest() {
        return "main/all";
    }
}
