package com.xhr.inclassapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xhr.inclassapp.databinding.ActivityForm314Binding;
import com.xhr.inclassapp.databinding.ActivityLogin314Binding;

public class Login3_14Activity extends AppCompatActivity {

    private ActivityLogin314Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityLogin314Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.submit.setOnClickListener(view -> {
            final String username=binding.username.getText().toString();
            final String password=binding.password.getText().toString();

            if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password))
                Toast.makeText(Login3_14Activity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
            else if(!username.equals("yexss")||!password.equals("xhr740630"))
                Toast.makeText(Login3_14Activity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
            else{
                final Intent intent=new Intent(Login3_14Activity.this,Form3_14Activity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });
    }
}
