package com.example.linkshortener.Services;

import com.example.linkshortener.Model.Url;
import com.example.linkshortener.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User loadByUsername(String username);
    public void saveUserToDatabase(User user);
    public List<Url> loadUrls(Long user_id);
}
