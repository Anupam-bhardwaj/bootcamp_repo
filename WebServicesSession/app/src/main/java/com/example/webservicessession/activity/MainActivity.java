package com.example.webservicessession.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.webservicessession.Model.Data;
import com.example.webservicessession.Model.RetroData;
import com.example.webservicessession.R;
import com.example.webservicessession.adapter.CustomAdaptor;
import com.example.webservicessession.network.DataService;
import com.example.webservicessession.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button download;

    CustomAdaptor adapter;

    private List<RetroData> dataList = new ArrayList<>();

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
                adapter = new CustomAdaptor(dataList, MainActivity.this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                dataRequest();
                recyclerView.setAdapter(adapter);

            }
        });
    }

    private void dataRequest() {
        Call<Data> call= RetrofitClientInstance.getDataService().getAllData();
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (response.body()!= null){
                    progressDialog.dismiss();
                    Data data = response.body();
                    List<RetroData> retroData = data.getRetroDataList();
                    dataList.addAll(retroData);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Unable to Load Data...", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
