package com.example.demo;

import java.time.ZonedDateTime;

public class Link {
    private final long id;
    private String name;
    private String link;
    private String shortLink;
    private Integer clickCount;
    private final String createdAt;
    private String editedAt;

    public Link(long id, String name, String link, String shortLink) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.shortLink = shortLink;
        this.clickCount = 1;
        this.createdAt = ZonedDateTime.now().toString();
        this.editedAt = ZonedDateTime.now().toString();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(String editedAt) {
        this.editedAt = editedAt;
    }

    //generate random string as short link
    public String generateShortLink(){
        String shortenedLink = null;
        Integer linkSize = 6;

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "0123456789"
        + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(linkSize);

        for (int i = 0; i < linkSize; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
            = (int)(AlphaNumericString.length()
            * Math.random());

            // append character to end of string
            sb.append(AlphaNumericString.charAt(index));
        }
        shortenedLink = "http://localhost:8080/"+sb.toString();
        return shortenedLink;
    }
}

