package com.xhr.inclassapp;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

public class Question4_10ViewModel extends ViewModel {
    private static final String TAG="Question4_10ViewModel";

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

    public Question4_10ViewModel(){
        super();
        Log.d(TAG,"createViewModel");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG,"DestoryViewModel");
    }

    public int getCurrentQuestionResId(){
        return questions.get(currentIndex).getTextResId();
    }

    public boolean getCurrentQuestionAnswer(){
        return questions.get(currentIndex).isAnswer();
    }

    public void moveToNext(){
        currentIndex=(currentIndex+1)%questions.size();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public boolean isCheat() {
        return isCheat;
    }

    public void setCheat(boolean cheat) {
        isCheat = cheat;
    }
}
