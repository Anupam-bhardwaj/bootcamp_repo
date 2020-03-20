package com.example.webservicessession;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("posts.json")
    Call<List<RetroData>>getAllData();
}
