package com.example.linkshortener.Services;

import com.example.linkshortener.Model.Url;
import com.example.linkshortener.Model.UrlData;
import com.example.linkshortener.Repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlShorteningServiceImpl implements UrlShorteningService{

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public Url generateShortLink(UrlData url) {
        if(!url.getOriginalUrl().isEmpty()){
            Url urlToPersist = new Url();
            urlToPersist.setShortenedURL(this.encodeUrl());
            urlToPersist.setOriginalUrl(url.getOriginalUrl());
            urlToPersist.setCreationDate(url.getCreationTime());
            urlToPersist.setExpirationDate(url.getExpiryTime());
            Url urlInRep = persistShortLink(urlToPersist);
            if(urlInRep!=null){
                return urlInRep;
            }
            return null;
        }
        return null;
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

    }
}
