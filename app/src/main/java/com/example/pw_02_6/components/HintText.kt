package com.example.pw_02_6.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun HintText(text: String) {
    Text(
        text = text,
        fontSize = 12.sp
    )
}