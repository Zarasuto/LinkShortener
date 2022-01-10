package com.example.linkshortener.Validators;

import com.example.linkshortener.Model.User;
import com.example.linkshortener.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordMatch implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(user.getRepeatpassword()!=null){
            if(!user.getPlainpassword().equals(user.getRepeatpassword())){
                errors.rejectValue("repeatpassword","","Password Mismatch");
            }
        }
        if(user.getEmail()!=null){
            if(userRepository.countByEmail(user.getEmail())!=0){
                errors.rejectValue("email","","There is an account already connected to this email");
            }
        }
    }
}
