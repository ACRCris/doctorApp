package com.myapp.doctorapp.view.ui.screens

import androidx.compose.foundation.layout.*


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapp.doctorapp.view.ui.components.MainButton
import com.myapp.doctorapp.R

@Composable
fun MainScreen(navController: NavController) {
    Row(){

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f, true)
        ){
            MainButton(
                modifier = Modifier.weight(1f, true),
                id = R.drawable.doctor,navController = navController)
            MainButton(modifier = Modifier.weight(1f, true),
                id = R.drawable.patient , navController = navController)
        }
    }


}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val navController = NavController(LocalContext.current)
    MainScreen(navController = navController)
}