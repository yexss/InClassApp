package com.xhr.inclassapp;

public class Question3_21 {
    private int textResId;
    private boolean answer;

    public Question3_21(){
    }

    public Question3_21(int textResId, boolean answer) {
        this.textResId = textResId;
        this.answer = answer;
    }

    public int getTextResId() {
        return textResId;
    }

    public void setTextResId(int textResId) {
        this.textResId = textResId;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
