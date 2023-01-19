package com.myapp.doctorapp.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.doctorapp.model.AppDatabase;
import com.myapp.doctorapp.model.DatabaseHelper;
import com.myapp.doctorapp.model.Doctor;
import com.myapp.doctorapp.model.Patient;
import com.myapp.doctorapp.model.PatientDao;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PatientViewModel extends ViewModel implements ReadDatabase, DeleteRegister<Patient> {

    private MutableLiveData<List<Patient>> patients;
    private MutableLiveData<Boolean> isRead;

    public MutableLiveData<List<Patient>> getPatients() {
        if (patients == null) {
            patients = new MutableLiveData<>();
        }
        return patients;
    }

    public MutableLiveData<Boolean> getIsRead() {
        if (isRead == null) {
            isRead = new MutableLiveData<>();
        }
        return isRead;
    }


    @Override
    public void read(Context context) {

        Future<List<Patient>> future = Executors.newSingleThreadExecutor().submit(() -> {
            synchronized (this) {
                wait(500);
                AppDatabase db = DatabaseHelper.getDB(context);
                return db.patientDao().getAll();
            }
        });

        Runnable runnable = () -> {
            try {
                patients.postValue(future.get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            isRead.postValue(true);

        };

        Thread thread = new Thread(runnable);
        thread.start();



    }

    @Override
    public void delete(Context context, Patient model) {
        Runnable runnable = () -> {
            AppDatabase db = DatabaseHelper.getDB(context);

            PatientDao patientDao = db.patientDao();
            patientDao.delete(model);
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
