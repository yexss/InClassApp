package com.xhr.inclassapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository5_11 {

    private StudentRoomDao5_11 studentDao;


    public StudentRepository5_11(Application application) {
        StudentRoomDatabase5_11 db = StudentRoomDatabase5_11.getDatabase(application);
        studentDao = db.getStudentRoomDao();
    }


    public void insert(Student5_11Entity student) {
        StudentRoomDatabase5_11.databaseWriteExecutor.execute(() -> {
            studentDao.insert(student);
        });
    }

    public void update(Student5_11Entity student){
        StudentRoomDatabase5_11.databaseWriteExecutor.execute(() -> {
            studentDao.update(student);
        });
    }

    public void delete(Student5_11Entity student){
        StudentRoomDatabase5_11.databaseWriteExecutor.execute(() -> {
            studentDao.delete(student);
        });
    }

    public List<Student5_11Entity> getAll(){
        return studentDao.getAll();
    }

    public List<Student5_11Entity> getAll(String kw){
        if (kw == null || kw.isEmpty()) {
            return studentDao.getAll();
        }
        return studentDao.getAll(kw);
    }

}
