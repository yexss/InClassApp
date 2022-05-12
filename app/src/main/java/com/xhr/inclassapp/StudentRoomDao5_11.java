package com.xhr.inclassapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentRoomDao5_11 {

    @Query("select * from stu_room")
    List<Student5_11Entity> getAll();

//    @Query("select * from stu_room where student_name like '%'||:kw||'%' or classmate like '%'||:kw||'%'")
//    List<Student5_11Entity> getAll(String kw);


    @Insert
    void insert(Student5_11Entity student);

    @Update
    void update(Student5_11Entity student);

    @Query("delete from stu_room where id = :id")
    void delete(int id);

    @Delete
    void delete(Student5_11Entity student);

    //    @Query("SELECT * FROM stu_room WHERE id IN (:userIds)")
    //    List<User> loadAllByIds(int[] userIds);
    //
    //    @Query("SELECT * FROM stu_room WHERE student_name LIKE :name ")
    //    User findByName(String name);
    //
    //    @Insert
    //    void insertAll(User... users);

}
