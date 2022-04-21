package com.xhr.inclassapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xhr.inclassapp.databinding.FragmentDashboardChild418Binding;


public class DashboardChild4_18Fragment extends Fragment {

    private FragmentDashboardChild418Binding binding;

    private static final String ARG_POSITION = "arg_position";

    public static DashboardChild4_18Fragment newInstance(int position) {
        DashboardChild4_18Fragment fragment = new DashboardChild4_18Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDashboardChild418Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            final int position = getArguments().getInt(ARG_POSITION, -1);
            binding.tvChild.setText("dashboard"+position);
        }
    }
}