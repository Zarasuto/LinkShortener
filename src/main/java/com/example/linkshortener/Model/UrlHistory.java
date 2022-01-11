package com.example.linkshortener.Model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="urlhistory")
public class UrlHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "url_id")
    private Long url_id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "usage_date")
    private LocalDateTime usageDate;

    public UrlHistory(Long url_id, Long user_id, LocalDateTime usageDate){
        this.url_id=url_id;
        this.user_id=user_id;
        this.usageDate=usageDate;
    }

    public UrlHistory() {

    }

    public LocalDateTime getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(LocalDateTime usageDate) {
        this.usageDate = usageDate;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getUrl_id() {
        return url_id;
    }

    public void setUrl_id(Long url_id) {
        this.url_id = url_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
