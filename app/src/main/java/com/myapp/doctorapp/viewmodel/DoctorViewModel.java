package com.myapp.doctorapp.viewmodel;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.slider.BaseOnChangeListener;
import com.myapp.doctorapp.model.ApiHelper;
import com.myapp.doctorapp.model.AppDatabase;
import com.myapp.doctorapp.model.DatabaseHelper;
import com.myapp.doctorapp.model.Doctor;
import com.myapp.doctorapp.model.DoctorDao;
import com.myapp.doctorapp.model.DoctorEndPoint;
import com.myapp.doctorapp.model.MedicalHistoryDao;
import com.myapp.doctorapp.model.MyResponse;
import com.myapp.doctorapp.model.PatientDao;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Response;

public class DoctorViewModel extends ViewModel implements ReadDatabase, DeleteRegister<Doctor> {

    private MutableLiveData<List<Doctor>> doctors = new MutableLiveData<>();
    private MutableLiveData<Boolean> isRead = new MutableLiveData<>();
    private MutableLiveData<Boolean> canDeleted = new MutableLiveData<>();
    private MutableLiveData<Boolean> havePatients = new MutableLiveData<>();
    private MutableLiveData<Boolean> finishDeleteProcess = new MutableLiveData<>();


    public MutableLiveData<List<Doctor>> getDoctors() {
        if (doctors == null) {
            doctors = new MutableLiveData<>();
        }
        return doctors;
    }

    public MutableLiveData<Boolean> getIsRead() {
        if (isRead == null) {
            isRead = new MutableLiveData<>();
        }
        return isRead;
    }

    public MutableLiveData<Boolean> getCanDeleted() {
        if (canDeleted == null) {
            canDeleted = new MutableLiveData<>();
        }
        return canDeleted;
    }

    public MutableLiveData<Boolean> getHavePatients() {
        if (havePatients == null) {
            havePatients = new MutableLiveData<>();
        }
        return havePatients;
    }

    public MutableLiveData<Boolean> getFinishDeleteProcess() {
        if (finishDeleteProcess == null) {
            finishDeleteProcess = new MutableLiveData<>();
        }
        return finishDeleteProcess;
    }



    @Override
    public void read(Context context)  {
        Runnable runnable = () -> {

            Future<List<Doctor>> future = Executors.newSingleThreadExecutor().submit(() -> {
                synchronized (this) {
                    wait(500);
                    AppDatabase db = DatabaseHelper.getDB(context);
                    return db.doctorDao().getAll();
                }
            });

            try {
                doctors.postValue(future.get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            isRead.postValue(true);



        };

        Thread thread = new Thread(runnable);
        thread.start();


    }

    public void verifyDelete(Context context,Doctor model){
        Future<Boolean> futurePatient = Executors.newSingleThreadExecutor().submit(() -> {
            synchronized (this) {
                AppDatabase db = DatabaseHelper.getDB(context);
                PatientDao patientDao = db.patientDao();
                return patientDao.findByProfessionalCard(model.getProfessionalCard()).isEmpty();

            }
        });

        Future<Boolean> futureHistory =  Executors.newSingleThreadExecutor().submit(() -> {
            synchronized (this) {
                AppDatabase db = DatabaseHelper.getDB(context);
                MedicalHistoryDao medicalHistoryDao = db.medicalHistoryDao();
                return medicalHistoryDao.findByDoctorProfessionalCard(model.getProfessionalCard()).isEmpty();

            }
        });

        Runnable runnable2 = () -> {
            boolean havePatient = false;
            boolean haveHistory = false;
            boolean canDelete = false;
            try {
                havePatient = !futurePatient.get();
                haveHistory = !futureHistory.get();
                canDelete = havePatient && haveHistory;
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            if (canDelete) {
                delete(context, model);
            }else if(!havePatient) {
                havePatients.postValue(false);
                finishDeleteProcess.postValue(true);

            }else  {
                finishDeleteProcess.postValue(true);
                canDeleted.postValue(false);
                havePatients.postValue(true);

            }
        };

        Thread thread2 = new Thread(runnable2);
        thread2.start();

    }

    public void delete(Context context, Doctor model) {
        Runnable runnable = () -> {
            AppDatabase db = DatabaseHelper.getDB(context);

            DoctorDao doctorDao = db.doctorDao();
            doctorDao.delete(model);
            MedicalHistoryDao medicalHistoryDao = db.medicalHistoryDao();
            medicalHistoryDao.deleteByDoctorProfessionalCard(model.getProfessionalCard());
            finishDeleteProcess.postValue(true);
            canDeleted.postValue(true);
            isRead.postValue(false);

        };




        Thread thread = new Thread(runnable);
        thread.start();
    }


}



