package com.myapp.doctorapp.view.ui.components.patient

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.myapp.doctorapp.model.MyDate
import com.myapp.doctorapp.viewmodel.PatientRegisteViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun MyDatePicker(modifier: Modifier, myDate: MyDate, onChangeValue: DatePickerDialog.OnDateSetListener) {

    val mContext = LocalContext.current


    val mDatePickerDialog  = DatePickerDialog(
        mContext,
        onChangeValue,
        myDate.year, myDate.month, myDate.day
    )


        // Creating a button that on
        // click displays/shows the DatePickerDialog
        IconButton(
            onClick = mDatePickerDialog::show,
            modifier= modifier,
        ) {
            Icon(
                tint = Color(0xDDF859BE),
                imageVector = Icons.Outlined.DateRange,
                contentDescription = null
            )
        }




}

@Preview(showBackground = true)
@Composable

fun DefaultPreview() {
    val date = MutableLiveData<MyDate>()
    MyDatePicker(modifier = Modifier, myDate = MyDate(), onChangeValue =  { _, _, _, _ ->
        {}
    })
}