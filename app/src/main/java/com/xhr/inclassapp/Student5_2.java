package com.xhr.inclassapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class Student5_2 implements Serializable {
    private int id;
    private String name;
    private String classmate;
    private int age;

    public Student5_2() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassmate() {
        return classmate;
    }

    public void setClassmate(String classmate) {
        this.classmate = classmate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NonNull
    @Override
    public String toString() {
        return "Student5_2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classmate='" + classmate + '\'' +
                ", age=" + age +
                '}';
    }
}
