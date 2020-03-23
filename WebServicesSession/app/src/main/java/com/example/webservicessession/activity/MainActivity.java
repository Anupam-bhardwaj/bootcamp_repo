package com.example.webservicessession.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static boolean SCROLLER = false;
    RecyclerView recyclerView;
    Button download;
    Button download2;

    LinearLayoutManager layoutManager;
    CustomAdaptor adapter;

    private HttpsURLConnection httpURLConnection = null;
    private URL url;
    private BufferedReader reader = null;
    private List<RetroData> dataList = new ArrayList<>();

    String BASE_URL = "https://storage.googleapis.com/network-security-conf-codelab.appspot.com/v2/posts.json";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        download = findViewById(R.id.downloadBtn);
        download2 = findViewById(R.id.downloadBtn2);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");

        layoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                adapter = new CustomAdaptor(dataList, MainActivity.this);
                dataRequest();
                recyclerView.setAdapter(adapter);
            }
        });

        download2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                new DataDownload().execute(BASE_URL);
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

    public class DataDownload extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {

            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpsURLConnection)url.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = reader.readLine())!=null){

                    stringBuilder.append(line);

                }
                return stringBuilder.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            httpURLConnection.disconnect();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            Gson gson = new Gson();
            Data data = gson.fromJson(result, Data.class);
            adapter = new CustomAdaptor(data.getRetroDataList(), MainActivity.this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

}
