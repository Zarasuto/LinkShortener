package com.example.linkshortener.Repositories;

import com.example.linkshortener.Model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authorities,Long> {
}
