package com.myapp.doctorapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myapp.doctorapp.model.*
import com.myapp.doctorapp.view.ui.screens.*
import com.myapp.doctorapp.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONStringer


class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val mainViewModel = MainViewModel()
        mainViewModel.connectRetrofit(this)

        setContent { NavigationHost() }
    }
}

@Composable
fun NavigationHost() {







    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        NavHost(navController = navController, startDestination = Routes.HOME) {
            composable(route = Routes.HOME) {
                MainScreen(navController = navController)
            }
            composable(route = Routes.PATIENT) {
                PatientScreen(navController = navController)
            }
            composable(route = Routes.DOCTOR) {
                DoctorScreen(navController = navController)
            }
            composable(route = Routes.DOCTOR_REGISTER+"/" +
                    "{doctor_object}/{action}") {
                val objectString: String = it.arguments?.getString("doctor_object") ?: ""
                val action: String = it.arguments?.getString("action") ?: ""
                if (objectString!= "''"){
                    DoctorRegisterScreen(navController = navController, init = Doctor.valueOf(objectString), action=action)
                } else {
                    DoctorRegisterScreen(navController = navController)
                }
            }
            composable(route = Routes.PATIENT_REGISTER+"/{patient_object}/{action}") {
                val objectString: String = it.arguments?.getString("patient_object") ?: ""
                val action: String = it.arguments?.getString("action") ?: ""

                if (objectString!= "''"){
                    PatientRegisterScreen(navController = navController, init = Patient.valueOf(objectString), action=action)
                } else {
                    PatientRegisterScreen(navController = navController)
                }

            }
            composable(route = Routes.HISTORY_DOCTOR+"/{doctor_object}") {
                val doctor: String = it.arguments?.getString("doctor_object") ?: ""
                DoctorHistoryScreen(navController=navController,doctor = Doctor.valueOf(doctor))
            }
            composable(route = Routes.HISTORY_PATIENT+"/{patient_identification}") {
                val identification: String = it.arguments?.getString("patient_identification") ?: ""
                PatientHistoryScreen(patientIdentification = identification)
            }
            composable(route = Routes.SIGNATURE+"/{doctor_object}/{patient_object}") {
                val doctor: String = it.arguments?.getString("doctor_object") ?: ""
                val patient: String = it.arguments?.getString("patient_object") ?: ""
                SignatureScreen(navController=navController,doctor=Doctor.valueOf(doctor),patient=Patient.valueOf(patient))
            }

        }
    }

}




