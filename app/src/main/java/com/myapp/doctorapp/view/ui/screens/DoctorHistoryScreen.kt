package com.myapp.doctorapp.view.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapp.doctorapp.model.Doctor
import com.myapp.doctorapp.model.MedicalHistory
import com.myapp.doctorapp.model.Patient
import com.myapp.doctorapp.model.Routes
import com.myapp.doctorapp.view.ui.components.patient.PatientCardView
import com.myapp.doctorapp.view.ui.theme.Typography
import com.myapp.doctorapp.viewmodel.DoctorHistoryModel
import se.warting.signaturepad.SignaturePadView

@Composable
fun DoctorHistoryScreen(navController: NavController,doctor: Doctor) {
    val context: Context = LocalContext.current

    val doctorHistoryModel = DoctorHistoryModel(doctor.professionalCard)
    val unResolve:List<Patient> by doctorHistoryModel.unResolvedPatients.observeAsState(listOf())
    val resolved:List<MedicalHistory> by doctorHistoryModel.resolvedPatients.observeAsState(listOf())

    val isRead: Boolean by doctorHistoryModel.isRead.observeAsState(false)

    val showUnresolved: Boolean by doctorHistoryModel.showUnresolved.observeAsState(false)
    val showResolved: Boolean by doctorHistoryModel.showResolved.observeAsState(false)

    if(!isRead){
        doctorHistoryModel.read(context)
    }

    if(isRead){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = {
                    doctorHistoryModel.showUnresolved.value = !showUnresolved
                })
                {
                    Icon(
                        imageVector = if (showUnresolved) Icons.Filled.KeyboardArrowRight else Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
                Text(text = "Consultas Pendientes", style = Typography.h1)

            }
            if (showUnresolved)
                Box {
                    LazyColumn(
                        content = {
                            items(
                                unResolve.size,
                                key = { item -> unResolve[item].id }
                            )
                            {
                                PatientCardView(
                                    unResolve[it]
                                ) {

                                    IconButton(
                                        modifier = Modifier.align(Alignment.BottomEnd),
                                        onClick = {
                                            doctorHistoryModel.write(context,
                                                MedicalHistory(
                                                    unResolve[it].id,
                                                    unResolve[it].name,
                                                    unResolve[it].surname,
                                                    unResolve[it].identification,
                                                    unResolve[it].birthDate,
                                                    unResolve[it].doctorAppointment,
                                                    unResolve[it].doctor,
                                                    unResolve[it].isInHealing,
                                                    unResolve[it].scaleFee,
                                                    false,
                                                    doctor.id,
                                                    doctor.name,
                                                    doctor.surname,
                                                    doctor.professionalCard,
                                                    doctor.speciality,
                                                    doctor.clinic,
                                                    doctor.experience,
                                                    null

                                                )
                                            )
                                            doctorHistoryModel.updateRegister(context, Patient(
                                                unResolve[it].id,
                                                unResolve[it].name,
                                                unResolve[it].surname,
                                                unResolve[it].identification,
                                                unResolve[it].birthDate,
                                                "",
                                               "",
                                                unResolve[it].isInHealing,
                                                unResolve[it].scaleFee,
                                            ))
                                            doctorHistoryModel.isRead.value = false


                                        }
                                    ) {
                                        Icon(
                                            Icons.Outlined.Close,
                                            contentDescription = null,
                                            tint = Color(0xFFF44336)
                                        )
                                    }
                                    IconButton(
                                        modifier = Modifier.align(Alignment.TopEnd),
                                        onClick = {

                                            navController.navigate(Routes.SIGNATURE+"/${doctor}/${unResolve[it]}")

                                        }
                                    ) {
                                        Icon(
                                            Icons.Outlined.Check,
                                            contentDescription = null,
                                            tint = Color(0xFF4CAF50)
                                        )
                                    }


                                }
                            }
                        }
                    )
                }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = {
                    doctorHistoryModel.showResolved.value = !showResolved
                })
                {
                    Icon(
                        imageVector = if (showResolved) Icons.Filled.KeyboardArrowRight else Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
                Text(text = "Consultas Resueltas", style = Typography.h1)

                }
            if (showResolved)
                Box {
                    LazyColumn(
                        content = {
                            items(
                                resolved.size,
                                key = { item -> resolved[item].id }
                            )
                            {
                                PatientCardView(
                                    Patient(
                                        resolved[it].id,
                                        resolved[it].patient_name,
                                        resolved[it].patient_surname,
                                        resolved[it].patient_identification,
                                        resolved[it].patient_birthDate,
                                        resolved[it].patient_doctorAppointment,
                                        resolved[it].patient_doctor,
                                        resolved[it].isPatient_inHealing,
                                        resolved[it].patient_scaleFee,
                                    )
                                ) {

                                    IconButton(
                                        modifier = Modifier.align(
                                            if (resolved[it].isAccepted) Alignment.TopEnd else Alignment.BottomEnd
                                            //Alignment.BottomEnd
                                        ),
                                        onClick = {


                                        }
                                    ) {
                                        Icon(
                                            if(resolved[it].isAccepted) Icons.Outlined.Check else Icons.Outlined.Close,
                                            //Icons.Outlined.Delete,
                                            contentDescription = null,
                                            tint =  if(resolved[it].isAccepted) Color(0xFF4CAF50) else Color(0xFFF44336),//Color(0xFF4CAF50)//
                                        )
                                    }

                                }
                            }
                        }
                    )
                }




        }
    }else{
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }

}

