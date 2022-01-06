package com.example.linkshortener.Services;

import com.example.linkshortener.Model.Url;
import com.example.linkshortener.Model.UrlData;
import com.example.linkshortener.Repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UrlShorteningServiceImpl implements UrlShorteningService{

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public Url generateShortLink(UrlData url) {
        if(!url.getOriginalUrl().isEmpty()){
            Url urlToPersist = new Url();
            urlToPersist.setShortenedURL(this.encodeUrl());
            urlToPersist.setOriginalUrl(validateAndFixUrl(url));
            urlToPersist.setCreationDate(LocalDateTime.now());
            urlToPersist.setExpirationDate(setExpiryDate(url.getExpiryHours()));
            Url urlInRep = persistShortLink(urlToPersist);
            if(urlInRep!=null){
                return urlInRep;
            }
            return null;
        }
        return null;
    }

    private LocalDateTime setExpiryDate(int hours){
        return LocalDateTime.now().plusHours(hours);
    }

    private String encodeUrl() {
        //Placeholder
        return "asdf";
    }

    @Override
    public Url getShortenedUrl(String shortenedurl) {
        return urlRepository.findByshortenedURL(shortenedurl);
    }

    @Override
    public Url persistShortLink(Url url) {
        Url urlToRep = urlRepository.save(url);
        return urlToRep;
    }

    @Override
    public void deleteShortLink(Url url) {
        urlRepository.delete(url);
    }

    @Override
    public String validateAndFixUrl(UrlData url) {
        if(url.getOriginalUrl().startsWith("http://")||url.getOriginalUrl().startsWith("https://")){
            return url.getOriginalUrl();
        }else{
            return "http://"+url.getOriginalUrl();
        }
    }
}
