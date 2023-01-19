package com.myapp.doctorapp.view.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapp.doctorapp.view.ui.theme.Shapes

@Composable
fun ActionButton(modifier : Modifier = Modifier, action:String="Añadir",onClick: () -> Unit){
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(50.dp),

        onClick = onClick,
        shape = Shapes.medium,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xDDF859BE)),
    ) {

        Text(
            text = action,
            color = Color.White,
        )

    }
}
@Preview(showBackground = true)
@Composable
fun ActionButtonPreview() {
    ActionButton(){}
}