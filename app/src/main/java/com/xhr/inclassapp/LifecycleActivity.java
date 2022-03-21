package com.xhr.inclassapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xhr.inclassapp.databinding.ActivityQuestion321Binding;

import java.util.Arrays;
import java.util.List;

public class LifecycleActivity extends AppCompatActivity {

    private ActivityQuestion321Binding binding;
    private final List<Question3_21> questions= Arrays.asList(
            new Question3_21(R.string.question_australia,true),
            new Question3_21(R.string.question_oceans,true),
            new Question3_21(R.string.question_mideast,false),
            new Question3_21(R.string.question_africa,true),
            new Question3_21(R.string.question_americas,false),
            new Question3_21(R.string.question_asia,true)
    );
    private int currentIndex=0;

    private static final String TAG="LifecycleActivity";

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause() called");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate() called");
        binding = ActivityQuestion321Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnTrue.setOnClickListener(v -> {
            checkAnswer(true);
        });
        binding.btnFalse.setOnClickListener(v -> {
            checkAnswer(false);
        });

        binding.btnNext.setOnClickListener(v -> {
            currentIndex=(currentIndex+1)%questions.size();
            updateQuestion();
        });

        updateQuestion();
    }

    private void updateQuestion(){
        int resId = questions.get(currentIndex).getTextResId();
        binding.questionTextView.setText(resId);
    }

    private void checkAnswer(boolean userAnswer){
        final boolean correctAnswer = questions.get(currentIndex).isAnswer();
        Toast.makeText(this,
                correctAnswer==userAnswer?R.string.correct_toast:R.string.incorrect_toast,
                Toast.LENGTH_SHORT)
                .show();
    }
}
