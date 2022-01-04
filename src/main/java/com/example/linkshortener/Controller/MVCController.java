package com.example.linkshortener.Controller;


import com.example.linkshortener.Model.Url;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MVCController {

    @GetMapping("/")
    public String getForm(Model model){
        model.addAttribute("Url", new Url());
        return "index";
    }
}
