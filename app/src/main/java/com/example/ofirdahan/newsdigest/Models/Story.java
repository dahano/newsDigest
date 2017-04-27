package com.example.ofirdahan.newsdigest.Models;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Story {
    Long created_at_i;
    String title;
    String author;
    String url;
    int points;
    List<Story> hits;

    public Story(Long created_at_i, String title, String author, String url, int points, float rating) {
        this.created_at_i = created_at_i;
        this.title = title;
        this.author = author;
        this.url = url;
        this.points = points;
    }

    public List<Story> getHits() {
        return hits;
    }

    public String getCreatedAt() {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        Date dateTime = new Date(created_at_i * 1000);
        return formatter.format(dateTime);
    }


    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }


    public String getPoints() {
        return String.valueOf(points);
    }

    public String getUrl(){
        return url;
    }

}
