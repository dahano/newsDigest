package com.example.ofirdahan.newsdigest;

import java.util.List;

public class Story {
    private List<Story> hits;
    private String created_at_i;
    private String title;
    private String author;
    private String url;
    private int points;

    public Story(String created_at_i, String title, String author, String url, int points) {
        this.created_at_i = created_at_i;
        this.title = title;
        this.author = author;
        this.url = url;
        this.points = points;
    }

    public String getCreatedAt() {
        return created_at_i;
    }


    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public int getPoints() {
        return points;
    }

}
