package com.myapp.doctorapp.model;

import android.content.Context;

import androidx.room.Room;

public class DatabaseHelper {

    public static AppDatabase getDB(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "MedicalData.db").build();
    }
}
