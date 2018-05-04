package com.example.erik.newsapp.model;

import java.util.Objects;

public class News {
    String url;
    String title;
    String section;
    String date;

    public News(String url, String title, String section, String date){
        this.url = url;
        this.title = title;
        this.section = section;
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setId(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return Objects.equals(getUrl(), news.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl());
    }
}
