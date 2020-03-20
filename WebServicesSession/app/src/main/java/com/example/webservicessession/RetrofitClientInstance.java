package com.example.webservicessession;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://storage.googleapis.com/network-security-conf-codelab.appspot.com/v2/";

    public static Retrofit getRetrofitInstance(){

        if (retrofit == null){
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

            retrofit = builder.build();
        }

        return retrofit;
    }

}
