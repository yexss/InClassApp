package com.xhr.inclassapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.xhr.inclassapp.databinding.DialogLoginBinding;

public class Login4_11Dialog extends DialogFragment {

    private DialogLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DialogLoginBinding.inflate(inflater,container,false);

        binding.btnLogin.setOnClickListener(view -> {
            // 获取用户名或密码
            String username=binding.etUsername.getText().toString();
            String password=binding.etPassword.getText().toString();
            // 通过DialogActivity回调方法将数据传递给Activity
//            callback.onDlgPositiveClick(username,password);


            // 使用Fragment Result API替代回调方式的Fragment向Activity传递主数据
            Bundle bundle = new Bundle();
            bundle.putString("username", username);
            bundle.putString("password", password);
            getParentFragmentManager().setFragmentResult("login", bundle);
            // 关闭对话框
            Login4_11Dialog.this.dismiss();
        });

        binding.btnCancel.setOnClickListener(view->{
            callback.onDlgNegativeClick(Login4_11Dialog.this);
            Login4_11Dialog.this.dismiss();
        });

        return binding.getRoot();
    }

    private LoginCallback callback;

    public interface LoginCallback{
//        void onDlgPositiveClick(String username,String password);
        void onDlgNegativeClick(DialogFragment dialog);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginCallback) {
            this.callback= (LoginCallback) context;
        }else{
            throw new ClassCastException(context+"未实现LoginCallback");
        }
    }
}
