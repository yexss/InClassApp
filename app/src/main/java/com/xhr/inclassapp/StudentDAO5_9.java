package com.xhr.inclassapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

// 定义t_student表的CRUD操作代码
public class StudentDAO5_9 {

    private final DBHelper5_9 dbHelper;

    public StudentDAO5_9(Context context) {
        this.dbHelper = new DBHelper5_9(context);
    }

    public int insert(Student5_2 student) {
        // 1.组装数据
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("classmate", student.getClassmate());
        values.put("age", student.getAge());

        // 2.获取数据库对象
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        // 3.插入
        long id = db.insert(DBHelper5_9.T_STUDENT_NAME, null, values);

        // 4.关闭
        db.close();

        return Math.toIntExact(id);
    }

    public void update(Student5_2 student) {
        // 1.组装数据
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("classmate", student.getClassmate());
        values.put("age", student.getAge());

        // 2.获取数据库对象
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        // 3.插入
        db.update(DBHelper5_9.T_STUDENT_NAME, values, "id=?", new String[]{String.valueOf(student.getId())});

        // 4.关闭
        db.close();
    }

    @SuppressLint("Range")
    public List<Student5_2> getAllStudent() {
        // 1.创建返回类型对象
        List<Student5_2> students = new ArrayList<>();

        // 2.获取数据库对象
        final SQLiteDatabase db = dbHelper.getReadableDatabase();

        // columns: 要查询的列名
        // selection: 查询条件
        // 3.执行查询语句
        final Cursor cursor = db.query(DBHelper5_9.T_STUDENT_NAME, null, null, null, null, null, null);

        // 4.解析表数据
        while (cursor.moveToNext()) {
            Student5_2 student = new Student5_2();

            student.setId(cursor.getInt(cursor.getColumnIndex("id")));
            student.setName(cursor.getString(cursor.getColumnIndex("name")));
            student.setClassmate(cursor.getString(cursor.getColumnIndex("classmate")));
            student.setAge(cursor.getInt(cursor.getColumnIndex("age")));

            students.add(student);
        }

        // 5.关闭资源
        cursor.close();
        db.close();

        // 6.返回结果
        return students;
    }

    @SuppressLint("Range")
    public List<Student5_2> getAllStudent(String kw) {
        // 1.创建
        List<Student5_2> students = new ArrayList<>();

        final SQLiteDatabase db = dbHelper.getReadableDatabase();

        String condition = "name like ? or classmate like ? or age like ?";
        String[] conditionArgs = new String[]{
                "%" + kw + "%", "%" + kw + "%", "%" + kw + "%",
        };


        final Cursor cursor = db.query(DBHelper5_9.T_STUDENT_NAME, null, condition, conditionArgs, null, null, null);

        while (cursor.moveToNext()) {
            Student5_2 student = new Student5_2();

            student.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            student.setName(cursor.getString(cursor.getColumnIndex("name")));
            student.setClassmate(cursor.getString(cursor.getColumnIndex("classmate")));
            student.setAge(cursor.getInt(cursor.getColumnIndex("age")));

            students.add(student);
        }

        cursor.close();
        db.close();

        return students;
    }

    public boolean deleteStudent(Student5_2 student) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = db.delete(DBHelper5_9.T_STUDENT_NAME, "id=?", new String[]{String.valueOf(student.getId())});
        return result > 0;
    }
}
