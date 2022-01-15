package com.example.linkshortener.Controller;


import com.example.linkshortener.Model.Url;
import com.example.linkshortener.Model.UrlData;
import com.example.linkshortener.Security.CurrentAuthenticated;
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

@Controller
public class UrlShorteningController {

    @Autowired
    private CurrentAuthenticated currentAuthenticated;

    @Autowired
    private UrlShorteningServiceImpl urlShorteningService;


    @GetMapping(value={"/","/generate"})
    public String getForm(Model model){
        model.addAttribute("urlData", new UrlData());
        return "index";
    }

    @PostMapping("/generate")
    public String generateShortlink(@Valid @ModelAttribute UrlData url, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs){
        //Checks If there is any validation errors
        if(bindingResult.hasErrors()){
            return "index";
        }
        //Checks if the user is either logged in or not. if yes, add a url based on that user
        if(currentAuthenticated.getAuthentication()==null){
            url.setUser_id(0);
        }else{
            url.setUser_id(currentAuthenticated.getUserDetails().getId());
        }
        switch(url.getScope()){
            case "day":
                url.setExpiryHours(url.getExpiryHours()*24);
                break;
            case "week":
                url.setExpiryHours(url.getExpiryHours()*168);
                break;
            case "month":
                url.setExpiryHours(url.getExpiryHours()*672);
                break;
        }
        Url urlRep = urlShorteningService.generateShortLink(url);
        model.addAttribute("Url",urlRep);
        return "index";
    }
    @InitBinder    /* Converts empty strings into null when a form is submitted */
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    //Cleans the database every hour for expired guest links
    @Scheduled(fixedRate = 3600000) //Hours
    public void scheduledTest(){
        urlShorteningService.checkAndDeleteExpiredLinks();
    }
}
