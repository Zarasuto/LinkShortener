package com.example.linkshortener.Model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "url")
@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "shortened_url")
    private String shortenedURL;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "user_id")
    private Long userid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long user_id) {
        this.userid = user_id;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getShortenedURL() {
        return shortenedURL;
    }

    public void setShortenedURL(String shortenedURL) {
        this.shortenedURL = shortenedURL;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Long getId() {
        return id;
    }

}