package com.xhr.inclassapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.xhr.inclassapp.databinding.ActivityDialog411Binding;

public class Dialog4_11Activity extends AppCompatActivity implements Login4_11Dialog.LoginCallback {

    private ActivityDialog411Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDialog411Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(view -> {
            Login4_11Dialog dlg=new Login4_11Dialog();
            dlg.show(getSupportFragmentManager(),"登录");
        });

        binding.btnLoginout.setOnClickListener(view -> {
            Loginout4_11Dialog dlg=new Loginout4_11Dialog();
            dlg.show(getSupportFragmentManager(),"退出");
        });

        // 使用Fragment Result API替代回调方式获取Fragment传递的数据
        getSupportFragmentManager().setFragmentResultListener("login", this, (requestKey, result) -> {
            String username = result.getString("username");
            String password = result.getString("password");
            Toast.makeText(Dialog4_11Activity.this, username + ", " + password, Toast.LENGTH_SHORT).show();
        });
    }

//    @Override
//    public void onDlgPositiveClick(String username, String password) {
//        Toast.makeText(this,username+","+password,Toast.LENGTH_SHORT).show();
//    }
//
    @Override
    public void onDlgNegativeClick(DialogFragment dialog) {
        Toast.makeText(this,"取消",Toast.LENGTH_SHORT).show();
    }
}