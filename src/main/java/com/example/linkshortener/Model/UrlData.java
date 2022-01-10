package com.example.linkshortener.Model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UrlData {

    @NotNull
    @Pattern(regexp = "\\S*\\.\\S*", message="Must be a valid URL")
    private String originalUrl;
    private int expiryHours; //Optional
    private long user_id;

    public UrlData(){
        setExpiryHours(1);
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public int getExpiryHours() {
        return expiryHours;
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
