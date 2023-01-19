package com.myapp.doctorapp.view.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
import com.myapp.doctorapp.model.MyDate
import com.myapp.doctorapp.model.Patient
import com.myapp.doctorapp.view.ui.components.ActionButton
import com.myapp.doctorapp.view.ui.components.CustomTextField
import com.myapp.doctorapp.view.ui.components.DropdownTextField
import com.myapp.doctorapp.view.ui.components.patient.MyDatePicker
import com.myapp.doctorapp.view.ui.theme.DoctorAppTheme
import com.myapp.doctorapp.view.ui.theme.Typography
import com.myapp.doctorapp.viewmodel.PatientRegisteViewModel
import androidx.compose.runtime.livedata.observeAsState




@Composable
fun PatientRegisterScreen(
    navController: NavController,
    action:String="Añadir",
    init: Patient = Patient("","","",MyDate().toString(), MyDate().toString(),"",false,0.0)
) {
    val patientRegisterViewModel = PatientRegisteViewModel()

    val context = LocalContext.current



    val name: String by patientRegisterViewModel.name.observeAsState(init.name)
    val surname: String by patientRegisterViewModel.surname.observeAsState(init.surname)
    val ID: String by patientRegisterViewModel.id.observeAsState(init.identification)
    val birthDate: MyDate by patientRegisterViewModel.birthDate.observeAsState(MyDate(init.birthDate))
    val doctorAppointment: MyDate by patientRegisterViewModel.doctorAppointment.observeAsState(MyDate(init.doctorAppointment))
    val doctor: Doctor by patientRegisterViewModel.doctor.observeAsState(Doctor(init.doctor))
    val inHealing: Boolean by patientRegisterViewModel.inHealing.observeAsState(init.isInHealing)
    val scaleFee: Double by patientRegisterViewModel.scaleFee.observeAsState(init.scaleFee)

    var stringScaleFee:String by remember{ mutableStateOf(init.scaleFee.toString()) }
    val doctors: List<String> by patientRegisterViewModel.doctorsProfessionalCard.observeAsState(listOf())

    patientRegisterViewModel.read(context)

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {

        Column{
            Text(text ="Registro de paciente", style = Typography.h1)
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(value = name, keyboardType = KeyboardType.Text, placeholder = "Nombre"){
                name -> patientRegisterViewModel.name.value = name
            }
            CustomTextField(value = surname, keyboardType = KeyboardType.Text, placeholder = "Apellido"){
                surname -> patientRegisterViewModel.surname.value = surname
            }
            CustomTextField(value = ID, keyboardType = KeyboardType.Decimal, placeholder = "Cedula de ciudadania"){
                ID -> patientRegisterViewModel.id.value = ID
            }
            CustomTextField(value = stringScaleFee, keyboardType = KeyboardType.Decimal, placeholder = "Couta moderadora"){
                try {
                    patientRegisterViewModel.scaleFee.value = it.toDouble()
                    stringScaleFee = it
                }catch (_:java.lang.NumberFormatException){
                }
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)) {
                CustomTextField(
                    value = birthDate.toString(),
                    keyboardType =KeyboardType.Text,
                    placeholder = "Fecha De nacimiento",
                    enabled = false,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .align(Alignment.CenterStart)

                ){
                    date -> patientRegisterViewModel.birthDate.value = MyDate(date);
                }
                MyDatePicker(
                    modifier = Modifier
                        .fillMaxSize(0.2f)
                        .align(Alignment.CenterEnd),
                    myDate = birthDate,
                    onChangeValue = { _, year, month, dayOfMonth -> patientRegisterViewModel.birthDate.value = MyDate("${dayOfMonth}-${month+1}-${year}")
                    }
                )
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)) {
                CustomTextField(
                    value = doctorAppointment.toString(),
                    keyboardType =KeyboardType.Text,
                    placeholder = "Fecha de cita medica",
                    enabled = false,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .align(Alignment.CenterStart)

                ){
                        date -> patientRegisterViewModel.doctorAppointment.value = MyDate(date);
                }
                MyDatePicker(
                    modifier = Modifier
                        .fillMaxSize(0.2f)
                        .align(Alignment.CenterEnd),
                    myDate = birthDate,
                    onChangeValue = { _, year, month, dayOfMonth -> patientRegisterViewModel.doctorAppointment.value = MyDate("${dayOfMonth}-${month+1}-${year}")
                    }
                )
            }
            DropdownTextField(suggestions = doctors, value = doctor.professionalCard, placeholder = "Medicos", ){
                doctor -> patientRegisterViewModel.doctor.value = Doctor(doctor)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 10.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = "En tratamiento")
                Checkbox(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    checked = inHealing,
                    onCheckedChange = {
                        inHealing -> patientRegisterViewModel.inHealing.value = inHealing
                    },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color(0xDDF859BE),
                        disabledColor = Color(0xFFBB5FE9),
                        checkedColor = Color(0xDDF859BE),
                        uncheckedColor = Color(0xFFBB5FE9),

                        )
                )
            }
            ActionButton (action= action){
                if(action == "Añadir") {
                    patientRegisterViewModel.write(
                        context,
                        Patient(
                            name,
                            surname,
                            ID,
                            birthDate.toString(),
                            doctorAppointment.toString(),
                            doctor.professionalCard,
                            inHealing,
                            scaleFee
                        )
                    )
                }else if (action == "Actualizar"){
                    patientRegisterViewModel.updateRegister(
                        context,
                        Patient(
                            init.id,
                            name,
                            surname,
                            ID,
                            birthDate.toString(),
                            doctorAppointment.toString(),
                            doctor.professionalCard,
                            inHealing,
                            scaleFee
                        )
                    )
                }
                navController.popBackStack()

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PatientRegisterScreenPreview() {
    val navController = rememberNavController()
    DoctorAppTheme {
        PatientScreen(navController = navController)
    }
}

