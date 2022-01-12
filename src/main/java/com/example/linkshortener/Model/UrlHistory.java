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
    private Long urlid;

    @Column(name = "user_id")
    private Long userid;

    @Column(name = "usage_date")
    private LocalDateTime usageDate;

    public UrlHistory(Long urlid, Long user_id, LocalDateTime usageDate){
        this.urlid = urlid;
        this.userid =user_id;
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

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long user_id) {
        this.userid = user_id;
    }

    public Long getUrlid() {
        return urlid;
    }

    public void setUrlid(Long url_id) {
        this.urlid = url_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
