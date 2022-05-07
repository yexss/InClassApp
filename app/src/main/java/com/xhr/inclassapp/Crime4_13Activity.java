package com.xhr.inclassapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.xhr.inclassapp.databinding.ActivityCrime413Binding;

public class Crime4_13Activity extends AppCompatActivity {

    private ActivityCrime413Binding binding;
    final FragmentManager manager=getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCrime413Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        Crime4_4Fragment crime4_4Fragment=new Crime4_4Fragment();
        manager.beginTransaction()
                .replace(R.id.fragment_crime,crime4_4Fragment)
                .addToBackStack(null)
                .commit();
    }
}