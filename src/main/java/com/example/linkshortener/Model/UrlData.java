package com.example.linkshortener.Model;

import java.time.LocalDateTime;

public class UrlData {
    private String originalUrl;
    private int expiryHours; //Optional

    public UrlData(){
        expiryHours=1;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public int getExpiryHours() {
        return expiryHours;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public void setExpiryHours(int expiryHours) {
        this.expiryHours = expiryHours;
    }
}
