package com.xhr.inclassapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.xhr.inclassapp.DatePicker4_13Dialog;
import com.xhr.inclassapp.databinding.FragmentCrime44Binding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Crime4_4Fragment extends Fragment {

    private FragmentCrime44Binding binding;
    private boolean isChangeDate=false;


    @SuppressLint("SimpleDateFormat")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCrime44Binding.inflate(inflater, container, false);

        Bundle arguments=getArguments();

        assert arguments != null;
        Crime4_13 crime= (Crime4_13) arguments.getSerializable("crime");
        int position=arguments.getInt("position");
        binding.title.setText(crime.getTitle());
        binding.detail.setText(crime.getDate().toString());
        binding.solved.setChecked(crime.isSolved());




        // 5.通过点击事件调用接口的回调方法，将数据传递给DataActivity
        Crime4_13 IntentCrime = new Crime4_13();
        binding.submit.setOnClickListener(view -> {
            IntentCrime.setId(crime.getId());
            IntentCrime.setTitle(binding.title.getText().toString());
            if (!isChangeDate) {
                IntentCrime.setDate(crime.getDate());
            }
            final Bundle bundle = new Bundle();
            bundle.putSerializable("IntentCrime",IntentCrime);
            bundle.putInt("position", position);
            NavHostFragment.findNavController(Crime4_4Fragment.this)
                    .navigate(R.id.action_CrimeFragment_to_ListFragment, bundle);
        });

        binding.solved.setOnCheckedChangeListener((buttonView, isChecked) -> {
            IntentCrime.setSolved(isChecked);
        });


//        binding.detail.setText(new Date().toString());
        binding.detail.setOnClickListener(view -> {
            DatePicker4_13Dialog dlg = new DatePicker4_13Dialog();
            dlg.show(getChildFragmentManager(), "选择");
        });

        getChildFragmentManager().setFragmentResultListener(DatePicker4_13Dialog.SELECTED_DATE, this, (requestKey, result) -> {
            int year = result.getInt(DatePicker4_13Dialog.SELECTED_YEAR);
            int month = result.getInt(DatePicker4_13Dialog.SELECTED_MONTH) + 1;
            int day = result.getInt(DatePicker4_13Dialog.SELECTED_DAY);


            Calendar instance = Calendar.getInstance();
            String strTime = year + "-" + month + "-" + day;
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(strTime);
                assert date != null;
                instance.setTime(date);//将时间对象data设置为新的日历
                IntentCrime.setDate(instance.getTime());
                binding.detail.setText(instance.getTime().toString());
                isChangeDate=true;
//                binding.detail.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E").format(instance.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}