package com.myapp.doctorapp.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientDao {

    @Query("SELECT * FROM patient")
    List<Patient> getAll();

    @Query("SELECT * FROM patient WHERE id IN (:patientIds)")
    List<Patient> loadAllByIds(int[] patientIds);

    @Query("SELECT * FROM patient WHERE identification IN (:identification)")
    List<Patient> findByIdentification(List<String> identification);


    @Query("SELECT * FROM patient WHERE doctor LIKE :professionalCard")
    List<Patient> findByProfessionalCard(String professionalCard);

    @Insert
    void insertAll(Patient... patients);

    @Update
    void update(Patient patient);

    @Delete
    void delete(Patient patient);

}
