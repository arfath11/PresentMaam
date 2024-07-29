package com.example.myattendance.data.model

data class Subject(
    val name: String,
    val startDate: Long,
    val endDate: Long,
    val repeatDays: List<LessonDateTime>,
    val lesson: List<Lesson>
)
