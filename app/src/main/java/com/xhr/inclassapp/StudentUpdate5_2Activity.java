package com.xhr.inclassapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SpinnerAdapter;

import com.xhr.inclassapp.databinding.ActivityStudentUpdate52Binding;

public class StudentUpdate5_2Activity extends AppCompatActivity {

    private ActivityStudentUpdate52Binding binding;
    private Student5_2 currentStudent;
    private boolean isUpdate = false;
    private StudentDAO5_9 studentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentUpdate52Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        studentDAO = new StudentDAO5_9(this);

        final Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        if (bundle != null) {
            currentStudent = (Student5_2) bundle.get("student");
        }

        if (currentStudent != null) {
            isUpdate = true;
            binding.etName.setText(currentStudent.getName());
            binding.etAge.setText(String.valueOf(currentStudent.getAge()));

            SpinnerAdapter spinnerAdapter = binding.spClassmate.getAdapter();
            for (int i = 0; i < spinnerAdapter.getCount(); i++) {
                if (spinnerAdapter.getItem(i).toString().equals(currentStudent.getClassmate())) {
                    binding.spClassmate.setSelection(i);
                    break;
                }
            }
        }

        binding.btnConfirm.setOnClickListener(v -> {
            Student5_2 student = new Student5_2();
            student.setName(binding.etName.getText().toString());
            student.setAge(Integer.parseInt(binding.etAge.getText().toString()));
            student.setClassmate(binding.spClassmate.getSelectedItem().toString());

            if (isUpdate) {
                // setId
                student.setId(currentStudent.getId());
                studentDAO.update(student);
            } else {
                int id = studentDAO.insert(student);
                student.setId(id);
            }

            final Intent backIntent = new Intent();
            final Bundle backBundle = new Bundle();
            backBundle.putSerializable("student", student);
            backIntent.putExtras(backBundle);
            backIntent.putExtra("flag", isUpdate);
            setResult(RESULT_OK, backIntent);
            finish();
        });

    }
}