package com.example.jetpack2exercise;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class UserRepository {

    private UserDao mUserDao;
    private LiveData<List<User>> mAddUser;

    UserRepository(Application application){
        UserDatabase database = UserDatabase.getDatabase(application);
        mUserDao = database.userDao();
        mAddUser = mUserDao.getAllUsers();
    }

    LiveData<List<User>> getmAddUser(){
        return mAddUser;
    }

    void insert(final User user){
        UserDatabase.databaseWriteExecutor.execute(()->{
            mUserDao.InsertUser(user);
        });
    }
}
