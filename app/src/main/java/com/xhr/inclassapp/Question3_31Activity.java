package com.xhr.inclassapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xhr.inclassapp.databinding.ActivityQuestion331Binding;

import java.util.Arrays;
import java.util.List;

public class Question3_31Activity extends AppCompatActivity implements View.OnClickListener {

    private ActivityQuestion331Binding binding;

    private final List<Question3_21> questions= Arrays.asList(
            new Question3_21(R.string.question_australia,true),
            new Question3_21(R.string.question_oceans,true),
            new Question3_21(R.string.question_mideast,false),
            new Question3_21(R.string.question_africa,true),
            new Question3_21(R.string.question_americas,false),
            new Question3_21(R.string.question_asia,true)
    );
    private int currentIndex=0;
    private boolean isCheat=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestion331Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnTrue.setOnClickListener(this);
        binding.btnFalse.setOnClickListener(this);
        binding.btnNext.setOnClickListener(this);
        binding.btnCheat.setOnClickListener(this);

        updateQuestion();
    }

    private void updateQuestion(){
        int resId = questions.get(currentIndex).getTextResId();
        binding.questionTextView.setText(resId);
    }

    private void checkAnswer(boolean userAnswer){
        final boolean correctAnswer = questions.get(currentIndex).isAnswer();

        if(isCheat)
            Toast.makeText(this, "Cheating is wrong.", Toast.LENGTH_SHORT).show();
        else
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
                isCheat=false;
                currentIndex=(currentIndex+1)%questions.size();
                updateQuestion();
                break;
            case R.id.btnCheat:
                final Intent intent = new Intent(Question3_31Activity.this, Cheat3_31Activity.class);
                intent.putExtra(Question4_10ViewModelActivity.EXTRA_ANSWER_IS_TRUE, String.valueOf(questions.get(currentIndex).isAnswer()));
                launcher.launch(intent);
        }
    }

    private final ActivityResultLauncher<Intent> launcher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode()==RESULT_OK){
                    final Intent data=result.getData();
                    if(data!=null){
                        isCheat=data.getBooleanExtra(Cheat3_31Activity.EXTRA_ANSWER_SHOWN,true);
                    }
                }
            }
    );
}