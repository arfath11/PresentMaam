package com.example.myattendance.feature.extension

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

//Extension functions for data

// Extension function to format a date as "EEEE, LLLL dd" (e.g., "Monday, January 23")
fun Date.toFormattedDateString(): String {
    val sdf = SimpleDateFormat("EEEE, LLLL dd", Locale.getDefault())
    return sdf.format(this)
}

// Extension function to format a date as "MMMM dd" (e.g., "January 23")
fun Date.toFormattedMonthDateString(): String {
    val sdf = SimpleDateFormat("MMMM dd", Locale.getDefault())
    return sdf.format(this)
}

// Extension function to format a date as "dd" (e.g., "23")
fun Date.toFormattedDateShortString(): String {
    val sdf = SimpleDateFormat("dd", Locale.getDefault())
    return sdf.format(this)
}

// Extension function to format a long value as "LLLL dd, yyyy" (e.g., "January 23, 2024")
fun Long.toFormattedDateString(): String {
    val sdf = SimpleDateFormat("LLLL dd, yyyy", Locale.getDefault())
    return sdf.format(this)
}

// Extension function to format a date as "HH:mm" (e.g., "14:30")
fun Date.toFormattedTimeString(): String {
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(this)
}

// Extension function to check if a date has passed (compared to the current time)
fun Date.hasPassed(): Boolean {
    val calendar = Calendar.getInstance()

    // Subtract one second from the current time
    calendar.add(Calendar.SECOND, -1)
    val oneSecondAgo = calendar.time

    // Check if the date is earlier than one second ago
    return time < oneSecondAgo.time
}
