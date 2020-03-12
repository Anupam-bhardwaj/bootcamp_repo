package com.example.androidassignment7;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Dao
public interface EmployeeDao {

    @Query("Select * from Employee_table")
    List<Employee> getAllEmp();

    @Insert
    void insertEmp(Employee employee);

    @Delete
    void deleteEmp(Employee employee);

    @Update
    void update(Employee employee);
}
