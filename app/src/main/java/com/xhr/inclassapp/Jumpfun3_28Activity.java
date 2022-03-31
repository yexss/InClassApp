package com.xhr.inclassapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.xhr.inclassapp.databinding.ActivityJumpfun328Binding;

public class Jumpfun3_28Activity extends AppCompatActivity implements View.OnClickListener {

    private ActivityJumpfun328Binding binding;
    private CharSequence text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJumpfun328Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 设置点击事件监听器
        binding.btnJump.setOnClickListener(this);
        binding.btnCall.setOnClickListener(this);
        binding.btnMessage.setOnClickListener(this);
        binding.btnBrowser.setOnClickListener(this);
        binding.btnShare.setOnClickListener(this);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jump:
                gotoActivity();
                break;
            case R.id.btn_call:
                callPhone();
                break;
            case R.id.btn_message:
                sendMessage();
                break;
            case R.id.btn_browser:
                openBrowser();
                break;
            case R.id.btn_share:
                shareText("分享");
                break;
            default:
                break;
        }
    }

    private void gotoActivity() {
        Intent intent = new Intent();
        intent.setAction("com.example.implicit.intent.START");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
    }

    // 打电话
    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }
    // 发短信
    private void sendMessage() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:10086"));
        intent.putExtra("sms_body","10086, 你好");
        startActivity(intent);
    }
    // 打开百度的主页
    private void openBrowser() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.baidu.com"));
        startActivity(intent);
    }
    // 选择器
    private void shareText(String shareText) {
        final String txtMimeType = "text/plain";
        final String picMimeType = "image/*";
        final String chooser = "分享列表";

        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType(txtMimeType);
        intent.setType(picMimeType);
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, shareText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, chooser));

//        ShareCompat.IntentBuilder.from(this)
//                .setText(shareText)
//                .setChooserTitle(chooser)
//                .setType(txtMimeType)
//                .setType(picMimeType)
//                .startChooser();
    }
}