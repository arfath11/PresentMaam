package com.example.myattendance.ui.itemlesson.model

data class Lesson(val lessonName: String , val minPer: Int , val monday: List<TimeRang > , val tuesday: List<TimeRang > , val wednesday: List<TimeRang > , val thursday: List<TimeRang > , val friday: List<TimeRang >)
