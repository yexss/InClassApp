package com.xhr.inclassapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.xhr.inclassapp.databinding.ActivityIntentSecond328Binding;

import java.util.ArrayList;

public class IntentSecond3_28Activity extends AppCompatActivity {

    private ActivityIntentSecond328Binding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityIntentSecond328Binding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        final Intent intent=getIntent();

        final String data=intent.getStringExtra("data");
        if(data!=null)
            binding.tvData.setText(data);

        ArrayList<Integer> datas=intent.getIntegerArrayListExtra("list");
        if (datas!=null)
            binding.tvData.setText(datas.toString());

        User user= (User) intent.getSerializableExtra("object");
        if(user!=null)
            binding.tvData.setText(user.toString());

        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            String name=bundle.getString("username");
            int age=bundle.getInt("age");
            if(name!=null)
                binding.tvData.setText(name+","+age);
        }


        binding.btnBack.setOnClickListener(v->{
            final Intent returnIntent=new Intent();
            returnIntent.putExtra("data","返回数据fff");
            setResult(RESULT_OK,returnIntent);
            finish();
        });
    }
}