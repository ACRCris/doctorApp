package com.myapp.doctorapp.view.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.myapp.doctorapp.model.Patient
import com.myapp.doctorapp.model.Routes
import com.myapp.doctorapp.view.ui.components.AddButton
import com.myapp.doctorapp.view.ui.components.patient.PatientCardView
import com.myapp.doctorapp.viewmodel.PatientViewModel

@Composable
fun PatientScreen(navController: NavController) {

    val context = LocalContext.current
    val patientViewModel = PatientViewModel()
    val patients: List<Patient>  by patientViewModel.patients.observeAsState(ArrayList())
    val isRead: Boolean by patientViewModel.isRead.observeAsState(false)

    patientViewModel.read(context)

    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ){
        if (isRead)
            LazyColumn(

                content = {
                    items(patients.size, key = { index -> patients[index].id }) {
                        PatientCardView(patient = patients[it] ){

                            IconButton(
                                modifier = Modifier.align(Alignment.BottomEnd),
                                onClick = {patientViewModel.delete(context, patients[it])
                                    patientViewModel.read(context)}
                            ) {
                                Icon(
                                    Icons.Outlined.Delete, contentDescription = null
                                )
                            }
                            IconButton(
                                modifier = Modifier.align(Alignment.CenterEnd),
                                onClick = {
                                    navController.navigate(Routes.HISTORY_PATIENT + "/${patients[it].identification}")
                                }
                            ) {
                                Icon(
                                    Icons.Outlined.List, contentDescription = null
                                )
                            }
                            IconButton(
                                modifier = Modifier.align(Alignment.TopEnd),
                                onClick = {
                                    val stringPatient = patients[it].toString()
                                    navController.navigate(Routes.PATIENT_REGISTER+"/$stringPatient/Actualizar")
                                }
                            ) {
                                Icon(
                                    Icons.Outlined.Edit, contentDescription = null
                                )
                            }
                        }
                    }
                }
            )
        else
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        AddButton(modifier = Modifier.align(Alignment.BottomEnd), navController, Routes.PATIENT_REGISTER)
    }

}

@Preview(showBackground = true)
@Composable
fun PatientScreenPreview() {
    val navController = rememberNavController( )
    PatientScreen(navController = navController)
}