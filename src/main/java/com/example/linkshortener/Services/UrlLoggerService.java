package com.example.linkshortener.Services;

import com.example.linkshortener.Model.Url;
import com.example.linkshortener.Model.UrlData;
import org.springframework.stereotype.Service;

@Service
public interface UrlLoggerService {
    public void recordRedirectRequest(Long url_id, Long user_id);
}
