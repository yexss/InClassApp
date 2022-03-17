package com.xhr.inclassapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xhr.inclassapp.databinding.ActivityBmi314Binding;


public class Bmi3_14Activity extends AppCompatActivity {

    private ActivityBmi314Binding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityBmi314Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.calculate.setOnClickListener(v -> {
           final String weight = binding.weight.getText().toString();
           final String height = binding.height.getText().toString();
           String sex=null;
           if(binding.man.isChecked())
               sex=binding.man.getText().toString();
           else if(binding.woman.isChecked())
               sex=binding.woman.getText().toString();
           else
               Toast.makeText(this,"Please select sex",Toast.LENGTH_SHORT).show();


           if(TextUtils.isEmpty(weight) || TextUtils.isEmpty(height) || sex==null){
               Toast.makeText(this, "Please enter both weight and height", Toast.LENGTH_SHORT).show();
           } else {
               binding.bmiValue.setText("BMI: "+calculateBMI(weight, height));
               binding.bmiStatus.setText("诊断: "+judgeBMI(Double.parseDouble(calculateBMI(weight,height)),sex));
           }
        });

    }

    @SuppressLint("DefaultLocale")
    private String calculateBMI(String weight, String height) {
        double w = Double.parseDouble(weight);
        double h = Double.parseDouble(height);
        double bmi = w / (h * h);
        return String.format("%.2f", bmi);
    }

    private String judgeBMI(Double bmi,String sex){
        String result = "";

        if(sex.equals("男"))
            if(bmi < 20){
                result = "体重过轻";
            } else if(bmi < 25){
                result = "体重正常";
            } else if(bmi < 27){
                result = "体重超重";
            } else if(bmi < 30){
                result = "轻度肥胖";
            } else if(bmi < 35){
                result = "中度肥胖";
            } else{
                result = "重度肥胖";
            }
        else
            if(bmi < 19){
                result = "体重过轻";
            } else if(bmi < 24){
                result = "体重正常";
            } else if(bmi < 26){
                result = "体重超重";
            } else if(bmi < 29){
                result = "轻度肥胖";
            } else if(bmi < 34){
                result = "中度肥胖";
            } else{
                result = "重度肥胖";
            }

        return result;
    }
}
