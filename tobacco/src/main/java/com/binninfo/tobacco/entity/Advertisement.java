package com.binninfo.tobacco.entity;

public class Advertisement {
    private Integer id;
    private String title;
    private String content;
    private String vidoURL;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVidoURL() {
        return vidoURL;
    }

    public void setVidoURL(String vidoURL) {
        this.vidoURL = vidoURL;
    }
}
