package com.example.myattendance.feature.home.data

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CalendarDataSource {
    // Property to get the current date (today)
    val today: Date
        get() {
            return Date()
        }

    // Method to get calendar data for a specific date range
    fun getData(startDate: Date = today, lastSelectedDate: Date): CalendarModel {
        // Create a Calendar instance and set its time to the provided start date
        val calendar = Calendar.getInstance()
        calendar.time = startDate

        // Set the day of the week to Monday for the start date
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        val firstDayOfWeek = calendar.time

        // Add 6 days to the start date to get the end date of the week
        calendar.add(Calendar.DAY_OF_YEAR, 6)
        val endDayOfWeek = calendar.time

        // Get a list of dates between the start and end date of the week
        val visibleDates = getDatesBetween(firstDayOfWeek, endDayOfWeek)

        // Convert the list of dates to a CalendarModel and return
        return toCalendarModel(visibleDates, lastSelectedDate)
    }

    // Helper method to get a list of dates between two given dates
    private fun getDatesBetween(startDate: Date, endDate: Date): List<Date> {
        val dateList = mutableListOf<Date>()
        val calendar = Calendar.getInstance()
        calendar.time = startDate

        // Iterate through the dates and add them to the list
        while (calendar.time <= endDate) {
            dateList.add(calendar.time)
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
        return dateList
    }

    // Helper method to convert a list of dates to a CalendarModel
    private fun toCalendarModel(
        dateList: List<Date>,
        lastSelectedDate: Date
    ): CalendarModel {
        // Create a CalendarModel instance with selected and visible dates
        return CalendarModel(
            selectedDate = toItemModel(lastSelectedDate, true),
            visibleDates = dateList.map {
                toItemModel(it, it == lastSelectedDate)
            }
        )
    }

    // Helper method to convert a date to a CalendarModel.DateModel
    private fun toItemModel(date: Date, isSelectedDate: Boolean): CalendarModel.DateModel {
        // Create a DateModel instance for a specific date
        return CalendarModel.DateModel(
            isSelected = isSelectedDate,
            isToday = isToday(date),
            date = date
        )
    }

    // Helper method to check if a given date is today
    private fun isToday(date: Date): Boolean {
        // Compare the given date with the current date (today)
        val todayDate = today
        return date.toFormattedDateString() == todayDate.toFormattedDateString()
    }
}

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
