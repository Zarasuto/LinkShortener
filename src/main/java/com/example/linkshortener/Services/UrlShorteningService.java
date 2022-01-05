package com.example.linkshortener.Services;

import com.example.linkshortener.Model.Url;
import com.example.linkshortener.Model.UrlData;
import org.springframework.stereotype.Service;

@Service
public interface UrlShorteningService {
    public Url generateShortLink(UrlData url);
    public Url getShortenedUrl(String shortenedurl);
    public Url persistShortLink(Url url);
    public void deleteShortLink(Url url);
}
