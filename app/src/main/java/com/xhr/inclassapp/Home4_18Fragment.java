package com.xhr.inclassapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xhr.inclassapp.databinding.FragmentHome418Binding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home4_18Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home4_18Fragment extends Fragment {

    private FragmentHome418Binding binding;

    public static Home4_18Fragment newInstance() {
        Home4_18Fragment fragment = new Home4_18Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentHome418Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}