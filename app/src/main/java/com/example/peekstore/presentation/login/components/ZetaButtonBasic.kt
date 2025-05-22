package com.example.peekstore.presentation.login.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun ZetaButtonBasic(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    text: String,
    color: Color = MaterialTheme.colorScheme.primary,
    textSize: TextUnit = 16.sp,
    onClick: () -> Unit
) {
    Button (
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = color,
            containerColor = backgroundColor
        ),
        modifier = modifier
    ) {
        Text(text = text, fontSize = textSize, color = Color.White)
    }
}