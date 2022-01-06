package com.example.linkshortener.Controller;


import com.example.linkshortener.Model.UrlData;
import com.example.linkshortener.Services.UrlShorteningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MVCController {

    @Autowired
    private UrlShorteningServiceImpl urlShorteningService;

    @GetMapping(value={"/","/generate"})
    public String getForm(Model model){
        model.addAttribute("UrlData", new UrlData());
        return "index";
    }

    @PostMapping("/generate")
    public String generateShortlink(@ModelAttribute UrlData url, Model model){
        model.addAttribute("UrlData",url);
        return "index";
    }
}
