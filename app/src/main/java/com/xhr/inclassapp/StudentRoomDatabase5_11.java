package com.xhr.inclassapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Student5_11Entity.class}, version = 1, exportSchema = false)
public abstract class StudentRoomDatabase5_11 extends RoomDatabase {

    public static String DB_NAME="stu_info";
    private static volatile StudentRoomDatabase5_11 INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public abstract StudentRoomDao5_11 getStudentRoomDao();

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static StudentRoomDatabase5_11 getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StudentRoomDatabase5_11.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StudentRoomDatabase5_11.class, StudentRoomDatabase5_11.DB_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
