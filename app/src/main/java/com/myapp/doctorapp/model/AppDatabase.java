package com.myapp.doctorapp.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Doctor.class, Patient.class, MedicalHistory.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DoctorDao doctorDao();
    public abstract PatientDao patientDao();
    public abstract MedicalHistoryDao medicalHistoryDao();
}
