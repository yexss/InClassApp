package com.xhr.inclassapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xhr.inclassapp.databinding.ActivityIntentMain328Binding;

import java.util.ArrayList;

public class IntentMain3_28Activity extends AppCompatActivity implements View.OnClickListener {

    private ActivityIntentMain328Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityIntentMain328Binding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.btnData.setOnClickListener(this);
        binding.btnList.setOnClickListener(this);
        binding.btnObject.setOnClickListener(this);
        binding.btnBundle.setOnClickListener(this);
        binding.btnReturn.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        final Intent intent = new Intent(IntentMain3_28Activity.this, IntentSecond3_28Activity.class);
        switch (view.getId()){
            case R.id.btn_data:
                intent.putExtra("data", "今天天气很暖和");
                break;

            case R.id.btn_list:
                ArrayList<Integer> datas=new ArrayList<>();
                datas.add(85);
                datas.add(90);
                datas.add(33);
                intent.putIntegerArrayListExtra("list", datas);
                break;

            case R.id.btn_object:
                intent.putExtra("object", new User("张三", 20));
                break;

            case R.id.btn_bundle:
                final Bundle bundle=new Bundle();
                bundle.putString("username","张三");
                bundle.putInt("age", 20);
                bundle.putSerializable("user", new User("李四", 21));
                intent.putExtras(bundle);
                break;

            case R.id.btn_return:
                intent.putExtra("data", "返回数据123");
//                startActivityForResult(intent, 1);

                launcher.launch(intent);
//                mylauncher.launch("传递字符串自定义");
                return;

        }
        startActivity(intent);
    }

    private final ActivityResultLauncher<Intent> launcher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==RESULT_OK){
                        final Intent data=result.getData();
                        if(data!=null){
                            binding.tvData.setText(data.getStringExtra("data"));
                        }else{
                            binding.tvData.setText("没有数据");
                        }
                    }
                }
            }
    );

    private final ActivityResultLauncher<String> mylauncher=registerForActivityResult(
            new MyResultContract(),
            new ActivityResultCallback<String>() {
                @Override
                public void onActivityResult(String result) {
                    binding.tvData.setText(result);
                }
            }
    );

    //自定义的ActivityResultContract
    class MyResultContract extends ActivityResultContract<String,String>{

        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, String input) {
            Intent intent = new Intent(IntentMain3_28Activity.this, IntentSecond3_28Activity.class);
            intent.putExtra("data", input);
            return intent;
        }

        @Override
        public String parseResult(int resultCode, @Nullable Intent intent) {
            if (resultCode == RESULT_OK && intent != null) {
                return intent.getStringExtra("data");
            }
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null){
            final String returnData = data.getStringExtra("data");
            if(returnData!=null){
                binding.tvData.setText(returnData);
            }
        }else{
            binding.tvData.setText("没有返回数据");
        }
    }
}