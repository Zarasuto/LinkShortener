package com.example.linkshortener.Repositories;

import com.example.linkshortener.Model.UrlHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlHistoryRepository extends JpaRepository<UrlHistory,Long> {
    public List<UrlHistory> findByUrlid(long url_id);
    public int countByUrlid(long url_id);
}
