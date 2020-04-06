package com.example.jetpack2exercise;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users_table")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int mId;

    @ColumnInfo(name = "user_name")
    public String mUserName;

    @ColumnInfo(name = "user_email")
    public String mUserEmail;

    @ColumnInfo(name = "user_phoneNumber")
    public String mUserPhoneNumber;

    @ColumnInfo(name = "user_desc")
    public String mUserDesc;


    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmUserEmail() {
        return mUserEmail;
    }

    public void setmUserEmail(String mUserEmail) {
        this.mUserEmail = mUserEmail;
    }

    public String getmUserPhoneNumber() {
        return mUserPhoneNumber;
    }

    public void setmUserPhoneNumber(String mUserPhoneNumber) {
        this.mUserPhoneNumber = mUserPhoneNumber;
    }

    public String getmUserDesc() {
        return mUserDesc;
    }

    public void setmUserDesc(String mUserDesc) {
        this.mUserDesc = mUserDesc;
    }

}
