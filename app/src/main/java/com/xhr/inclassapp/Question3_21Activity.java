package com.xhr.inclassapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xhr.inclassapp.databinding.ActivityQuestion321Binding;

public class Question3_21Activity extends AppCompatActivity {

    private ActivityQuestion321Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestion321Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
