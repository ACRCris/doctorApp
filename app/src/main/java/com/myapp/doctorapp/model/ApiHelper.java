package com.myapp.doctorapp.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {

    public static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.9:3323/doctor/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
