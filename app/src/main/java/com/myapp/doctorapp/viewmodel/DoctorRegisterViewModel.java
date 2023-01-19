package com.myapp.doctorapp.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.doctorapp.model.AppDatabase;
import com.myapp.doctorapp.model.DatabaseHelper;
import com.myapp.doctorapp.model.Doctor;
import com.myapp.doctorapp.model.DoctorDao;

public class DoctorRegisterViewModel extends ViewModel  implements WriteDatabase<Doctor>, UpdateRegister<Doctor> {
    private MutableLiveData<String> name;
    private MutableLiveData<String> surname;
    private MutableLiveData<String> professionalCard;
    private MutableLiveData<String> speciality;
    private MutableLiveData<String> clinic;
    private MutableLiveData<Integer> experience;
    private MutableLiveData<Boolean> availableHomeCare;

    public MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<>();
        }
        return name;
    }

    public MutableLiveData<String> getSurname() {
        if (surname == null) {
            surname = new MutableLiveData<>();
        }
        return surname;
    }

    public MutableLiveData<String> getProfessionalCard() {
        if (professionalCard == null) {
            professionalCard = new MutableLiveData<>();
        }
        return professionalCard;
    }

    public MutableLiveData<String> getSpeciality() {
        if (speciality == null) {
            speciality = new MutableLiveData<>();
        }
        return speciality;
    }

    public MutableLiveData<String> getClinic() {
        if (clinic == null) {
            clinic = new MutableLiveData<>();
        }
        return clinic;
    }

    public MutableLiveData<Integer> getExperience() {
        if (experience == null) {
            experience = new MutableLiveData<>();
        }
        return experience;
    }

    public MutableLiveData<Boolean> getAvailableHomeCare() {
        if (availableHomeCare == null) {
            availableHomeCare = new MutableLiveData<>();
        }
        return availableHomeCare;
    }


    //Connect with database in the model
    @Override
    public void write(Context context, Doctor model) {
        Runnable runnable = () -> {
            AppDatabase db = DatabaseHelper.getDB(context);

            DoctorDao doctorDao = db.doctorDao();
            doctorDao.insertAll(model);
        };

        Thread thread = new Thread(runnable);
        thread.start();

    }

    @Override
    public void updateRegister(Context context,Doctor model) {
        Runnable runnable = () -> {
            AppDatabase db = DatabaseHelper.getDB(context);

            DoctorDao doctorDao = db.doctorDao();
            doctorDao.update(model);
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }


}
