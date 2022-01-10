package com.example.linkshortener.Controller;


import com.example.linkshortener.Model.Url;
import com.example.linkshortener.Model.UrlData;
import com.example.linkshortener.Model.User;
import com.example.linkshortener.Repositories.UrlRepository;
import com.example.linkshortener.Services.UrlShorteningServiceImpl;
import com.example.linkshortener.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UrlShorteningController {
    @Autowired
    private UrlShorteningServiceImpl urlShorteningService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value={"/","/generate"})
    public String getForm(Model model){
        model.addAttribute("urlData", new UrlData());
        return "index";
    }

    @PostMapping("/generate")
    public String generateShortlink(@Valid @ModelAttribute UrlData url, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "index";
        }
        url.setUser_id(1);
        model.addAttribute("urlData",url);
        Url urlRep = urlShorteningService.generateShortLink(url);
        model.addAttribute("Url",urlRep);
        return "index";
    }
    @InitBinder    /* Converts empty strings into null when a form is submitted */
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Scheduled(fixedRate = 3600000) //Hours
    public void scheduledTest(){
        urlShorteningService.checkAndDeleteExpiredLinks();
    }
}
