package com.example.linkshortener.Repositories;

import com.example.linkshortener.Model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UrlRepository extends JpaRepository<Url,Long> {

    public Url findByshortenedURL(String shortenedURL);

    @Query(value="SELECT * FROM url WHERE expiration_date < NOW()",
            nativeQuery = true)
    public List<Url> findALlExpiredLinks();
}
