package com.xhr.inclassapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper5_9 extends SQLiteOpenHelper {

    public static final String T_STUDENT_NAME = "t_student";
    public final static String T_STUDENT = "" +
            "CREATE TABLE t_student(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "name TEXT ," +
            "classmate TEXT ," +
            "age INT)";
    private final static String DROP_T_STUDENT = "DROP TABLE IF EXISTS t_student";
    public static String DB_NAME = "student.db";
    public static int DB_VERSION = 1;


    public DBHelper5_9(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(T_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_T_STUDENT);
        onCreate(sqLiteDatabase);
    }
}
