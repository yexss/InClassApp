package com.xhr.inclassapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xhr.inclassapp.databinding.ActivityQuestion321Binding;

import java.util.Arrays;
import java.util.List;

public class Question3_21Activity extends AppCompatActivity implements View.OnClickListener {

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



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestion321Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnTrue.setOnClickListener(this);
        binding.btnFalse.setOnClickListener(this);
        binding.btnNext.setOnClickListener(this);

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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnTrue:
                checkAnswer(true);
                break;
            case R.id.btnFalse:
                checkAnswer(false);
                break;
            case R.id.btnNext:
                currentIndex=(currentIndex+1)%questions.size();
                updateQuestion();
                break;
        }
    }
}
