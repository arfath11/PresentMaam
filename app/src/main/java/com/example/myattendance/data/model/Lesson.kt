package com.example.myattendance.data.model

import com.example.myattendance.domain.model.AttendanceStatus
import java.util.Date

data class Lesson(
    val id: Int,
    val date: Long, // not usefull to store date.
    val attendanceStatus: AttendanceStatus,
    )

