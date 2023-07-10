package com.example.webmvc.entity;

import java.time.LocalDateTime;

public class News {
    private int newsId;
    private String title;
    private int personId;
    private String Content;
    private LocalDateTime publicationDate;

    public News(int newsId, String title, int personId, String content, LocalDateTime publicationDate) {
        this.newsId = newsId;
        this.title = title;
        this.personId = personId;
        Content = content;
        this.publicationDate = publicationDate;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }
}
