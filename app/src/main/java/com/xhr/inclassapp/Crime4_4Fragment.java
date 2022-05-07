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
    private static final String ARG_PARAM = "param";
    private Crime4_13 mParam;
    private boolean isChangeDate = false;

    public Crime4_4Fragment() {
    }

    public static Crime4_4Fragment newInstance(Crime4_13 crime) {
        Crime4_4Fragment fragment = new Crime4_4Fragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, crime);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = (Crime4_13) getArguments().getSerializable(ARG_PARAM);
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCrime44Binding.inflate(inflater, container, false);


        if (mParam != null) {
            System.out.println(mParam);
            binding.title.setText(mParam.getTitle());
            binding.detail.setText(mParam.getDate().toString());
            binding.solved.setSelected(mParam.isSolved());
        }

        // 5.通过点击事件调用接口的回调方法，将数据传递给DataActivity
        Crime4_13 crime = new Crime4_13();
        binding.submit.setOnClickListener(view -> {
            crime.setId(mParam.getId());
            crime.setTitle(binding.title.getText().toString());
            if (!isChangeDate) {
                crime.setDate(mParam.getDate());
            }
            callback.changeData(crime);
            getActivity().onBackPressed();//销毁自己
        });

        binding.solved.setOnCheckedChangeListener((buttonView, isChecked) -> {
            crime.setSolved(isChecked);
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
                crime.setDate(instance.getTime());
                binding.detail.setText(instance.getTime().toString());
                isChangeDate=true;
//                binding.detail.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E").format(instance.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        return binding.getRoot();
    }

    // 1.定义一个内部回调接口
    public interface FragmentCallback {
        void changeData(Crime4_13 crime);
    }

    // 3.定义一个接口对象
    private Crime4_4Fragment.FragmentCallback callback;

    // 4.获取Activity的回调接口对象
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Crime4_4Fragment.FragmentCallback)
            this.callback = (Crime4_4Fragment.FragmentCallback) context;
        else
            throw new ClassCastException(context + "必须实现FragmentCallback接口");
    }
}