package com.xhr.inclassapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.xhr.inclassapp.databinding.ActivityForm314Binding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Form3_14Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,View.OnClickListener{

    private ActivityForm314Binding binding;
    private String selected="";
    private static final String PHONE_PATTERN="^1[3-9]\\d{9}$";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_form3_14);

        binding=ActivityForm314Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.submit.setOnClickListener(this);

        binding.cbjava.setOnCheckedChangeListener(this);
        binding.cbmath.setOnCheckedChangeListener(this);
        binding.cbpython.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        CheckBox checkbox=(CheckBox) buttonView;
        if(isChecked)
            selected+=checkbox.getText().toString()+", ";
        else
            selected=selected.replace(checkbox.getText().toString()+", ","");
//        Snackbar.make(binding.form314,selected,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        String username=binding.username.getText().toString().trim();
        String phone=binding.phone.getText().toString().trim();
        String sex=null;

        if(!vaildatePhone(phone)){
            binding.phone.setError("请输入正确手机号");
            binding.phone.setText("");
            binding.phone.requestFocus();
            return;
        }

        if(binding.man.isChecked())
            sex=binding.man.getText().toString();
        else if(binding.woman.isChecked())
            sex=binding.woman.getText().toString();

        String info="用户名: "+username+", 手机号: "+phone+", sex: "+sex+", favourite: "+selected;

        Snackbar.make(binding.getRoot(),info,Snackbar.LENGTH_LONG)
                .setAction("确定",new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(Form3_14Activity.this,"信息已确认",Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private boolean vaildatePhone(String phone){
        Pattern pattern=Pattern.compile(PHONE_PATTERN);
        Matcher matcher=pattern.matcher(phone);
        return matcher.matches();
    }
}
