package com.myapp.doctorapp.model;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class MyDate {

        private int day = 0;
        private int month = 0;
        private int year  = 0;



        public MyDate() {
            Calendar mCalendar = Calendar.getInstance();
            year = mCalendar.get(Calendar.YEAR);
            month = mCalendar.get(Calendar.MONTH)+1;
            day = mCalendar.get(Calendar.DAY_OF_MONTH);

        }
        public MyDate(String date){
            if(!date.equals("")) {
                String[] dateParts = date.split("-");
                day = Integer.parseInt(dateParts[0]);
                month = Integer.parseInt(dateParts[1]);
                year = Integer.parseInt(dateParts[2]);
            }
        }

        public void setDate(int day, int month, int year){
            this.day = day;
            this.month = month;
            this.year = year;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        @NonNull
        @Override
        public String toString() {
            if(day == 0 || month == 0 || year == 0){
                return "";
            }
            return day + "-" + month + "-" + year;
        }

        public Date toDate(){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                return Date.from(LocalDate.of(year, month, day).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            else
                return new Date();
        }
}
