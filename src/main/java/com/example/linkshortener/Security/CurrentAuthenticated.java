package com.example.linkshortener.Security;

import com.example.linkshortener.Model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentAuthenticated {

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getLoggedInName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getUserDetails(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
