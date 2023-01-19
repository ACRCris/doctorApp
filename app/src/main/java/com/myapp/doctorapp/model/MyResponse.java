package com.myapp.doctorapp.model;

import com.google.gson.annotations.SerializedName;

public class MyResponse {
    @SerializedName("status")
    private final String status;

    public MyResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


}
