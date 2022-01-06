package com.example.linkshortener.Model;

import org.springframework.stereotype.Component;

public class UrlErrorResponse {
    private String error;
    private String code;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "UrlErrorResponse{" +
                "error='" + error + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
