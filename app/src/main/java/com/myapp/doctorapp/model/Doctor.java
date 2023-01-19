package com.myapp.doctorapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;


@Entity
public class Doctor {
    //@SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "name")
    //@SerializedName("name")
    private String name;

    @ColumnInfo(name = "surname")
    //@SerializedName("surname")
    private String surname;

    @ColumnInfo(name = "professional_card")
    //@SerializedName("professional_card")
    private final String professionalCard;

    @ColumnInfo(name = "speciality")
    //@SerializedName("speciality")
    private String speciality;

    @ColumnInfo(name = "clinic")
    //@SerializedName("clinic")
    private String clinic;

    @ColumnInfo(name = "experience")
    //@SerializedName("experience")
    private int experience;

    @ColumnInfo(name = "available_home_care")
    //@SerializedName("available_home_care")
    private boolean availableHomeCare;

    @Ignore
    public Doctor(String professionalCard){
        this.professionalCard = professionalCard;
    }

    public Doctor(int id, String name, String surname, String professionalCard, String speciality, String clinic, int experience, boolean availableHomeCare) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.professionalCard = professionalCard;
        this.speciality = speciality;
        this.clinic = clinic;
        this.experience = experience;
        this.availableHomeCare = availableHomeCare;
    }

    @Ignore
    public Doctor(String name, String surname, String professionalCard, String speciality, String clinic, int experience, boolean availableHomeCare) {
        this.name = name;
        this.surname = surname;
        this.professionalCard = professionalCard;
        this.speciality = speciality;
        this.clinic = clinic;
        this.experience = experience;
        this.availableHomeCare = availableHomeCare;
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

    public String getSpeciality() {
        return speciality;
    }

    public String getClinic() {
        return clinic;
    }

    public int getExperience() {
        return experience;
    }

    public boolean isAvailableHomeCare() {
        return availableHomeCare;
    }

    public String getProfessionalCard() {
        return professionalCard;
    }

    @Override
    public String toString(){
        return "{" +
                " \"id\": \""+ id + "\"" +
                ", \"name\": \""+ name + "\"" +
                ", \"surname\": \""+ surname + "\"" +
                ", \"professionalCard\": \""+ professionalCard + "\"" +
                ", \"speciality\": \""+ speciality + "\"" +
                ", \"clinic\": \""+ clinic + "\"" +
                ", \"experience\": \""+ experience + "\"" +
                ", \"availableHomeCare\": \""+ availableHomeCare + "\"" +
                "}";
    }


    public static Doctor valueOf(String doctor) throws JSONException {
        JSONObject jsonObject = jsonStringToJsonObject(doctor);
        return new Doctor(
                jsonObject.getInt("id"),
                jsonObject.getString("name"),
                jsonObject.getString("surname"),
                jsonObject.getString("professionalCard"),
                jsonObject.getString("speciality"),
                jsonObject.getString("clinic"),
                jsonObject.getInt("experience"),
                jsonObject.getBoolean("availableHomeCare")
        );

    }

    public static JSONObject jsonStringToJsonObject(String doctor) throws JSONException {
        JSONObject jsonObject = new JSONObject(doctor);
        return jsonObject;
    }







}
