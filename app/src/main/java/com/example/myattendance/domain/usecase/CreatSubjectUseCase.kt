package com.example.myattendance.domain.usecase

import com.example.myattendance.data.AttendanceRepository
import com.example.myattendance.data.model.Lesson
import com.example.myattendance.data.model.LessonDateTime

class CreatSubjectUseCase(val attendanceRepository: AttendanceRepository) {

    // Way of saying that this class will have only 1 method and 1 function which will be invoked
    // You dont need val inside paramters
    operator fun invoke(
        name: String,
        startDate: Long,
        endDate: Long,
        repeatDays: List<LessonDateTime>,
        lesson: List<Lesson>

    ) {

    }
}