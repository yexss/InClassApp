package com.xhr.inclassapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.xhr.inclassapp.databinding.ActivityCrime413Binding;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Crime4_13Activity extends AppCompatActivity implements Crime4_4Fragment.FragmentCallback {

    private ActivityCrime413Binding binding;
    final FragmentManager manager=getSupportFragmentManager();
    private List<Crime4_13> crimes;
    private Crime5_7Adapter adapter;
    private int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCrime413Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 初始化数据
        initData();

        // 初始化视图
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 添加分割线
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter=new Crime5_7Adapter(crimes);
        adapter.setOnClickListener(view-> {
            Crime5_7Adapter.ViewHolder viewHolder=(Crime5_7Adapter.ViewHolder)view.getTag();

            currentPos=viewHolder.getAdapterPosition();
            adapter.setCurrentIndex(currentPos);

            Crime4_4Fragment crime4_4Fragment=Crime4_4Fragment.newInstance(crimes.get(currentPos));

            manager.beginTransaction()
                    .replace(R.id.fragment_crime,crime4_4Fragment)
                    .addToBackStack(null)
                    .commit();

        });

        binding.recyclerView.setAdapter(adapter);

    }

    private void initData() {
        crimes= new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Crime4_13 crime=new Crime4_13();
            crime.setId(i);
            crime.setTitle("Crime #"+i);
            crime.setDate(new Date());
            crimes.add(crime);
        }
    }

    @Override
    public void changeData(Crime4_13 crime) {
        crimes.set(currentPos,crime);
        adapter.notifyItemChanged(currentPos);
    }
}