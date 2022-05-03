package com.xhr.inclassapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.xhr.inclassapp.databinding.DialogDatePicker413Binding;

public class DatePicker4_13Dialog extends DialogFragment {

    private DialogDatePicker413Binding binding;


    public final static String SELECTED_DATE="date";
    public final static String SELECTED_YEAR="year";
    public final static String SELECTED_MONTH="month";
    public final static String SELECTED_DAY="dayOfMonth";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=DialogDatePicker413Binding.inflate(inflater,container,false);

        binding.calendarSelected.setOnDateChangeListener((calendarView, year, month, day) -> {
            Bundle bundle=new Bundle();
            bundle.putInt(SELECTED_YEAR, year);
            bundle.putInt(SELECTED_MONTH, month);
            bundle.putInt(SELECTED_DAY, day);
            getParentFragmentManager().setFragmentResult(SELECTED_DATE,bundle);

//            DatePicker4_13Dialog.this.dismiss();
        });

        return binding.getRoot();
    }
}