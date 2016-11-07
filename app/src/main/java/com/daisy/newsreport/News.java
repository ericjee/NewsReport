package com.daisy.newsreport;

/**
 * Created by macmini2 on 07/11/2016.
 */

public class News {
    private String newsUrl;
    private String newsTitle;
    private String sectionName;
    private String time;

    public News(String newsUrl, String newsTitle, String sectionName, String time) {
        this.newsUrl = newsUrl;
        this.newsTitle = newsTitle;
        this.sectionName = sectionName;
        this.time = time;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getTime() {
        return time;
    }
}
