package com.example.linkshortener.Model;

import java.time.LocalDateTime;

public class UrlData {
    private String originalUrl;
    private int expiryHours; //Optional
    private long user_id;

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

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
