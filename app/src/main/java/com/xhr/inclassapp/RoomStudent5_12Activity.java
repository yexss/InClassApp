package com.xhr.inclassapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.xhr.inclassapp.databinding.ActivityRoomStudent512Binding;
import com.xhr.inclassapp.databinding.ActivitySqlite52Binding;
import com.xhr.inclassapp.databinding.Toolbar52layoutBinding;

import java.util.ArrayList;
import java.util.List;

public class RoomStudent5_12Activity extends AppCompatActivity {

    private ActivityRoomStudent512Binding binding;
    private Toolbar52layoutBinding toolbarBinding;
    private StudentRoom5_12Adapter adapter;
    private Student5_11Entity currentStudent;
    private int currentPos;
    private StudentRepository5_11 studentRepository;
    private List<Student5_11Entity> students;

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            this::changeData
    );

    @SuppressLint("NotifyDataSetChanged")
    private void changeData(ActivityResult result) {
        if (result != null && result.getResultCode() == RESULT_OK && result.getData() != null) {
            students.clear();
            students.addAll(studentRepository.getAll());
            adapter.notifyDataSetChanged();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomStudent512Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // <merge>的binding方式
        toolbarBinding = Toolbar52layoutBinding.bind(binding.getRoot());
        setSupportActionBar(toolbarBinding.toolbar);

        // 初始化数据
        initData();

        // 初始化视图
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 添加分割线
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter = new StudentRoom5_12Adapter(students);
        adapter.setOnClickListener(view -> {
            // 获取选中的数据,并更新选中位置的背景色
            StudentRoom5_12Adapter.ViewHolder viewHolder = (StudentRoom5_12Adapter.ViewHolder) view.getTag();
            currentPos = viewHolder.getAdapterPosition();
            adapter.setCurrentIndex(currentPos);
            // 将选中位置的Student数据传递给StudentUpdateActivity
            final Intent intent = new Intent(RoomStudent5_12Activity.this, StudentRoomUpdate5_12Activity.class);
            final Bundle bundle = new Bundle();
            bundle.putSerializable("student", students.get(currentPos));
            intent.putExtras(bundle);
            launcher.launch(intent);
        });

        adapter.setOnLongClickListener(view -> {
            StudentRoom5_12Adapter.ViewHolder viewHolder = (StudentRoom5_12Adapter.ViewHolder) view.getTag();
            currentPos = viewHolder.getAdapterPosition();
            adapter.setCurrentIndex(currentPos);
            studentRepository.delete(students.get(currentPos));
            students.remove(currentPos);
            adapter.notifyItemRemoved(currentPos);
            Toast.makeText(RoomStudent5_12Activity.this, "删除成功", Toast.LENGTH_SHORT).show();
            return true;
        });

        binding.recyclerView.setAdapter(adapter);

    }

    private void initData() {
        students = new ArrayList<>();
        studentRepository = new StudentRepository5_11(getApplication());
        students = studentRepository.getAll();
    }

    public List<Student5_11Entity> updateStudents(String kw) {
        return studentRepository.getAll(kw);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_student, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.item_search).getActionView();
        initSearchView(searchView);
        searchData(searchView);

        return super.onCreateOptionsMenu(menu);
    }

    private void searchData(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }


    private void initSearchView(SearchView searchView) {
        // 设置提交按钮可见
        searchView.setSubmitButtonEnabled(true);
        // 搜索框View,设置样式
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setHint("请输入搜索内容");
        searchEditText.setHintTextColor(getResources().getColor(R.color.darker_gray));
        searchEditText.setTextColor(getResources().getColor(R.color.white));
        searchEditText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        // 去掉搜索框默认下划线
        LinearLayout searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_plate);
        searchPlate.setBackground(null);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_add) {
            final Intent intent = new Intent(RoomStudent5_12Activity.this, StudentRoomUpdate5_12Activity.class);
            launcher.launch(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}