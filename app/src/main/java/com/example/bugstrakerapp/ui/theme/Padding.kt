package com.example.bugstrakerapp.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Padding(
    val padding2: Dp,
    val padding6:Dp,
    val padding10: Dp,
    val padding24: Dp,
    )

val padding = Padding(
    padding2 = 2.dp,
    padding6 = 6.dp,
    padding10 = 10.dp,
    padding24 = 24.dp
)