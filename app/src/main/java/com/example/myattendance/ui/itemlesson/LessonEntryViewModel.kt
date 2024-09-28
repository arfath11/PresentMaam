package com.example.myattendance.ui.itemlesson

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

//construction inovkation
class LessonEntryViewModel: ViewModel() {

    private  val _selectedDay = MutableStateFlow(1)
       val selectedDay = _selectedDay.asStateFlow()




// function for day selected -> change selected day and time range
// mutuable state flow for time range

    fun onNewTimeAdd(){

    }


}