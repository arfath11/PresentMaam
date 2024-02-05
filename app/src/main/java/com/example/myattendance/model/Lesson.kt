package com.example.myattendance.model


enum class AttendanceStatus(val initialSelected: Boolean= false ,
                            val onClick: () -> Unit = { /*TODO*/  },
                            val label: String
                            ){
    PRESENT(label = "Present"),
    ABSENT(label = "Absent"),
    UNMARKED(initialSelected = true, label = "Unmarked"),
}
data class Lesson(
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