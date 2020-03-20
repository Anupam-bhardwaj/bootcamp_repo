package com.example.webservicessession.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static DataService service;
    private static final String BASE_URL = "https://storage.googleapis.com/network-security-conf-codelab.appspot.com/v2/";

    public static Retrofit getRetrofitInstance(){

        if (retrofit == null){

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static DataService getDataService() {
        if (service == null) {
            service = getRetrofitInstance().create(DataService.class);

        }
        return service;
    }

}
