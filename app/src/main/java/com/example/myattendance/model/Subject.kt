package com.example.myattendance.model

data class Subject(
    val name: String,
    val numberOfPresent: Int,
    val totalClasses: Int,
    //val classSchedule: List<TimeSlot>
) {

    var attendancePer: Float = if (totalClasses != 0) {
        numberOfPresent.toFloat() / totalClasses.toFloat()
    } else {
        0.0f
    }
}