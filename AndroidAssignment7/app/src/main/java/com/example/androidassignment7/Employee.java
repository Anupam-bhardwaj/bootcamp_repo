package com.example.androidassignment7;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Employee_table")
public class Employee implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int employeeId;

    @ColumnInfo(name = "employee_name")
    public String mEmployeeName;

    @ColumnInfo(name = "address")
    public String mEnployeeAddress;

    @ColumnInfo(name = "phoneNumber")
    public String mPhoneNumber;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setId(int id) {
        this.employeeId = id;
    }

    public String getmEmployeeName() {
        return mEmployeeName;
    }

    public void setmEmployeeName(String mEmployeeName) {
        this.mEmployeeName = mEmployeeName;
    }

    public String getmEnployeeAddress() {
        return mEnployeeAddress;
    }

    public void setmEnployeeAddress(String mEnployeeAddress) {
        this.mEnployeeAddress = mEnployeeAddress;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }


}
