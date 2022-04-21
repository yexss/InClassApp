package com.xhr.inclassapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xhr.inclassapp.databinding.FragmentNotification418Binding;


public class Notification4_18Fragment extends Fragment {

    private FragmentNotification418Binding binding;

    public static Notification4_18Fragment newInstance() {
        Notification4_18Fragment fragment = new Notification4_18Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentNotification418Binding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}