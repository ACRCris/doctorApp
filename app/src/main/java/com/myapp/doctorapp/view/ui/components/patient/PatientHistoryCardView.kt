package com.myapp.doctorapp.view.ui.components.patient

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapp.doctorapp.R
import com.myapp.doctorapp.model.MedicalHistory
import com.myapp.doctorapp.view.ui.theme.Shapes
import com.myapp.doctorapp.view.ui.theme.Typography

@Composable
fun PatientHistoryCardView(medicalHistory: MedicalHistory) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .clickable { },
        elevation = 10.dp,
        shape = Shapes.large,

        ) {
        Box {
            Row (
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(bottom = 10.dp)
            ){
                Column {
                    Row {
                        Image(
                            modifier = Modifier
                                .weight(4f, true)
                                .padding(start=10.dp,end=10.dp, top = 10.dp)
                                .align(Alignment.CenterVertically),
                            painter = painterResource(id = R.drawable.doctor),
                            contentDescription = null
                        )
                        Column(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .weight(9f, true),
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(6f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Bottom

                            ) {
                                Text(
                                    text = "${medicalHistory.doctor_name} ${medicalHistory.doctor_surname}",
                                    style = Typography.h1
                                )
                                Row(
                                    verticalAlignment = Alignment.Bottom,
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .weight(7f, true)
                                    ) {
                                        Text(text = "Tarjeta profesional: ${medicalHistory.doctor_ProfessionalCard}")
                                        Text(text = "Especialidad: ${medicalHistory.doctor_speciality}")
                                        Text(text = "Experiencia: ${medicalHistory.doctor_experience}")
                                        Text(text = "Clinica: ${medicalHistory.doctor_clinic}")
                                        Text(text = "Cita agendada: ${medicalHistory.patient_doctorAppointment} ")
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }

}

