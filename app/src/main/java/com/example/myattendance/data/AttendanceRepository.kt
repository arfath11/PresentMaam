package com.example.myattendance.data

interface AttendanceRepository {
    suspend fun getTodaysClasses()
    suspend fun getAllClasses()
    suspend fun getClass()
}

class LocalAttendanceRepository : AttendanceRepository {

    override suspend fun getTodaysClasses() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllClasses() {
        TODO("Not yet implemented")
    }

    override suspend fun getClass() {
        TODO("Not yet implemented")
    }
}
