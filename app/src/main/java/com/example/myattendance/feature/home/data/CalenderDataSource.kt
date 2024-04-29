package com.example.myattendance.feature.home.data

import com.example.myattendance.feature.extension.toFormattedDateString
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

