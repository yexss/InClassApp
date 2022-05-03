package com.xhr.inclassapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.xhr.inclassapp.databinding.ActivityFragdemo44Binding;

public class FragDemo4_4Activity extends AppCompatActivity implements View.OnClickListener {

    private ActivityFragdemo44Binding binding;
    private FragmentManager fragmentManager;
    private int index=0;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragdemo44Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        // 1.获取FragmentManager对象
        fragmentManager = getSupportFragmentManager();
//        // 2.创建fragment对象
//        Fragment fragment = BlankFragment.newInstance();
//        // 3.判断当前是否已加载fragment
//        final Fragment currentFragment= fragmentManager.findFragmentById(R.id.fragmentContainerView);
//        if (currentFragment == null) {
//            // 4.动态加载fragment
//            fragmentManager.beginTransaction()
//                    .add(R.id.fragmentContainerView, fragment)
//                    .commit();
//        }

        binding.btnAdd.setOnClickListener(this);
        binding.btnRemove.setOnClickListener(this);

        Log.d(TAG, "onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                String fragmentName=String.format("Fragment %s",++index);
                Fragment fragment = Blank4_4Fragment.newInstance(fragmentName);

                fragmentManager.beginTransaction()
                        .add(R.id.fragmentContainerView, fragment)
//                        .add(R.id.fragmentContainerView, fragment,"blankFragment")
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btn_remove:
                // 3.获取当前fragment
                fragment=fragmentManager.findFragmentById(R.id.fragmentContainerView);
//                fragment= fragmentManager.findFragmentByTag("blankFragment");
                if(fragment!=null && index!=0){
                    // 4.删除当前fragment
                    index--;
                    fragmentManager.beginTransaction()
                            .remove(fragment)
                            .commit();
                }
                break;
        }
    }
}