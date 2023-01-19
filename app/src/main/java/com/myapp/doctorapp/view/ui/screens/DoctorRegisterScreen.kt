package com.myapp.doctorapp.view.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.myapp.doctorapp.model.Doctor
import com.myapp.doctorapp.view.ui.components.ActionButton
import com.myapp.doctorapp.view.ui.components.CustomTextField
import com.myapp.doctorapp.view.ui.theme.DoctorAppTheme
import com.myapp.doctorapp.view.ui.theme.Typography
import com.myapp.doctorapp.viewmodel.DoctorRegisterViewModel


@Composable
fun DoctorRegisterScreen(
    navController: NavController,
    action:String="Añadir",
    init: Doctor = Doctor("","","","","",0,false)
) {
    val doctorRegisterViewModel = DoctorRegisterViewModel()
    val name : String by doctorRegisterViewModel.name.observeAsState(initial =init.name)
    val surname: String by doctorRegisterViewModel.surname.observeAsState(initial = init.surname)
    val professionalCard: String by doctorRegisterViewModel.professionalCard.observeAsState(initial = init.professionalCard)
    val speciality: String by doctorRegisterViewModel.speciality.observeAsState(initial = init.speciality)
    val experience: Int by doctorRegisterViewModel.experience.observeAsState(initial = init.experience)
    val clinic: String by doctorRegisterViewModel.clinic.observeAsState(initial = init.clinic)
    val availableHomeCare: Boolean by doctorRegisterViewModel.availableHomeCare.observeAsState(initial = init.isAvailableHomeCare)

    val context: Context = LocalContext.current

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {

        Column{
            Text(text = "Registro de medico", style = Typography.h1)
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(value = name, keyboardType = KeyboardType.Text, placeholder = "Nombre"){
                nameText -> doctorRegisterViewModel.name.value = nameText
            }
            CustomTextField(value = surname, keyboardType = KeyboardType.Text, placeholder = "Apellido"){
                surname -> doctorRegisterViewModel.surname.value = surname
            }
            CustomTextField(value = professionalCard, keyboardType = KeyboardType.Text, placeholder = "Codigo de tarjeta profesional"){
                professionalCard -> doctorRegisterViewModel.professionalCard.value = professionalCard
            }
            CustomTextField(value = speciality, keyboardType = KeyboardType.Text, placeholder = "Especialidad"){
                speciality -> doctorRegisterViewModel.speciality.value = speciality
            }
            CustomTextField(value = experience.toString(), KeyboardType.Decimal, placeholder = "Años de experiencia"){
                if (it == "") {
                    doctorRegisterViewModel.experience.value = 0
                } else {
                    doctorRegisterViewModel.experience.value = it.toInt()
                }

            }
            CustomTextField(value = clinic, keyboardType = KeyboardType.Text ,placeholder = "Consultorio"){
                clinic -> doctorRegisterViewModel.clinic.value = clinic
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 10.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = "Atecion domiciliaria")
                Checkbox(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    checked = availableHomeCare,
                    onCheckedChange = {
                        availableHomeCare -> doctorRegisterViewModel.availableHomeCare.value = availableHomeCare
                    },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color(0xDDF859BE),
                        disabledColor = Color(0xFFBB5FE9),
                        checkedColor = Color(0xDDF859BE),
                        uncheckedColor = Color(0xFFBB5FE9),

                        )
                )
            }
            ActionButton(action= action) {
                if(action == "Añadir")
                    doctorRegisterViewModel.write(context, Doctor( name, surname, professionalCard, speciality,clinic ,
                        experience, availableHomeCare))
                else if(action=="Actualizar")
                    doctorRegisterViewModel.updateRegister(context, Doctor(init.id, name, surname, professionalCard, speciality,clinic ,
                        experience, availableHomeCare))

                navController.popBackStack()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorRegisterScreenPreview() {
    val navController = rememberNavController()
    DoctorAppTheme {
        DoctorRegisterScreen(navController=  navController)
    }
}

