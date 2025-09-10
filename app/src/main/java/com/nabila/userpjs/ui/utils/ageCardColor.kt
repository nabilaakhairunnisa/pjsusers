package com.nabila.userpjs.ui.utils

import androidx.compose.ui.graphics.Color
import com.nabila.userpjs.ui.theme.Green
import com.nabila.userpjs.ui.theme.Yellow
import com.nabila.userpjs.ui.theme.Orange

fun ageCardColor(age: Int): Color = when {
    age <= 30 -> Green
    age in 31..40 -> Yellow
    else -> Orange
}
