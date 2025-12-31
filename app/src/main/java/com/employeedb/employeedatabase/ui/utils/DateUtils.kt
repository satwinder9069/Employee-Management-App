package com.employeedb.employeedatabase.ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
    return formatter.format(Date(millis))
}
fun parseDate(date: String): Long {
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return formatter.parse(date)?.time ?: 0L
}