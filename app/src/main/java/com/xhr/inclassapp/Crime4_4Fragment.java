package com.xhr.inclassapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.xhr.inclassapp.DatePicker4_13Dialog;
import com.xhr.inclassapp.databinding.FragmentCrime44Binding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Crime4_4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Crime4_4Fragment extends Fragment {

    private FragmentCrime44Binding binding;

    public Crime4_4Fragment() { }

    public static Crime4_4Fragment newInstance() {
        return new Crime4_4Fragment();
    }


    @SuppressLint("SimpleDateFormat")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentCrime44Binding.inflate(inflater,container,false);
        binding.detail.setText(new Date().toString());
        binding.detail.setOnClickListener(view -> {
            DatePicker4_13Dialog dlg=new DatePicker4_13Dialog();
            dlg.show(getChildFragmentManager(),"选择");
        });

        getChildFragmentManager().setFragmentResultListener(DatePicker4_13Dialog.SELECTED_DATE,this,(requestKey, result) -> {
            int year=result.getInt(DatePicker4_13Dialog.SELECTED_YEAR);
            int month=result.getInt(DatePicker4_13Dialog.SELECTED_MONTH)+1;
            int day=result.getInt(DatePicker4_13Dialog.SELECTED_DAY);


            Calendar instance = Calendar.getInstance();
            String strTime = year+"-"+month+"-"+day;
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(strTime);
                assert date != null;
                instance.setTime(date);//将时间对象data设置为新的日历
                binding.detail.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E").format(instance.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        return binding.getRoot();
    }
}