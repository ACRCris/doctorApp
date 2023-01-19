package com.myapp.doctorapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

@Entity
public class MedicalHistory {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "patient_id")
    private int patient_id;

    @ColumnInfo(name = "patient_name")
    private final String patient_name;

    @ColumnInfo(name = "patient_surname")
    private final String patient_surname;

    @ColumnInfo(name = "patient_identification")
    private final String patient_identification;

    @ColumnInfo(name = "patient_birth_date")
    private final String patient_birthDate;

    @ColumnInfo(name = "patient_doctor_appointment")
    private String patient_doctorAppointment;

    @ColumnInfo(name = "patient_doctor")
    private final String patient_doctor;

    @ColumnInfo(name = "patient_in_health_care")
    private final boolean patient_inHealing;

    @ColumnInfo(name = "patient_scale_fee")
    private final double patient_scaleFee;

    @ColumnInfo(name = "is_accepted")
    private boolean isAccepted;

    @ColumnInfo(name= "doctor_id")
    private int doctor_id;

    @ColumnInfo(name = "doctor_name")
    private String doctor_name;

    @ColumnInfo(name = "doctor_surname")
    private String doctor_surname;

    @ColumnInfo(name = "doctor_professional_card")
    private final String doctor_ProfessionalCard;

    @ColumnInfo(name = "doctor_speciality")
    private String doctor_speciality;

    @ColumnInfo(name = "clinic")
    private String doctor_clinic;

    @ColumnInfo(name = "doctor_experience")
    private int doctor_experience;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;


    public MedicalHistory(int id, int patient_id, String patient_name, String patient_surname, String patient_identification, String patient_birthDate, String patient_doctorAppointment, String patient_doctor, boolean patient_inHealing, double patient_scaleFee, boolean isAccepted, int doctor_id, String doctor_name, String doctor_surname, String doctor_ProfessionalCard, String doctor_speciality, String doctor_clinic, int doctor_experience, byte[] image) {
        this.id = id;
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.patient_surname = patient_surname;
        this.patient_identification = patient_identification;
        this.patient_birthDate = patient_birthDate;
        this.patient_doctorAppointment = patient_doctorAppointment;
        this.patient_doctor = patient_doctor;
        this.patient_inHealing = patient_inHealing;
        this.patient_scaleFee = patient_scaleFee;
        this.isAccepted = isAccepted;
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.doctor_surname = doctor_surname;
        this.doctor_ProfessionalCard = doctor_ProfessionalCard;
        this.doctor_speciality = doctor_speciality;
        this.doctor_clinic = doctor_clinic;
        this.doctor_experience = doctor_experience;
        this.image = image;
    }


    @Ignore
    public MedicalHistory(int patient_id, String patient_name, String patient_surname, String patient_identification, String patient_birthDate, String patient_doctorAppointment, String patient_doctor, boolean patient_inHealing, double patient_scaleFee, boolean isAccepted, int doctor_id, String doctor_name, String doctor_surname, String doctor_ProfessionalCard, String doctor_speciality, String doctor_clinic, int experience, byte[] image) {
        this.patient_name = patient_name;
        this.patient_surname = patient_surname;
        this.patient_identification = patient_identification;
        this.patient_birthDate = patient_birthDate;
        this.patient_doctorAppointment = patient_doctorAppointment;
        this.patient_doctor = patient_doctor;
        this.patient_inHealing = patient_inHealing;
        this.patient_scaleFee = patient_scaleFee;
        this.isAccepted = isAccepted;
        this.doctor_name = doctor_name;
        this.doctor_surname = doctor_surname;
        this.doctor_ProfessionalCard = doctor_ProfessionalCard;
        this.doctor_speciality = doctor_speciality;
        this.doctor_clinic = doctor_clinic;
        this.doctor_experience = experience;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.image = image;
    }



    public int getId() {
        return id;
    }

    public String getDoctor_ProfessionalCard() {
        return doctor_ProfessionalCard;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public String getPatient_surname() {
        return patient_surname;
    }

    public String getPatient_identification() {
        return patient_identification;
    }

    public String getPatient_birthDate() {
        return patient_birthDate;
    }

    public String getPatient_doctorAppointment() {
        return patient_doctorAppointment;
    }

    public String getPatient_doctor() {
        return patient_doctor;
    }

    public boolean isPatient_inHealing() {
        return patient_inHealing;
    }

    public double getPatient_scaleFee() {
        return patient_scaleFee;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public String getDoctor_surname() {
        return doctor_surname;
    }

    public String getDoctor_speciality() {
        return doctor_speciality;
    }

    public String getDoctor_clinic() {
        return doctor_clinic;
    }

    public int getDoctor_experience() {
        return doctor_experience;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @NonNull
    @Override
    public String toString(){
        return "{" +
                " \"id\": \""+ id + "\"" +
                ", \"patient_id\": \""+ patient_id + "\"" +
                ", \"patient_name\": \""+ patient_name + "\"" +
                ", \"patient_surname\": \""+ patient_surname + "\"" +
                ", \"patient_identification\": \""+ patient_identification + "\"" +
                ", \"patient_birthDate\": \""+ patient_birthDate + "\"" +
                ", \"patient_doctorAppointment\": \""+ patient_doctorAppointment + "\"" +
                ", \"patient_doctor\": \""+ patient_doctor + "\"" +
                ", \"patient_inHealing\": \""+ patient_inHealing + "\"" +
                ", \"patient_scaleFee\": \""+ patient_scaleFee + "\"" +
                ", \"isAccepted\": \""+ isAccepted + "\"" +
                ", \"doctor_id\": \""+ doctor_id + "\"" +
                ", \"doctor_name\": \""+ doctor_name + "\"" +
                ", \"doctor_surname\": \""+ doctor_surname + "\"" +
                ", \"doctor_ProfessionalCard\": \""+ doctor_ProfessionalCard + "\"" +
                ", \"doctor_speciality\": \""+ doctor_speciality + "\"" +
                ", \"doctor_clinic\": \""+ doctor_clinic + "\"" +
                ", \"doctor_experience\": \""+ doctor_experience + "\"" +
                ", \"image\": \""+ Arrays.toString(image) + "\"" +
                "}";

    }

    public static MedicalHistory valueOf(String medicalHistory) throws JSONException {
        JSONObject jsonObject = jsonStringToJsonObject(medicalHistory);

        return new MedicalHistory(
                jsonObject.getInt("id"),
                jsonObject.getInt("patient_id"),
                jsonObject.getString("patient_name"),
                jsonObject.getString("patient_surname"),
                jsonObject.getString("patient_identification"),
                jsonObject.getString("patient_birthDate"),
                jsonObject.getString("patient_doctorAppointment"),
                jsonObject.getString("patient_doctor"),
                jsonObject.getBoolean("patient_inHealing"),
                jsonObject.getDouble("patient_scaleFee"),
                jsonObject.getBoolean("isAccepted"),
                jsonObject.getInt("doctor_id"),
                jsonObject.getString("doctor_name"),
                jsonObject.getString("doctor_surname"),
                jsonObject.getString("doctor_ProfessionalCard"),
                jsonObject.getString("doctor_speciality"),
                jsonObject.getString("doctor_clinic"),
                jsonObject.getInt("doctor_experience"),
                jsonObject.getString("image").getBytes()
        );

    }

    public static JSONObject jsonStringToJsonObject(String doctor) throws JSONException {
        JSONObject jsonObject = new JSONObject(doctor);
        return jsonObject;
    }

}
