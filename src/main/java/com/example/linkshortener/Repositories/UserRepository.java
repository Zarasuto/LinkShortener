package com.example.linkshortener.Repositories;

import com.example.linkshortener.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findOneByEmailAndEnabledTrue(String username);
}
