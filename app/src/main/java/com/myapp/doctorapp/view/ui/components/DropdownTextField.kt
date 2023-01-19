package com.myapp.doctorapp.view.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize

@Composable
fun DropdownTextField(
    suggestions: List<String>,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit

) {

    var expanded by remember{ mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = Icons.Filled.ArrowDropDown
    Column() {
        CustomTextField(
            value = value,
            placeholder = placeholder,
            onValueChange = onValueChange,
            keyboardType = KeyboardType.Text,
            enabled = false,
            trailingIcon = {
                Icon( icon , contentDescription = null,
                    modifier = Modifier.clickable(
                        onClick = {
                            expanded = !expanded
                        }
                    )
                )

            },
            onGlobalyPosition = {
                    coordinates -> textFieldSize = coordinates.size.toSize()
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current){
                textFieldSize.width.toDp()
            })
        ){
            suggestions.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        onValueChange(label)
                        expanded = false
                    }
                ){
                    Text(
                        color = Color.Black,
                        text = label,

                        )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DropdownTextFieldPreview() {
    val cities = listOf("Bogota", "Medellin", "Cali", "Barranquilla")
    DropdownTextField(cities, "", "ciudades", ){}
}