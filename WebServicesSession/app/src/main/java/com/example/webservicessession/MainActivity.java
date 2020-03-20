package com.example.webservicessession;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button download;

    CustomAdaptor adapter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        download = findViewById(R.id.downloadBtn);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();
                DataService dataService = RetrofitClientInstance.getRetrofitInstance().create(DataService.class);
                Call<List<RetroData>> call = dataService.getAllData();
                call.enqueue(new Callback<List<RetroData>>() {
                    @Override
                    public void onResponse(Call<List<RetroData>> call, Response<List<RetroData>> response) {

                        createDataList(response.body());
                        progressDialog.dismiss();

                    }

                    @Override
                    public void onFailure(Call<List<RetroData>> call, Throwable t) {

                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Unable To Load Data...", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

    }

//    private void loadDataRequest() {
//
//
//    }

    private void createDataList(List<RetroData> body) {

        adapter = new CustomAdaptor(body, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

}
