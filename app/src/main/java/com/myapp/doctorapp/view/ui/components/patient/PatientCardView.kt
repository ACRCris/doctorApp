package com.myapp.doctorapp.view.ui.components.patient

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapp.doctorapp.R
import com.myapp.doctorapp.model.Patient
import com.myapp.doctorapp.model.Routes
import com.myapp.doctorapp.view.ui.theme.Shapes
import com.myapp.doctorapp.view.ui.theme.Typography

@Composable
fun PatientCardView(
    patient: Patient,
    content:@Composable () -> Unit,
) {

    val icon = if (patient.isInHealing) {
        Icons.Default.Check
    } else {
        Icons.Default.Close
    }
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
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 5.dp)
                    ){
                Column {
                    Row {
                        Image(
                            modifier = Modifier
                                .weight(4f, true)
                                .padding(top = 5.dp, start = 5.dp, bottom = 5.dp)
                                .align(Alignment.CenterVertically),
                            painter = painterResource(id = R.drawable.patient),
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
                                    text = "${patient.name} ${patient.surname}",
                                    style = Typography.h1
                                )
                                Row(
                                    verticalAlignment = Alignment.Bottom,
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .weight(7f, true)
                                    ) {
                                        Text(text = "Identificacion: ${patient.identification}")
                                        Text(text = "Fecha de nacimiento ${patient.birthDate}")
                                        Text(text = "Medico: ${patient.doctor}")
                                        Text(text = "Cuota moreradora: ${patient.scaleFee}")
                                    }
                                }
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 20.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(4f, true)
                        ) {

                            Text(text = "Cita agendada: ${patient.doctorAppointment}")
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(50.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "En tratamiento")
                                Icon(icon, contentDescription = null)
                            }
                        }
                        Column(
                            modifier = Modifier.weight(1f, true),
                        ) {

                        }
                    }
                }
            }
            content()



        }
    }


}


@Preview(showBackground = true)
@Composable
fun PatientCardViewPreview() {
    PatientCardView(
        patient = Patient(
             "Juan",
            "Perez",
            "123456789",
             "12/12/2021",
             "Medico nuclear",
             "Cuota moreradora",
             false,
             1000.0
        ),
    ){}
}