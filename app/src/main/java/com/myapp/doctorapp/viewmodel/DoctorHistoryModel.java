package com.myapp.doctorapp.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.doctorapp.model.AppDatabase;
import com.myapp.doctorapp.model.DatabaseHelper;
import com.myapp.doctorapp.model.MedicalHistory;
import com.myapp.doctorapp.model.Patient;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DoctorHistoryModel extends ViewModel implements ReadDatabase, WriteDatabase<MedicalHistory>, UpdateRegister<Patient> {

    private final String doctorProfessionalCard;

    private MutableLiveData<List<Patient>> unResolvedPatients;
    private MutableLiveData<List<MedicalHistory>> resolvedPatients;

    private MutableLiveData<Boolean> isRead;


    private MutableLiveData<Boolean> showUnresolved;
    private MutableLiveData<Boolean> showResolved;

    public DoctorHistoryModel(String doctorProfessionalCard) {
        this.doctorProfessionalCard = doctorProfessionalCard;
    }

    public MutableLiveData<List<Patient>> getUnResolvedPatients() {
        if (unResolvedPatients== null) {
            unResolvedPatients = new MutableLiveData<>();
        }
        return unResolvedPatients;
    }

    public MutableLiveData<List<MedicalHistory>> getResolvedPatients() {
        if (resolvedPatients== null) {
            resolvedPatients = new MutableLiveData<>();
        }
        return resolvedPatients;
    }

    public MutableLiveData<Boolean> getIsRead() {
        if (isRead == null) {
            isRead = new MutableLiveData<>();
        }
        return isRead;
    }

    public MutableLiveData<Boolean> getShowUnresolved() {
        if (showUnresolved == null) {
            showUnresolved = new MutableLiveData<>();
        }
        return showUnresolved;
    }

    public MutableLiveData<Boolean> getShowResolved() {
        if (showResolved == null) {
            showResolved = new MutableLiveData<>();
        }
        return showResolved;
    }


    @Override
    public void read(Context context){

        Future<List<Patient>> future = Executors.newSingleThreadExecutor().submit(() -> {
            synchronized (this) {
                AppDatabase db = DatabaseHelper.getDB(context);
                return db.patientDao().findByProfessionalCard(doctorProfessionalCard);
            }
        });

        Future<List<MedicalHistory>> futureResolved= Executors.newSingleThreadExecutor().submit(() -> {
            synchronized (this) {
                AppDatabase db = DatabaseHelper.getDB(context);
                return db.medicalHistoryDao().findByDoctorProfessionalCard(doctorProfessionalCard);
            }
        });



        Runnable runnable = () -> {
            try {
                unResolvedPatients.postValue(future.get());
                resolvedPatients.postValue(futureResolved.get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            isRead.postValue(true);


        };

        Thread thread = new Thread(runnable);
        thread.start();




    }


    @Override
    public void write(Context context, MedicalHistory model) {
        Runnable runnable = () -> {
            AppDatabase db = DatabaseHelper.getDB(context);
            db.medicalHistoryDao().insertAll(model);
        };

        Thread thread = new Thread(runnable);
        thread.start();

    }

    @Override
    public void updateRegister(Context context, Patient model) {
        Runnable runnable = () -> {
            AppDatabase db = DatabaseHelper.getDB(context);
            db.patientDao().update(model);
        };

        Thread thread = new Thread(runnable);
        thread.start();


    }
}
