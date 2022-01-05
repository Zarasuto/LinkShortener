package com.example.linkshortener.Model;

import java.time.LocalDateTime;

public class UrlData {
    private String originalUrl;
    private LocalDateTime creationTime=LocalDateTime.now();
    private LocalDateTime expiryTime=LocalDateTime.now().plusWeeks(1);

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
}
