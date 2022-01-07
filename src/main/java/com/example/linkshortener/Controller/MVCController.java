package com.example.linkshortener.Controller;


import com.example.linkshortener.Model.Url;
import com.example.linkshortener.Model.UrlData;
import com.example.linkshortener.Repositories.UrlRepository;
import com.example.linkshortener.Services.UrlShorteningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MVCController {
    @Autowired
    private UrlShorteningServiceImpl urlShorteningService;

    @Autowired
    private UrlRepository urlRepository;

    @GetMapping(value={"/","/generate"})
    public String getForm(Model model){
        model.addAttribute("UrlData", new UrlData());
        model.addAttribute("Url", new Url());
        return "index";
    }

    @PostMapping("/generate")
    public String generateShortlink(@ModelAttribute UrlData url, Model model){
        if(url!=null){
            Url urlRep = urlShorteningService.generateShortLink(url);
            model.addAttribute("Url",urlRep);
        }
        return "index";
    }

    @Scheduled(fixedRate=3600000) // Calls this method every hour
    public void CheckAndDeleteExpiredLinks(){
        List<Url> urlList = urlRepository.findALlExpiredLinks();
        if(urlList!=null){
            for(Url url:urlList){
                urlShorteningService.deleteShortLink(url);
            }
        }
    }
}
