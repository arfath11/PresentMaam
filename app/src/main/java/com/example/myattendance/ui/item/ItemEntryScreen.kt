package com.example.myattendance.ui.item

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
fun IteamEntryScreen(
    modifier: Modifier = Modifier,
    isSheetOpen: MutableState<Boolean>
) {
    if (isSheetOpen.value)
        AttendanceBottomSheet { isSheetOpen.value = false }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceBottomSheet(onDismissRequest: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismissRequest() }) {
        // TODO
        LazyColumn {
            // Add a single item
            item {
                Text(text = "Header")
            }

            // Add 3 items
            items(3) { index ->
                Text(text = "First List items : $index")
            }
            // Add 2 items
            items(2) { index ->
                Text(text = "Second List Items: $index")
            }
            // Add another single item
            item {
                Text(text = "Footer")
            }
        }
    }

}

@Composable
fun ItemEntry(modifier: Modifier) {
//TODO()

}