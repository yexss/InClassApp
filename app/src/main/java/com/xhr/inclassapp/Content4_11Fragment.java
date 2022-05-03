package com.xhr.inclassapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.xhr.inclassapp.databinding.FragmentContent411Binding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Content4_11Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Content4_11Fragment extends Fragment {

    private FragmentContent411Binding binding;
    public Content4_11Fragment() { }


    public static Content4_11Fragment newInstance() {
        return new Content4_11Fragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentContent411Binding.inflate(inflater,container,false);

        // 5.通过点击事件调用接口的回调方法，将数据传递给DataActivity
        binding.txtContent.setOnClickListener(view -> {
            callback.onItemSelected(12);

            // 1、fragment与fragment
            Bundle value=new Bundle();
            value.putString("title","第12条新闻的标题");
            getParentFragmentManager().setFragmentResult("key",value);
        });
        return binding.getRoot();
    }

    // 1.定义一个内部回调接口
    public interface FragmentCallback{
        void onItemSelected(int position);
    }

    // 3.定义一个接口对象
    private FragmentCallback callback;

    // 4.获取Activity的回调接口对象
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCallback)
            this.callback= (FragmentCallback) context;
        else
            throw new ClassCastException(context+"必须实现FragmentCallback接口");
    }
}