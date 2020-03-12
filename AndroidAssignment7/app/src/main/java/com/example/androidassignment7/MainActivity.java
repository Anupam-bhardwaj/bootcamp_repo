package com.example.androidassignment7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    private static final int REQUEST_CODE = 10;
    private RecyclerView recyclerView;
    private MyAdapter adapter;

    private List<Employee> myAdapterList;
    private FloatingActionButton insertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAdapterList = new ArrayList<>();

        insertBtn = (FloatingActionButton) findViewById(R.id.insertBtn);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        getEmployeeDetails();

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertData.class);
                startActivity(intent);
            }
        });

    }

    private void getEmployeeDetails() {

        class GetEmployeeDataTask extends AsyncTask<Void, Void, List<Employee>> {
            @Override
            protected List<Employee> doInBackground(Void... voids) {
                List<Employee> employeeList = EmployeeDatabase
                        .getDatabase(getApplicationContext()).employeeDao().getAllEmp();

                return employeeList;
            }

            @Override
            protected void onPostExecute(List<Employee> employees) {
                super.onPostExecute(employees);

                recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(MainActivity.this, LinearLayout.VERTICAL, false));
                adapter = new MyAdapter(MainActivity.this, employees);
                recyclerView.setAdapter(adapter);
            }
        }

        GetEmployeeDataTask getEmployeeDataTask = new GetEmployeeDataTask();
        getEmployeeDataTask.execute();

    }
}
