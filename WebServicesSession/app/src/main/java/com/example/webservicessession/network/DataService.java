package com.example.webservicessession.network;

import com.example.webservicessession.Model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("posts.json")
    Call<Data>getAllData();
}
