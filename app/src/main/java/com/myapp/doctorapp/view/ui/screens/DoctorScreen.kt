package com.myapp.doctorapp.view.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.myapp.doctorapp.view.ui.components.doctor.DoctorCardView
import com.myapp.doctorapp.view.ui.theme.DoctorAppTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.myapp.doctorapp.model.Doctor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.myapp.doctorapp.model.Routes
import com.myapp.doctorapp.view.ui.components.AddButton
import com.myapp.doctorapp.viewmodel.DoctorViewModel


@Composable
fun DoctorScreen(navController: NavController) {

    val doctorViewModel = DoctorViewModel()
    val context: Context = LocalContext.current
    val doctors: List<Doctor> by doctorViewModel.doctors.observeAsState(ArrayList( ))
    val isRead: Boolean by doctorViewModel.isRead.observeAsState(false)
    val canDelete:Boolean by doctorViewModel.canDeleted.observeAsState(false)
    val finishDeleteProcess:Boolean by doctorViewModel.finishDeleteProcess.observeAsState(false)
    val havePatients:Boolean by doctorViewModel.havePatients.observeAsState(false)
    var index:Int by remember{ mutableStateOf(0) }
    var showDialog: Boolean by remember { mutableStateOf(false) }


    if(!isRead){
        doctorViewModel.read(context)
    }
    if(finishDeleteProcess){
        if (!canDelete && havePatients){
            Toast.makeText(context, "No se puede eliminar el medico debido \n a que tiene citas en proceso", Toast.LENGTH_SHORT).show()
        }else {
            if(!havePatients && !canDelete) {
                showDialog = true
            }

        }
        index = 0
        doctorViewModel.finishDeleteProcess.value=false

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        if(showDialog){
            Card(
                modifier= Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(10.dp),
                elevation = 10.dp,

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                       text ="¿Desea eliminar el medico?",
                       fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(text = "Al eleminar el medico se eliminaran el historial de citas asociadas a este medico", fontSize = 14.sp)
                    Text(text = "¿Desea continuar?", fontSize = 14.sp)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = {
                            showDialog = false
                        }) {
                            Text(text = "Cancelar", color = Color(0xFFF44336))
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        TextButton(onClick = {
                            doctorViewModel.delete(context, doctors[index])
                            doctorViewModel.isRead.value = false
                            showDialog = false
                        }) {
                            Text(text = "Aceptar", color = Color(0xFF6E6F6F))
                        }
                    }


                }

            }
        }
        if(isRead)
            LazyColumn (

                    ){
                items(
                    doctors.size,
                    key = { item -> doctors[item].id }
                )
                 {
                    DoctorCardView(
                        navController = navController,
                        doctors[it],
                    ) {
                        doctorViewModel.verifyDelete(context, doctors[it]);
                        index=it
                    }
                }

            }
        else
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        AddButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            navController = navController,
            route = Routes.DOCTOR_REGISTER
        )

    }

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    DoctorAppTheme {
        DoctorScreen(navController)
    }
}
