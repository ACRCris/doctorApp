package com.myapp.doctorapp.viewmodel;

import android.content.Context;

public interface WriteDatabase<T> {
    void write( Context context, T model);
}
