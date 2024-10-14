package com.example.myattendance.ui.itemlesson


import androidx.compose.material3.TimePicker
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myattendance.R
import com.example.myattendance.feature.extension.toFormattedDateString
import com.example.myattendance.ui.theme.MyAttendanceTheme
import java.time.DayOfWeek
import java.util.Calendar
import java.time.format.TextStyle
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceBottomSheet(onDismissRequest: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismissRequest() }) {
        // TODO
        ItemEntry()
    }

}


// Enter the lessonItem 
@Composable
fun ItemEntry(modifier: Modifier= Modifier) {

    var subjectName by remember { mutableStateOf("") }
    var minPer by remember { mutableStateOf("75") }
    var save by remember { mutableStateOf(false) }
    var selectedDay by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.add_subject),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.Start)
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
               // .size(400.dp),
        ){

            //To Enter Name of the  Subject
            EditNumberField(
                label = R.string.subject_name,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                value = subjectName ,
                onValueChanged = { subjectName = it },
                modifier = Modifier
                    .padding(bottom = 32.dp, end = 16.dp)
                    .fillMaxWidth(0.76f),
            )
            //To Enter Min percentage
            EditNumberField(
                label = R.string.min_attendnce,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = minPer ,
                onValueChanged = { minPer = it },
                modifier = Modifier.padding(bottom = 32.dp),
            )
        }

           // TO enter the start and end date
            LessonDateTextField(endDate = {},
                                label = R.string.start_date,
                                modifier = Modifier.padding(bottom = 32.dp)
            )

            LessonDateTextField(endDate = {},
                                label = R.string.end_date,
                                modifier = Modifier.padding(bottom = 32.dp)
            )
           // LessonDateTextField(endDate = {})

        LessonDayTextField(
            selectedDay = selectedDay,
            onDaySelected = { day ->
                selectedDay = day
            }
        )

// hrs -> Int , min -> Int .
       Button(onClick = {viewmodel.onNewTimeAdd()}) {
           
       }

// Start time   -    end time
       12.30          1.00
        2.00          3.00
                +


    }
}




//@Preview(showBackground = true)
@Composable
fun LessonDayTextField(
    selectedDay: Int,
    onDaySelected: (Int) -> Unit
) {
    val daysOfWeek = DayOfWeek.values()

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 0.dp),horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(daysOfWeek) { day ->
            Text(
                text = day.getDisplayName(TextStyle.SHORT, Locale.UK),
                modifier = Modifier
                    .clickable { onDaySelected(day.value) }
                    .padding(8.dp),
                color = if (day.value == selectedDay) Color.Blue else Color.Black
            )
        }
    }
}
@ExperimentalMaterial3Ap   vdsi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputExample(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    // For information on how to implement a time picker dialog, see the "Dialogs for time pickers" guide.
//    TimePickerDialog(
//        onDismiss = { onDismiss() },
//        onConfirm = { onConfirm(timePickerState) }
//    ) {
//        TimeInput(
//            state = timePickerState,
//        )
//    }

    val state = rememberTimePickerState()

//launch effect
    LaunchedEffect(key1 = state ) {
        state.

    }

    TimePicker(
        state = state,
        modifier = Modifier.padding(16.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonDateTextField(endDate: (Long) -> Unit,
                        @StringRes label: Int,
                        modifier: Modifier = Modifier) {

    Text(
        text = stringResource(id = label),
        style = MaterialTheme.typography.titleSmall,
        modifier= Modifier.padding(bottom = 4.dp)
    )


    var shouldDisplay by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
    if (isPressed) {
        shouldDisplay = true
    }

    val today = Calendar.getInstance()
    today.set(Calendar.HOUR_OF_DAY, 0)
    today.set(Calendar.MINUTE, 0)
    today.set(Calendar.SECOND, 0)
    today.set(Calendar.MILLISECOND, 0)
    val currentDayMillis = today.timeInMillis
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),

    )

    var selectedDate by rememberSaveable {
        mutableStateOf(
            datePickerState.selectedDateMillis?.toFormattedDateString() ?: ""
        )
    }


    LessonDatePickerDialog(
        state = datePickerState,
        shouldDisplay = shouldDisplay,
        onConfirmClicked = { selectedDateInMillis ->
            selectedDate = selectedDateInMillis.toFormattedDateString()
            endDate(selectedDateInMillis)
        },
        dismissRequest = {
            shouldDisplay = false
        }
    )

    TextField(
        modifier = modifier,
        readOnly = true,
        value = selectedDate,
        onValueChange = {},
        trailingIcon = { Icon(imageVector = Icons.Default.DateRange, contentDescription ="date range") },
        label = {stringResource(id = label)},
        interactionSource = interactionSource
    )
}
@Composable
fun EditNumberField(
    @StringRes label: Int,
 //   @DrawableRes leadingIcon: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        singleLine = true,
       // leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions
    )
}
@Preview(showBackground = true)
@Composable
fun ItemEntryPreview() {
    MyAttendanceTheme {
        ItemEntry()
    }
}