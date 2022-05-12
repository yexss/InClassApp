package com.xhr.inclassapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel5_12 extends AndroidViewModel {

    private final StudentRepository5_11 mRepository;

    private final List<Student5_11Entity> mAllStudents;

    public StudentViewModel5_12 (Application application) {
        super(application);
        mRepository = new StudentRepository5_11(application);
        mAllStudents = mRepository.getAllStudents();
    }

    List<Student5_11Entity> getAllStudents() { return mAllStudents; }

    public void insert(Student5_11Entity student) { mRepository.insert(student); }
}
