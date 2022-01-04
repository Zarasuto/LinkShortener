package com.example.linkshortener.Repositories;

import com.example.linkshortener.Model.Url;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UrlRepository extends JpaRepository<Url,Long> {

    public Url findByshortenedURL(String shortenedURL);

}
