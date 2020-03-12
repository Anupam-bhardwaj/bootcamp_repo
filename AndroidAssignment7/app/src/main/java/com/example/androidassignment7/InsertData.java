package com.example.androidassignment7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertData extends AppCompatActivity {

    private AppCompatEditText mName;
    private AppCompatEditText mAddress;
    private AppCompatEditText mNumber;
    private AppCompatButton mSubmit;

    private EmployeeDao employeeDao;

    String name, address, phone;

    boolean FOR_UPDATE =false;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        mName = (AppCompatEditText) findViewById(R.id.insertName);
        mAddress = (AppCompatEditText) findViewById(R.id.insertAddress);
        mNumber = (AppCompatEditText) findViewById(R.id.insertPhone);
        mSubmit = (AppCompatButton) findViewById(R.id.submitBtn);

        final EmployeeDatabase employeeDatabase = Room.databaseBuilder(getApplication(),
                EmployeeDatabase.class, "Employee_Database")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        employeeDao = employeeDatabase.employeeDao();

        final Employee employee = new Employee();

        final List<Employee> employeeList = employeeDatabase.employeeDao().getAllEmp();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               saveData();
            }
        });
        
        getData();
    }

    private void getData() {

        if (getIntent().hasExtra("record")){

            FOR_UPDATE = true;
            mSubmit.setText("Update");
            Employee employee = (Employee)getIntent().getSerializableExtra("record");
            mName.setText(employee.getmEmployeeName());
            mAddress.setText(employee.getmEnployeeAddress());
            mNumber.setText(employee.getmPhoneNumber());
            id= employee.getEmployeeId();

        }else {
            FOR_UPDATE = false;
            mSubmit.setText("Submit");
        }

    }

    private void saveData() {
        name = mName.getText().toString();
        address = mAddress.getText().toString();
        phone = mNumber.getText().toString();


        if (FOR_UPDATE == false){
            SaveEmployeeRecord saveEmployeeRecord = new SaveEmployeeRecord();
            saveEmployeeRecord.execute();
        }else {
            UpdateRecodTask updateRecodTask = new UpdateRecodTask();
            updateRecodTask.execute();
        }
    }

    class SaveEmployeeRecord extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            Employee employee = new Employee();
            employee.setmEmployeeName(name);
            employee.setmEnployeeAddress(address);
            employee.setmPhoneNumber(phone);


            EmployeeDatabase.getDatabase(getApplicationContext())
                    .employeeDao().insertEmp(employee);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Toast.makeText(getApplicationContext(), "Data Saved.....", Toast.LENGTH_SHORT).show();
        }
    }

    class UpdateRecodTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            Employee employee = new Employee();
            employee.setmEmployeeName(name);
            employee.setmEnployeeAddress(address);
            employee.setmPhoneNumber(phone);

            employee.setId(id);


            EmployeeDatabase.getDatabase(getApplicationContext())
                    .employeeDao().update(employee);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
            startActivity(new Intent(InsertData.this, MainActivity.class));
            Toast.makeText(getApplicationContext(), "Record Updated...", Toast.LENGTH_SHORT).show();

        }
    }
}