package com.myapp.doctorapp.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MedicalHistoryDao {

    @Query("SELECT * FROM medicalhistory")
    List<MedicalHistory> getAll();

    @Query("SELECT * FROM medicalhistory WHERE id IN (:medicalHistoryIds)")
    List<MedicalHistory> loadAllByIds(int[] medicalHistoryIds);

    @Query("SELECT * FROM medicalhistory WHERE doctor_professional_card LIKE :doctorProfessionalCard" )
    List<MedicalHistory> findByDoctorProfessionalCard(String doctorProfessionalCard);

    @Query("SELECT * FROM medicalhistory WHERE patient_identification LIKE :patientId")
    List<MedicalHistory> findByPatientId(String patientId);

    @Query("SELECT * FROM medicalhistory WHERE patient_identification LIKE :patientId AND is_accepted = 1")
    List<MedicalHistory> findByAcceptedPatient(String patientId);

    @Insert
    void insertAll(MedicalHistory... medicalHistories);

    @Query("DELETE FROM medicalhistory WHERE doctor_professional_card LIKE :doctor_professionalCard")
    void deleteByDoctorProfessionalCard(String doctor_professionalCard);

    @Delete
    void delete(MedicalHistory medicalHistory);

    @Update
    void update(MedicalHistory medicalHistory);

}
