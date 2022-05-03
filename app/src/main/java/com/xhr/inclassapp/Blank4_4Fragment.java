package com.xhr.inclassapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xhr.inclassapp.databinding.FragmentBlank44Binding;


public class Blank4_4Fragment extends Fragment {

    private static final String TAG = "BlankFragment";
    private static final String ARG_PARAM = "param";
    private String param;

    public Blank4_4Fragment() {

    }

    public static Blank4_4Fragment newInstance(String param) {
        Blank4_4Fragment fragment = new Blank4_4Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, param);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            param = getArguments().getString(ARG_PARAM);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentBlank44Binding binding=FragmentBlank44Binding.inflate(inflater,container,false);
        if(param!=null && !param.isEmpty()){
            binding.txtMsg.setText(param);
        }
//        Toast.makeText(requireContext(),"fragement的值"+binding.txtMsg.getText().toString(),Toast.LENGTH_SHORT).show();
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

}