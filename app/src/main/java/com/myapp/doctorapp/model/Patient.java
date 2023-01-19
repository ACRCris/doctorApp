package com.myapp.doctorapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class Patient {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private final String name;

    @ColumnInfo(name = "surname")
    private final String surname;

    @ColumnInfo(name = "identification")
    private final String identification;


    @ColumnInfo(name = "birth_date")
    private final String birthDate;

    @ColumnInfo(name = "doctor_appointment")
    private String doctorAppointment;

    @ColumnInfo(name = "doctor")
    private final String doctor;

    @ColumnInfo(name = "in_health_care")
    private final boolean inHealing;

    @ColumnInfo(name = "scale_fee")
    private final double scaleFee;

    public Patient(int id, String name, String surname, String identification, String birthDate, String doctorAppointment, String doctor, boolean inHealing, double scaleFee) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.identification = identification;
        this.birthDate = birthDate;
        this.doctorAppointment = doctorAppointment;
        this.doctor = doctor;
        this.inHealing = inHealing;
        this.scaleFee = scaleFee;
    }


    public Patient(String name, String surname, String identification, String birthDate, String doctorAppointment, String doctor, boolean inHealing, double moderatorFee) {
        this.name = name;
        this.surname = surname;
        this.identification = identification;
        this.birthDate = birthDate;
        this.doctorAppointment = doctorAppointment;
        this.doctor = doctor;
        this.inHealing = inHealing;
        this.scaleFee = moderatorFee;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getIdentification() {
        return identification;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getDoctorAppointment() {
        return doctorAppointment;
    }

    public String getDoctor() {
        return doctor;
    }

    public boolean isInHealing() {
        return inHealing;
    }

    public double getScaleFee() {
        return scaleFee;
    }

    public void setDoctorAppointment(String doctorAppointment) {
        this.doctorAppointment = doctorAppointment;
    }

    @Override
    public String toString(){
        return "{" +
                " \"id\": \""+ id + "\"" +
                ", \"name\": \""+ name + "\"" +
                ", \"surname\": \""+ surname + "\"" +
                ", \"identification\": \""+ identification + "\"" +
                ", \"birthDate\": \""+ birthDate + "\"" +
                ", \"doctorAppointment\": \""+ doctorAppointment + "\"" +
                ", \"doctor\": \""+ doctor + "\"" +
                ", \"inHealing\": \""+ inHealing + "\"" +
                ", \"scaleFee\": \""+ scaleFee + "\"" +
                "}";
    }





    public static Patient valueOf(String doctor) throws JSONException {
        JSONObject jsonObject = jsonStringToJsonObject(doctor);
        return new Patient(
                jsonObject.getInt("id"),
                jsonObject.getString("name"),
                jsonObject.getString("surname"),
                jsonObject.getString("identification"),
                jsonObject.getString("birthDate"),
                jsonObject.getString("doctorAppointment"),
                jsonObject.getString("doctor"),
                jsonObject.getBoolean("inHealing"),
                jsonObject.getDouble("scaleFee"));

    }

    public static JSONObject jsonStringToJsonObject(String patient) throws JSONException {
        JSONObject jsonObject = new JSONObject(patient);
        return jsonObject;
    }

}
