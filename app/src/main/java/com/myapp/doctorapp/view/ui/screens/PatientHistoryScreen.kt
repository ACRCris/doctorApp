package com.myapp.doctorapp.view.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.myapp.doctorapp.model.MedicalHistory
import com.myapp.doctorapp.view.ui.components.patient.PatientHistoryCardView
import com.myapp.doctorapp.view.ui.theme.Typography
import com.myapp.doctorapp.viewmodel.PatientHistoryModel
import se.warting.signaturepad.SignaturePadView

@Composable
fun PatientHistoryScreen(patientIdentification: String) {

    val patientHistoryModel = PatientHistoryModel(patientIdentification)

    val context: Context = LocalContext.current

    val patientHistory:List<MedicalHistory> by patientHistoryModel.medicalHistory.observeAsState(listOf())
    val isRead:Boolean by patientHistoryModel.isRead.observeAsState(false)

    if(!isRead){
        patientHistoryModel.read(context)
    }
    Box (
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()
            ){


        if(isRead){
            Column {
                Text(
                    modifier= Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 10.dp),
                    text = "Historial de paciente ${patientIdentification}",
                    style= Typography.h1)
                LazyColumn(
                    content = {
                        items(patientHistory.size, key = { index -> patientHistory[index].id }) {
                            PatientHistoryCardView(medicalHistory = patientHistory[it])
                        }
                    }
                )
            }
        }else{
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }



}
