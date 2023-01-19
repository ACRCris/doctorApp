package com.myapp.doctorapp.view.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapp.doctorapp.model.Doctor
import com.myapp.doctorapp.model.MedicalHistory
import com.myapp.doctorapp.model.Patient
import com.myapp.doctorapp.viewmodel.SignatureViewModel
import se.warting.signaturepad.SignaturePadAdapter
import se.warting.signaturepad.SignaturePadView

@Composable

fun SignatureScreen(navController: NavController,doctor: Doctor,patient: Patient) {
    var signaturePadAdapter: SignaturePadAdapter? = null
    val signatureViewModel = SignatureViewModel(doctor.professionalCard)
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp)
                .weight(0.9f)
                .background(Color(0xFFDFDEDE))
        ) {
            SignaturePadView(
                clearOnDoubleClick = true,
                onReady = { signaturePadView ->
                    signaturePadAdapter = signaturePadView
                }

            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.2f, fill = true)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xDDF859BE)),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f, fill = true)
                    .padding(start = 20.dp, end = 20.dp),
                onClick = {
                    if(signaturePadAdapter != null){
                        signatureViewModel.write(context,
                            MedicalHistory(
                                patient.id,
                                patient.name,
                                patient.surname,
                                patient.identification,
                                patient.birthDate,
                                patient.doctorAppointment,
                                patient.doctor,
                                patient.isInHealing,
                                patient.scaleFee,
                                true,
                                doctor.id,
                                doctor.name,
                                doctor.surname,
                                doctor.professionalCard,
                                doctor.speciality,
                                doctor.clinic,
                                doctor.experience,
                                signatureViewModel.bitmapToByteArray(signaturePadAdapter!!.getSignatureBitmap())
                            )
                        )
                        signatureViewModel.updateRegister(context, Patient(
                            patient.id,
                            patient.name,
                            patient.surname,
                            patient.identification,
                            patient.birthDate,
                            "",
                            "",
                            patient.isInHealing,
                            patient.scaleFee,
                        ))
                        navController.popBackStack()

                    }
                }
            ) {
                Text("Guardar firma", fontSize = 20.sp, color= Color.White)
            }
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFBB5FE9)),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f, fill = true)
                    .padding(start = 20.dp, end = 20.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Cancelar", fontSize = 20.sp, color= Color.White)
            }
         
        }
       
    }



}

