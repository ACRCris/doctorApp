package com.myapp.doctorapp.viewmodel;

import android.content.Context;
import android.util.Log;

import com.myapp.doctorapp.model.ApiHelper;
import com.myapp.doctorapp.model.AppDatabase;
import com.myapp.doctorapp.model.DatabaseHelper;
import com.myapp.doctorapp.model.Doctor;
import com.myapp.doctorapp.model.DoctorEndPoint;
import com.myapp.doctorapp.model.MedicalHistory;
import com.myapp.doctorapp.model.MedicalHistoryEndPoint;
import com.myapp.doctorapp.model.MyResponse;
import com.myapp.doctorapp.model.Patient;
import com.myapp.doctorapp.model.PatientEndPoint;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Response;

public class MainViewModel {

    public void sendDoctorsToServer(Context context) throws ExecutionException, InterruptedException {

        Future<List<Doctor>> futureDoctors = Executors.newSingleThreadExecutor().submit(() -> {
            AppDatabase db = DatabaseHelper.getDB(context);
            return db.doctorDao().getAll();
        });

        List<Doctor> doctors = futureDoctors.get();

        Log.d("doctors", doctors.toString());
        for (Doctor doctor : doctors) {

            DoctorEndPoint endPoint = ApiHelper.getRetrofitInstance().create(DoctorEndPoint.class);
            Call<MyResponse> call = endPoint.setDoctor(doctor.toString());
            try {
                Response<MyResponse> response = call.execute();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("TAG", "sendDoctorsToServer: " + response.body().getStatus());
                    } else
                        Log.d("TAG", "sendDoctorsToServer: null response");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void sendPatientsToServer(Context context) throws ExecutionException, InterruptedException {

        Future<List<Patient>> futurePatients = Executors.newSingleThreadExecutor().submit(() -> {
            AppDatabase db = DatabaseHelper.getDB(context);
            return db.patientDao().getAll();
        });

        List<Patient> patients = futurePatients.get();

        Log.d("patients", patients.toString());
        for (Patient patient : patients) {

            PatientEndPoint endPoint = ApiHelper.getRetrofitInstance().create(PatientEndPoint.class);
            Call<MyResponse> call = endPoint.setPatient(patient.toString());
            try {
                Response<MyResponse> response = call.execute();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("TAG", "sendPatientsToServer: " + response.body().getStatus());
                    } else
                        Log.d("TAG", "sendPatientsToServer: null response");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void sendMedicalHistoryToServer(Context context) throws ExecutionException, InterruptedException {

        Future<List<MedicalHistory>> futureMedicalHistory = Executors.newSingleThreadExecutor().submit(() -> {
            AppDatabase db = DatabaseHelper.getDB(context);
            return db.medicalHistoryDao().getAll();
        });

        List<MedicalHistory> medicalHistories = futureMedicalHistory.get();

        Log.d("medicalHistory", medicalHistories.toString());
        for (MedicalHistory medicalHistory : medicalHistories) {

            MedicalHistoryEndPoint endPoint = ApiHelper.getRetrofitInstance().create(MedicalHistoryEndPoint.class);
            Call<MyResponse> call = endPoint.setMedicalHistory(medicalHistory.toString());
            try {
                Response<MyResponse> response = call.execute();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("TAG", "sendMedicalHistoryToServer: " + response.body().getStatus());
                    } else
                        Log.d("TAG", "sendMedicalHistoryToServer: null response");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }




    public void connectRetrofit(Context context) {



        Runnable runnable = ()->{

            try {
                sendDoctorsToServer(context);
                sendPatientsToServer(context);
                sendMedicalHistoryToServer(context);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

        };


        Runnable execute = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (this) {
                    try {
                        Thread thread = new Thread(runnable);
                        thread.start();
                        wait(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread = new Thread(execute);
        thread.start();




    }

}
