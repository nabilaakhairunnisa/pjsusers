package com.nabila.userpjs.ui.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatBirthDate(dateStr: String): String {
    val parser = SimpleDateFormat("yyyy-M-d", Locale.getDefault())
    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)

    return try {
        val date = parser.parse(dateStr)
        if (date != null) {
            formatter.format(date)
        } else {
            dateStr
        }
    } catch (e: Exception) {
        dateStr
    }
}
