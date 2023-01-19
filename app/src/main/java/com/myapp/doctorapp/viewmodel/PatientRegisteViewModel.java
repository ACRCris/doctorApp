package com.myapp.doctorapp.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.doctorapp.model.AppDatabase;
import com.myapp.doctorapp.model.DatabaseHelper;
import com.myapp.doctorapp.model.Doctor;
import com.myapp.doctorapp.model.DoctorDao;
import com.myapp.doctorapp.model.MyDate;
import com.myapp.doctorapp.model.Patient;
import com.myapp.doctorapp.model.PatientDao;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class PatientRegisteViewModel extends ViewModel implements WriteDatabase<Patient>, UpdateRegister<Patient>, ReadDatabase {

    private MutableLiveData<String> name;
    private MutableLiveData<String> surname;
    private MutableLiveData<String> ID;
    private MutableLiveData<MyDate> birthDate;
    private MutableLiveData<MyDate> doctorAppointment;
    private MutableLiveData<Doctor> doctor;
    private MutableLiveData<Boolean> inHealing;
    private MutableLiveData<Double> scaleFee;
    private MutableLiveData<List<String>> doctorsProfessionalCard;

    public MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<String>();
        }
        return name;
    }

    public MutableLiveData<String> getSurname() {
        if (surname == null) {
            surname = new MutableLiveData<String>();
        }
        return surname;
    }

    public MutableLiveData<String> getID() {
        if (ID == null) {
            ID = new MutableLiveData<String>();
        }
        return ID;
    }

    public MutableLiveData<MyDate> getBirthDate() {
        if (birthDate == null) {
            birthDate = new MutableLiveData<>();
        }
        return birthDate;
    }

    public MutableLiveData<MyDate> getDoctorAppointment() {
        if (doctorAppointment == null) {
            doctorAppointment = new MutableLiveData<>();
        }
        return doctorAppointment;
    }

    public MutableLiveData<Doctor> getDoctor() {
        if (doctor == null) {
            doctor = new MutableLiveData<>();
        }
        return doctor;
    }

    public MutableLiveData<Boolean> getInHealing() {
        if (inHealing == null) {
            inHealing = new MutableLiveData<Boolean>();
        }
        return inHealing;
    }

    public MutableLiveData<Double> getScaleFee() {
        if (scaleFee == null) {
            scaleFee = new MutableLiveData<Double>();
        }
        return scaleFee;
    }

    public MutableLiveData<List<String>> getDoctorsProfessionalCard() {
        if (doctorsProfessionalCard == null) {
            doctorsProfessionalCard = new MutableLiveData<>();
        }
        return doctorsProfessionalCard;
    }


    @Override
    public void write(Context context, Patient model) {
        Runnable runnable = () -> {
            AppDatabase db = DatabaseHelper.getDB(context);

            PatientDao patientDao = db.patientDao();
            patientDao.insertAll(model);
        };

        Thread thread = new Thread(runnable);
        thread.start();

    }

    @Override
    public void updateRegister(Context context, Patient model) {
        Runnable runnable = () -> {
            AppDatabase db = DatabaseHelper.getDB(context);

            PatientDao patientDao = db.patientDao();
            patientDao.update(model);
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void read(Context context) {
        Future<List<String>> future = Executors.newSingleThreadExecutor().submit(() -> {
            synchronized (this) {
                AppDatabase db = DatabaseHelper.getDB(context);
                DoctorDao doctorDao = db.doctorDao();
                return doctorDao.getAllProfessionalCard();
            }
        });

        Runnable runnable = () -> {
            try {
                doctorsProfessionalCard.postValue(  future.get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

    }
}
