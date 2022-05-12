package com.xhr.inclassapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.xhr.inclassapp.databinding.ActivitySqlite52Binding;
import com.xhr.inclassapp.databinding.Toolbar52layoutBinding;

import java.util.ArrayList;
import java.util.List;

public class SQLite5_2Activity extends AppCompatActivity {

    private StudentDAO5_9 studentDAO;
    private ActivitySqlite52Binding binding;
    private Toolbar52layoutBinding toolbarBinding;
    private List<Student5_2> students;
    private Student5_2Adapter adapter;
    //    private Student5_2 currentStudent;
    private int currentPos;
    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            this::changeData
    );

    private void changeData(ActivityResult result) {
        if (result != null && result.getResultCode() == RESULT_OK && result.getData() != null) {
            final Intent intent = result.getData();
            boolean isUpdate = intent.getBooleanExtra("flag", false);
            Student5_2 student = (Student5_2) intent.getExtras().get("student");
            if (isUpdate) {
                // 更新:修改选中位置student数据，并更新列表
                students.set(currentPos, student);
                adapter.notifyItemChanged(currentPos);
            } else {
                // 添加:在列表最后添加数据，并更新最后一行列表数据
                students.add(student);
                adapter.notifyItemChanged(students.size() - 1);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySqlite52Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        studentDAO = new StudentDAO5_9(this);

        // <merge>的binding方式
        toolbarBinding = Toolbar52layoutBinding.bind(binding.getRoot());
        setSupportActionBar(toolbarBinding.toolbar);

        // 初始化数据
        initData();

        // 初始化视图
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 添加分割线
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter = new Student5_2Adapter(students);
        adapter.setOnClickListener(view -> {
            // 获取选中的数据,并更新选中位置的背景色
            Student5_2Adapter.ViewHolder viewHolder = (Student5_2Adapter.ViewHolder) view.getTag();
            currentPos = viewHolder.getAdapterPosition();
            adapter.setCurrentIndex(currentPos);
            // 将选中位置的Student数据传递给StudentUpdateActivity
            final Intent intent = new Intent(SQLite5_2Activity.this, StudentUpdate5_2Activity.class);
            final Bundle bundle = new Bundle();
            bundle.putSerializable("student", students.get(currentPos));
            intent.putExtras(bundle);
            launcher.launch(intent);
        });
        binding.recyclerView.setAdapter(adapter);

    }

    private void initData() {
        students = new ArrayList<>();

        students = studentDAO.getAllStudent();
//        for (int i = 0; i < 5; i++) {
//            Student5_2 student = new Student5_2();
//            student.setName("Ye"+(i+1));
//            student.setAge(20+i);
//            student.setClassmate("软件2118");
//            students.add(student);
//        }
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
            final Intent intent = new Intent(SQLite5_2Activity.this, StudentUpdate5_2Activity.class);
            launcher.launch(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}