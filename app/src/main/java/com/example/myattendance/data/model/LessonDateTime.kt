package com.example.myattendance.data.model

import java.time.DayOfWeek

data class LessonDateTime(
    val dayOfWeek: Int,  // Dont use DayOfWeek from java class
    val startTime: Long,
    val duration: Long,
)
