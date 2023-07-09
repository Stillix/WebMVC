package com.example.webmvc.entity;

import java.time.LocalDateTime;

public class News {
    private int newsId;
    private String title;
    private int idMissingPerson;
    private String Content;
    private LocalDateTime publicationDate;

    public News(int newsId, String title, int idMissingPerson, String content, LocalDateTime publicationDate) {
        this.newsId = newsId;
        this.title = title;
        this.idMissingPerson = idMissingPerson;
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

    public int getIdMissingPerson() {
        return idMissingPerson;
    }

    public void setIdMissingPerson(int idMissingPerson) {
        this.idMissingPerson = idMissingPerson;
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
