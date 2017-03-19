package com.example.ofirdahan.newsdigest.Models;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Story {
    Long created_at_i;
    String title;
    String author;
    String url;
    int points;


    public Long getCreatedAt() {
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

    public String getDayOfTheWeek(){
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        Date dateTime = new Date(created_at_i * 1000);
        return formatter.format(dateTime);
    }

}
