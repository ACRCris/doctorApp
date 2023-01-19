package com.myapp.doctorapp.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PatientEndPoint {
    @GET("patient/set_patient")
    Call<MyResponse> setPatient(@Query("patient") String patient);
}
