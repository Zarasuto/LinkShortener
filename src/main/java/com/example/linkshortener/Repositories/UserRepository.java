package com.example.linkshortener.Repositories;

import com.example.linkshortener.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findOneByUsernameAndEnabledTrue(String username);
}
