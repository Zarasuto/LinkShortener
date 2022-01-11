package com.example.linkshortener.Controller;

import com.example.linkshortener.Model.Url;
import com.example.linkshortener.Model.UrlErrorResponse;
import com.example.linkshortener.Security.CurrentAuthenticated;
import com.example.linkshortener.Services.UrlLoggerServiceImpl;
import com.example.linkshortener.Services.UrlShorteningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class RESTEndpoints {

    @Autowired
    private UrlLoggerServiceImpl urlLoggerService;

    @Autowired
    private UrlShorteningServiceImpl urlShorteningService;

    @GetMapping("/{shortLink}")
    public ResponseEntity<UrlErrorResponse> redirectShortLink(@PathVariable String shortLink, HttpServletResponse response) throws IOException {
        Url urlToRedirect = urlShorteningService.getShortenedUrl(shortLink);
        if(urlToRedirect==null){
            UrlErrorResponse urlErrorResponse = new UrlErrorResponse();
            urlErrorResponse.setError("Short link does not exist or has expired");
            urlErrorResponse.setCode("400");
            return new ResponseEntity<UrlErrorResponse>(urlErrorResponse, HttpStatus.OK);
        }
        if(urlToRedirect.getExpirationDate().isBefore(LocalDateTime.now())){
            UrlErrorResponse urlErrorResponse = new UrlErrorResponse();
            urlErrorResponse.setError("Short link has expired");
            urlErrorResponse.setCode("200");
            return new ResponseEntity<UrlErrorResponse>(urlErrorResponse, HttpStatus.OK);
        }
        if(urlToRedirect.getUser_id()!=1){
            urlLoggerService.recordRedirectRequest(urlToRedirect.getId(),urlToRedirect.getUser_id());
        }
        response.sendRedirect(urlToRedirect.getOriginalUrl());
        return null;
    }
}
