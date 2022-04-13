package com.xhr.inclassapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.xhr.inclassapp.databinding.ActivityCheat331Binding;

import java.util.Locale;

public class Cheat3_31Activity extends AppCompatActivity {

    private ActivityCheat331Binding binding;
    public static final String EXTRA_ANSWER_SHOWN = "answer_shown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheat331Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String answer=intent.getStringExtra(Question4_10ViewModelActivity.EXTRA_ANSWER_IS_TRUE);

        binding.btnShow.setOnLongClickListener(v -> {
            final Intent returnIntent=new Intent();
            returnIntent.putExtra(EXTRA_ANSWER_SHOWN,true);
            setResult(RESULT_OK,returnIntent);
            finish();
            return false;
        });

        binding.btnShow.setOnClickListener(v -> {
            binding.answer.setText(answer.toUpperCase(Locale.ROOT));
        });
    }
}