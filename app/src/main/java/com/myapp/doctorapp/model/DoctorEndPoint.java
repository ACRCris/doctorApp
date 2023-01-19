package com.myapp.doctorapp.model;

import android.hardware.lights.LightState;

import java.util.List;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface DoctorEndPoint {

    @GET("doctor/set_doctor")
    Call<MyResponse> setDoctor(@Query("doctor") String doctors);

}
