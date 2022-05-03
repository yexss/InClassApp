package com.xhr.inclassapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xhr.inclassapp.databinding.ActivitySqlite52Binding;
import com.xhr.inclassapp.databinding.Toolbar52layoutBinding;

import java.util.ArrayList;
import java.util.List;

public class SQLite5_2Activity extends AppCompatActivity {

    private ActivitySqlite52Binding binding;
    private Toolbar52layoutBinding toolbarBinding;
    private List<Student5_2> students;
    private Student5_2Adapter adapter;
    private Student5_2 currentStudent;
    private ActivityResultLauncher<Intent> launcher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            (result)->{
//                if(result.getIntExtra("result",0)==1){
//                    Toast.makeText(this,"Student deleted",Toast.LENGTH_SHORT).show();
//                    students.remove(currentStudent);
//                    adapter.notifyDataSetChanged();
//                }
                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySqlite52Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // <mearge>的binding方式
        toolbarBinding=Toolbar52layoutBinding.bind(binding.getRoot());
        setSupportActionBar(toolbarBinding.toolbar);

        // 初始化数据
        initData();

        // 初始化视图
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 添加分割线
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter=new Student5_2Adapter(students);
        adapter.setOnClickListener(view -> {
            Student5_2Adapter.ViewHolder viewHolder= (Student5_2Adapter.ViewHolder) view.getTag();
            final int position=viewHolder.getAdapterPosition();
            adapter.setCurrentIndex(position);
            currentStudent=students.get(position);

            final Intent intent = new Intent(SQLite5_2Activity.this, StudentUpdate5_2Activity.class);
            final Bundle bundle = new Bundle();
            bundle.putSerializable("student", currentStudent);
            intent.putExtras(bundle);
            launcher.launch(intent);
        });
        binding.recyclerView.setAdapter(adapter);

    }

    private void initData() {
        students= new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student5_2 student = new Student5_2();
            student.setName("张"+(i+1));
            student.setAge(20+i);
            student.setClassmate("软件2118");
            students.add(student);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.item_add){
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}