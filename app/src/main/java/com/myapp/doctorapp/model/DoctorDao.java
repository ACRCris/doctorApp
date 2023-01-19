package com.myapp.doctorapp.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DoctorDao {

    @Query("SELECT * FROM doctor")
    List<Doctor> getAll();

    @Query("SELECT * FROM doctor WHERE id IN (:doctorIds)")
    List<Doctor> loadAllByIds(int[] doctorIds);

    @Query("SELECT * FROM doctor WHERE professional_card LIKE :professionalCard LIMIT 1")
    Doctor findByProfessionalCard(String professionalCard);

    @Query("SELECT professional_card FROM doctor")
    List<String> getAllProfessionalCard();

    @Insert
    void insertAll(Doctor... doctors);

    @Delete
    void delete(Doctor doctor);

    @Update
    void update(Doctor doctor);

}
