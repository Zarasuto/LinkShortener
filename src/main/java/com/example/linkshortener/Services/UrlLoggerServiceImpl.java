package com.example.linkshortener.Services;

import com.example.linkshortener.Model.UrlHistory;
import com.example.linkshortener.Repositories.UrlHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UrlLoggerServiceImpl implements UrlLoggerService{

    @Autowired
    private UrlHistoryRepository urlHistoryRepository;

    @Override
    public void recordRedirectRequest(Long url_id, Long user_id) {
        urlHistoryRepository.save(new UrlHistory(url_id,user_id, LocalDateTime.now()));
    }
}
