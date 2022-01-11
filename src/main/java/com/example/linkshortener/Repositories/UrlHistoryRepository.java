package com.example.linkshortener.Repositories;

import com.example.linkshortener.Model.UrlHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlHistoryRepository extends JpaRepository<UrlHistory,Long> {
}
