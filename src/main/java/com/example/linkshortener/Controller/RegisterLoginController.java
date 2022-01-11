package com.example.linkshortener.Controller;

import com.example.linkshortener.Model.User;
import com.example.linkshortener.Security.CurrentAuthenticated;
import com.example.linkshortener.Security.PasswordConfig;
import com.example.linkshortener.Services.UserServiceImpl;
import com.example.linkshortener.Validators.PasswordMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
public class RegisterLoginController {
    @Autowired
    private CurrentAuthenticated currentAuthenticated;

    @Autowired
    private PasswordMatch passwordMatch;

    @Autowired
    private PasswordConfig PasswordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public String login() {
        if(currentAuthenticated.getAuthentication()==null){
            return "login";
        }
        return "index";
    }

    @GetMapping("/register")
    public String getForm(Model model) {
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs){
        passwordMatch.validate(user,bindingResult);
        if(bindingResult.hasErrors()){
            return "register";
        }
        model.addAttribute("user",user);
        user.setPassword(PasswordEncoder.passwordEncoder().encode(user.getPlainpassword()));
        user.setPlainpassword("");
        user.setRepeatpassword("");
        userService.saveUserToDatabase(user);
        return "login";
    }

    @InitBinder    /* Converts empty strings into null when a form is submitted */
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
