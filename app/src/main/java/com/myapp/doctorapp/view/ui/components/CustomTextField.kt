package com.myapp.doctorapp.view.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CustomTextField(
    value: String,
    keyboardType: KeyboardType,
    placeholder: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    trailingIcon: @Composable() (() -> Unit)? = null,
    onGlobalyPosition: ((LayoutCoordinates) -> Unit)? = null,
    onValueChange: (String) -> Unit,


){
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(color = Color.Black),
        label = {
            Text(text = placeholder,
                style = MaterialTheme.typography.caption,
            )
                },
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                if (onGlobalyPosition != null) {
                    onGlobalyPosition(coordinates)
                }
            },
        enabled = enabled,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White,
            cursorColor =Color(0xFFF859BE),
            disabledLabelColor = Color(0xFFA7A3A3),
            focusedLabelColor =Color(0xFFF859BE),
            unfocusedLabelColor = Color.Black,
            textColor = Color.Black,
            focusedBorderColor = Color(0xFFF859BE),
            unfocusedBorderColor = Color(0xFFBB5FE9),

        )
    )
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    CustomTextField("",KeyboardType.Text,"Text Field" ){
    }
}