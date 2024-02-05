package com.example.myattendance.ui.itemlesson

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myattendance.R
import com.example.myattendance.ui.theme.MyAttendanceTheme


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

@Composable
fun ItemEntry(modifier: Modifier= Modifier) {

    var subjectName by remember { mutableStateOf("") }
    var minPer by remember { mutableStateOf("") }
    var save by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
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


            EditNumberField(
                label = R.string.subject_name,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                value = subjectName ,
                onValueChanged = { subjectName = it },
                modifier = Modifier.padding(bottom = 32.dp, end = 16.dp).fillMaxWidth(0.76f),
            )

            EditNumberField(
                label = R.string.min_attendnce,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                value = minPer ,
                onValueChanged = { minPer = it },
                modifier = Modifier.padding(bottom = 32.dp),
            )
        }



    }
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
        modifier = modifier.clip(shapes.medium),
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