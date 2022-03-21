package com.xhr.inclassapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.googlecode.aviator.AviatorEvaluator;
import com.xhr.inclassapp.databinding.ActivityCalculator314Binding;

public class Calculator3_14Activity extends AppCompatActivity implements View.OnClickListener {

    private ActivityCalculator314Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalculator314Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button0.setOnClickListener(this);
        binding.button1.setOnClickListener(this);
        binding.button2.setOnClickListener(this);
        binding.button3.setOnClickListener(this);
        binding.button4.setOnClickListener(this);
        binding.button5.setOnClickListener(this);
        binding.button6.setOnClickListener(this);
        binding.button7.setOnClickListener(this);
        binding.button8.setOnClickListener(this);
        binding.button9.setOnClickListener(this);

        binding.buttonEq.setOnClickListener(view -> {
            String expression = binding.textInput.getText().toString();
            Object result= AviatorEvaluator.execute(expression);
            binding.textInput.setText(result.toString());
        });
        binding.buttonBack.setOnClickListener(view -> {
            String expression = binding.textInput.getText().toString();
            if (expression.length() > 0) {
                binding.textInput.setText(expression.substring(0, expression.length() - 1));
            }
        });

        binding.buttonAdd.setOnClickListener(view -> {
            binding.textInput.append("+");
        });
        binding.buttonSub.setOnClickListener(view -> {
            binding.textInput.append("-");
        });
        binding.buttonMul.setOnClickListener(view -> {
            binding.textInput.append("*");
        });
        binding.buttonDiv.setOnClickListener(view -> {
            binding.textInput.append("/");
        });
        binding.buttonRem.setOnClickListener(view -> {
            binding.textInput.append("%");
        });
        binding.buttonPow.setOnClickListener(view -> {
            binding.textInput.append("^");
        });
        binding.buttonClr.setOnClickListener(view -> {
            binding.textInput.setText("");
        });
        binding.buttonDec.setOnClickListener(view -> {
            binding.textInput.append(".");
        });



    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_0:
                binding.textInput.append("0");
                break;
            case R.id.button_1:
                binding.textInput.append("1");
                break;
            case R.id.button_2:
                binding.textInput.append("2");
                break;
            case R.id.button_3:
                binding.textInput.append("3");
                break;
            case R.id.button_4:
                binding.textInput.append("4");
                break;
            case R.id.button_5:
                binding.textInput.append("5");
                break;
            case R.id.button_6:
                binding.textInput.append("6");
                break;
            case R.id.button_7:
                binding.textInput.append("7");
                break;
            case R.id.button_8:
                binding.textInput.append("8");
                break;
            case R.id.button_9:
                binding.textInput.append("9");
                break;
        }
    }
}
