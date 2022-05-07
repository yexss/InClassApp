package com.xhr.inclassapp;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Crime4_13 implements Serializable {
    private String title;
    private Integer id;
    private Date date;
    private boolean isSolved;

    public Crime4_13() { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    @NonNull
    @Override
    public String toString() {
        return "Crime4_13{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", date=" + date +
                ", isSolved=" + isSolved +
                '}';
    }
}
