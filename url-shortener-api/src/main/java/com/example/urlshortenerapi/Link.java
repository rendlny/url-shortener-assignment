package com.example.urlshortenerapi;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Link {
    private @Id @GeneratedValue Long id;
    private String name;
    private String link;
    private String code;
    private Integer clickCount;
    private String createdAt;
    private String editedAt;

    public Link() {
        this.name = null;
        this.link = null;
        this.code = null;
        this.clickCount = 1;
        this.createdAt = ZonedDateTime.now().toString();
        this.editedAt = ZonedDateTime.now().toString();
    }

    public Link(String link) {
        this.name = null;
        this.link = link;
        this.clickCount = 1;
        this.createdAt = ZonedDateTime.now().toString();
        this.editedAt = ZonedDateTime.now().toString();
        this.generateCode();
    }

    public Link(long id, String name, String link, String code) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.code = code;
        this.clickCount = 1;
        this.createdAt = ZonedDateTime.now().toString();
        this.editedAt = ZonedDateTime.now().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(String editedAt) {
        this.editedAt = editedAt;
    }

    //generate random string as short link
    public void generateCode(){
        String code = null;
        Integer codeSize = 6;

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "0123456789"
        + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(codeSize);

        for (int i = 0; i < codeSize; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
            = (int)(AlphaNumericString.length()
            * Math.random());

            // append character to end of string
            sb.append(AlphaNumericString.charAt(index));
        }
        code = sb.toString();
        this.code = code;
    }

    @Override
    public String toString(){
        return "Link{" + "id=" + this.id + ", name='" + this.name + '\'' + ", link='" + this.link
        + '\'' + ", code='" + this.code + '\'' + ", clickCount='" + this.clickCount 
        + '\'' + ", createdAt='" + this.createdAt + '\'' + ", editedAt='" + this.editedAt + '\'' + '}';
    }
}

