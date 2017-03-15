package com.example.ofirdahan.newsdigest;


public class Story {
    private String mCreatedAt;
    private String mTitle;
    private String mAuthor;
    private String mUrl;
    private int mPoints;

    public Story(String mCreatedAt, String mTitle, String mAuthor, String mUrl, int mPoints) {
        this.mCreatedAt = mCreatedAt;
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mUrl = mUrl;
        this.mPoints = mPoints;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }


    public String getTitle() {
        return mTitle;
    }


    public String getAuthor() {
        return mAuthor;
    }

    public String getUrl() {
        return mUrl;
    }

    public int getPoints() {
        return mPoints;
    }

}
