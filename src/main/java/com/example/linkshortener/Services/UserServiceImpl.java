package com.example.linkshortener.Services;

import com.example.linkshortener.Model.Authorities;
import com.example.linkshortener.Model.Url;
import com.example.linkshortener.Model.User;
import com.example.linkshortener.Repositories.AuthorityRepository;
import com.example.linkshortener.Repositories.UrlRepository;
import com.example.linkshortener.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public User loadByUsername(String username) {
        User user = userRepository.findOneByEmailAndEnabledTrue(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Override
    public void saveUserToDatabase(User user) {
        userRepository.save(user);
        authorityRepository.save(new Authorities("SITE_USER",user));
    }

    @Override
    public List<Url> loadUrls(Long user_id) {
        return urlRepository.findAllByUserid(user_id);
    }
}
