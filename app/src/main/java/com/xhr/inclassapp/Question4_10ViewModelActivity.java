package com.xhr.inclassapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xhr.inclassapp.databinding.ActivityQuestion410ViewModelBinding;

public class Question4_10ViewModelActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityQuestion410ViewModelBinding binding;
    private Question4_10ViewModel quizViewModel;
    public static final String EXTRA_ANSWER_IS_TRUE = "answer_is_true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityQuestion410ViewModelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        quizViewModel=new ViewModelProvider(this).get(Question4_10ViewModel.class);

        binding.btnTrue.setOnClickListener(this);
        binding.btnFalse.setOnClickListener(this);
        binding.btnNext.setOnClickListener(this);
        binding.btnCheat.setOnClickListener(this);

        quizViewModel.setCurrentIndex(0);
        updateQuestion();
    }

    private void updateQuestion() {
        int resId = quizViewModel.getCurrentQuestionResId();
        binding.questionTextView.setText(resId);
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
                quizViewModel.moveToNext();
                quizViewModel.setCheat(false);
                updateQuestion();
                break;
            case R.id.btnCheat:
                final Intent intent = new Intent(Question4_10ViewModelActivity.this, Cheat3_31Activity.class);
                intent.putExtra(EXTRA_ANSWER_IS_TRUE, String.valueOf(quizViewModel.getCurrentQuestionAnswer()));
                final ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(view, 0, 0, view.getWidth(), view.getHeight());
                launcher.launch(intent, options);
        }
    }

    private void checkAnswer(boolean userAnswer) {
        boolean correctAnswer = quizViewModel.getCurrentQuestionAnswer();
        int msgResId;
        if (quizViewModel.isCheat()) {
            msgResId = R.string.judgment_toast;
        } else {
            msgResId = correctAnswer==userAnswer ? R.string.correct_toast : R.string.incorrect_toast;
        }
        Toast.makeText(this, msgResId, Toast.LENGTH_SHORT).show();
    }

    private final ActivityResultLauncher<Intent> launcher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode()==RESULT_OK){
                    final Intent data=result.getData();
                    if(data!=null){
                        quizViewModel.setCheat(data.getBooleanExtra(Cheat3_31Activity.EXTRA_ANSWER_SHOWN,false));
                    }
                }
            }
    );
}