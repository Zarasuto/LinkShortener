package com.example.linkshortener.Services;

import com.example.linkshortener.Model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User loadByUsername(String username);
    public void saveUserToDatabase(User user);
}
