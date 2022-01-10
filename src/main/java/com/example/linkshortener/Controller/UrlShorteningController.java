package com.example.linkshortener.Controller;


import com.example.linkshortener.Model.Url;
import com.example.linkshortener.Model.UrlData;
import com.example.linkshortener.Model.User;
import com.example.linkshortener.Repositories.UrlRepository;
import com.example.linkshortener.Services.UrlShorteningServiceImpl;
import com.example.linkshortener.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UrlShorteningController {
    @Autowired
    private UrlShorteningServiceImpl urlShorteningService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value={"/","/generate"})
    public String getForm(Model model){
        UrlData data = new UrlData();
        model.addAttribute("UrlData", new UrlData());
        model.addAttribute("Url", new Url());
        return "index";
    }

    @PostMapping("/generate")
    public String generateShortlink(@ModelAttribute UrlData url, Model model){
        if(url!=null){
            url.setUser_id(1);
            Url urlRep = urlShorteningService.generateShortLink(url);
            model.addAttribute("Url",urlRep);
        }
        return "index";
    }

    @Scheduled(fixedRate = 3600000) //Hours
    public void scheduledTest(){
        urlShorteningService.checkAndDeleteExpiredLinks();
    }
}
