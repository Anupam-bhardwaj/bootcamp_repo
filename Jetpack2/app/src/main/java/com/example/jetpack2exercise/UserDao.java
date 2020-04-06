package com.example.jetpack2exercise;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("Select * from users_table")
    LiveData<List<User>> getAllUsers();

    @Insert
    void InsertUser(User user);

}
