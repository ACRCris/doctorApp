package com.myapp.doctorapp.viewmodel;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class SignatureViewModel extends DoctorHistoryModel{

    public SignatureViewModel(String doctorProfessionalCard) {
        super(doctorProfessionalCard);
    }

    public byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}
