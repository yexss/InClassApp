package com.xhr.inclassapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.xhr.inclassapp.databinding.ActivityData411Binding;

public class Data4_11Activity extends AppCompatActivity implements Content4_11Fragment.FragmentCallback {

    private ActivityData411Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityData411Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Title4_11Fragment titleFragment=Title4_11Fragment.newInstance("新闻标题");
        Content4_11Fragment contentFragment=new Content4_11Fragment();

        replaceFragment(R.id.container_title,titleFragment);
        replaceFragment(R.id.container_content,contentFragment);
    }

    final FragmentManager manager=getSupportFragmentManager();
    private void replaceFragment(int containerId,Fragment fragment){
        manager.beginTransaction()
                .replace(containerId,fragment)
                .addToBackStack(null)
                .commit();
    }

    // 2.DataActivity实现接口
    @Override
    public void onItemSelected(int position) {
        Toast.makeText(this,"获取ContentFragment传递数据为: "+position,Toast.LENGTH_SHORT).show();
    }
}