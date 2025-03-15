package com.example.bugstrakerapp.ui

import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun SnackBarError(text: String) {
    Snackbar(
        containerColor = Color.Red,
        contentColor = Color.White
    ) {
        Text(text = text)
    }
}
