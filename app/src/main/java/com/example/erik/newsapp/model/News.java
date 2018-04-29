package com.example.erik.newsapp.model;

import java.util.Objects;

public class News {
    String id;
    String title;
    String author;
    String section;
    String date;

    public News(String id, String title, String author, String section, String date){
        this.id = id;
        this.title = title;
        this.author = author;
        this.section = section;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return Objects.equals(getId(), news.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
