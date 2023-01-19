package com.myapp.doctorapp.view.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.myapp.doctorapp.model.Doctor
import com.myapp.doctorapp.model.Routes

@Composable
fun AddButton(
    modifier: Modifier,
    navController: NavController,
    route:String
){
    Button(
        modifier = modifier
            .size(50.dp),
        onClick = {
            when(route){
                Routes.PATIENT_REGISTER -> navController.navigate(Routes.PATIENT_REGISTER+"/''/''")
                Routes.DOCTOR_REGISTER -> navController.navigate(Routes.DOCTOR_REGISTER+"/''/''")
                 //Routes.DOCTOR_REGISTER -> navController.navigate(Routes.DOCTOR_REGISTER+"/${Doctor("", "", "","","",0,false)}")
            } },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xDDF859BE)),
    ) {

        Icon(
            tint = Color.White,
            modifier = Modifier
                .size(30.dp),
            imageVector = Icons.Outlined.Add,
            contentDescription = null
        )

    }
}

@Preview(showBackground = true)
@Composable
fun AddButtonPreview(){
    val navController = rememberNavController()
    AddButton(Modifier, navController, Routes.DOCTOR_REGISTER)
}