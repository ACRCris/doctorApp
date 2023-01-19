package com.myapp.doctorapp.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MedicalHistoryEndPoint {
    @GET("history/set_history")
    Call<MyResponse> setMedicalHistory(@Query("history") String medicalHistory);
}
