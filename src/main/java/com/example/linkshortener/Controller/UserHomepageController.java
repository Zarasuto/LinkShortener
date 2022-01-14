package com.example.linkshortener.Controller;

import com.example.linkshortener.Model.Url;
import com.example.linkshortener.Repositories.UrlHistoryRepository;
import com.example.linkshortener.Repositories.UrlRepository;
import com.example.linkshortener.Security.CurrentAuthenticated;
import com.example.linkshortener.Services.UserServiceImpl;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class UserHomepageController {

    private final DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    @Autowired
    private CurrentAuthenticated currentAuthenticated;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UrlHistoryRepository urlHistoryRepository;

    @Autowired
    private UrlRepository urlRepository;

    @GetMapping("/user/home")
    public String redirectToHomepage(Model model){

        if(currentAuthenticated==null){
            return "redirect:/";
        }
        List<Url> urlList =  userService.loadUrls(currentAuthenticated.getUserDetails().getId());
        model.addAttribute("urls",urlList);
        model.addAttribute("urlDta",new Url());
        model.addAttribute("Timeformat",dTF);
        model.addAttribute("UrlTotalClicks",0);
        model.addAttribute("TotalClicks",urlHistoryRepository.countAllByUserid(currentAuthenticated.getUserDetails().getId()));
        return "/user/home";
    }
    @GetMapping("/user/home/{shortenedLink}")
    public String populateUrlData(@PathVariable String shortenedLink, Model model){
        if(currentAuthenticated==null){
            return "redirect:/";
        }
        Url url = urlRepository.findByshortenedURL(shortenedLink);
        model.addAttribute("UrlTotalClicks",0);
        model.addAttribute("TotalClicks",urlHistoryRepository.countAllByUserid(currentAuthenticated.getUserDetails().getId()));
        if(url!=null){
            model.addAttribute("UrlTotalClicks",urlHistoryRepository.countByUrlid(url.getId()));
            model.addAttribute("urlDta",url);
        }
        List<Url> urlList =  userService.loadUrls(currentAuthenticated.getUserDetails().getId());
        model.addAttribute("urls",urlList);
        model.addAttribute("Timeformat",dTF);
        return "/user/home";
    }
}
