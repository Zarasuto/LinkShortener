package com.example.linkshortener.Services;

import com.example.linkshortener.Model.Url;
import com.example.linkshortener.Model.UrlData;
import com.example.linkshortener.Repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UrlShorteningServiceImpl implements UrlShorteningService{

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public Url generateShortLink(UrlData url) {
        if(!url.getOriginalUrl().isEmpty()){
            Url urlToPersist = new Url();
            urlToPersist.setShortenedURL(this.encodeUrl(url.getOriginalUrl()));
            urlToPersist.setOriginalUrl(validateAndFixUrl(url));
            urlToPersist.setUserid(url.getUser_id());
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

    private String encodeUrl(String input) {
        String sampleString = "aA1bB2cC3dD4eE5fF6gG7hH8iI9jJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
        char[] sample = sampleString.toCharArray();
        Integer sampleSize = sampleString.length();
        char[] chars = input.toCharArray();
        Integer charsLen = chars.length;
        int wholeNo = Math.floorDiv(charsLen , 7);
        Integer remainder = charsLen - wholeNo * 7;
        char[] shortened = new char[7];
        Integer i,j;
        Integer x = 0;
        for (i = 0; i <= 6; i++) {
            int temp = 0,temp1 = 0;
            for (j = 1; j <= wholeNo; j++) {
                if(remainder !=0) {
                    temp += chars[x];
                    temp += chars[x];
                    x++;
                    remainder -= 1;
                }

                temp += chars[x];
                x++;
            }
            temp1 = Math.floorDiv(temp, sampleSize);
            temp -= temp1 * sampleSize;
            shortened[i] += sample[Math.abs((i*LocalDateTime.now().getNano())%61)];
        }
        String str= new String(shortened);
        return(str);
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

    @Override
    public void checkAndDeleteExpiredLinks(){
        List<Url> urlList = urlRepository.findALlExpiredGuestLinks();
        if(urlList!=null){
            for(Url url:urlList){
                deleteShortLink(url);
            }
        }
    }
}
