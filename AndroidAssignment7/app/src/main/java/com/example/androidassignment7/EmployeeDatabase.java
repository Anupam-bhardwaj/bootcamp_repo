package com.example.androidassignment7;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Employee.class}, version = 2)
public abstract class EmployeeDatabase extends RoomDatabase {

    public abstract EmployeeDao employeeDao();

    private static volatile EmployeeDatabase INSTANCE;

    Context context;

    static EmployeeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EmployeeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EmployeeDatabase.class, "word_database").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }




}
