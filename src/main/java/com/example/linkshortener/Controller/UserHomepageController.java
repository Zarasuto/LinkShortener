package com.example.linkshortener.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserHomepageController {

    @GetMapping("/user/home")
    public String redirectToHomepage(Model model){
        return "/user/home";
    }
}
