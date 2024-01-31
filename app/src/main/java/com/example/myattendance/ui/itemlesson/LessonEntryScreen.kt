package com.example.myattendance.ui.itemlesson

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceBottomSheet(onDismissRequest: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismissRequest() }) {
        // TODO
      Column{
            // Add a single item
          Text(text = "arafetht ")
          Text(text = "arafetht ")
          Text(text = "arafetht ")
          Text(text = "arafetht ")
          Text(text = "arafetht ")


      }
    }

}

@Composable
fun ItemEntry(modifier: Modifier) {
//TODO()

}