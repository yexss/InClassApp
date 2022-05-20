package com.xhr.inclassapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SpinnerAdapter;

import com.xhr.inclassapp.databinding.ActivityStudentRoomUpdate512Binding;
import com.xhr.inclassapp.databinding.ActivityStudentUpdate52Binding;

public class StudentRoomUpdate5_12Activity extends AppCompatActivity {

    private ActivityStudentRoomUpdate512Binding binding;
    private Student5_11Entity currentStudent;
    private boolean isUpdate = false;
    private StudentRepository5_11 studentRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentRoomUpdate512Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        studentRepository=new StudentRepository5_11(getApplication());

        final Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        if (bundle != null) {
            currentStudent = (Student5_11Entity) bundle.get("student");
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
            Student5_11Entity student = new Student5_11Entity();
            student.setName(binding.etName.getText().toString());
            student.setAge(Integer.parseInt(binding.etAge.getText().toString()));
            student.setClassmate(binding.spClassmate.getSelectedItem().toString());

            if (isUpdate) {
                // setId
                student.setId(currentStudent.getId());
                studentRepository.update(student);
            } else {
                studentRepository.insert(student);
            }

            final Intent backIntent = new Intent();
            final Bundle backBundle = new Bundle();
            backBundle.putSerializable("student", student);
            backIntent.putExtras(backBundle);
            backIntent.putExtra("flag", isUpdate);
            setResult(RESULT_OK, backIntent);
            finish();
        });

        binding.btnCancel.setOnClickListener(view->{
            finish();
        });

    }
}