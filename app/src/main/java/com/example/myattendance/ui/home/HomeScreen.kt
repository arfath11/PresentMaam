package com.example.myattendance.ui.home


import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myattendance.AppFAB
import com.example.myattendance.R
import com.example.myattendance.feature.home.data.CalendarDataSource
import com.example.myattendance.feature.home.data.CalendarModel
import com.example.myattendance.feature.extension.toFormattedDateShortString
import com.example.myattendance.feature.extension.toFormattedDateString
import com.example.myattendance.feature.extension.toFormattedMonthDateString
import com.example.myattendance.model.AttendanceStatus
import com.example.myattendance.model.Lesson
import com.example.myattendance.ui.item.IteamEntryScreen
import com.example.myattendance.ui.navigation.TopLevelDestination
import com.example.myattendance.ui.theme.MyAttendanceTheme
import java.util.Calendar
import java.util.Date
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToTLDClick: (TopLevelDestination) -> Unit,
    modifier: Modifier = Modifier,
    @StringRes title: Int
) {
    // to hold state of BottomSheet
    var isSheetOpen = rememberSaveable {
        mutableStateOf(false)
    }
    Scaffold(
        floatingActionButton = { AppFAB { isSheetOpen.value = true } },
    ) {
        Column(
            modifier = Modifier.padding(it),
            //  verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            DatesHeader { }
            LessonList(subjectList = createRandomSubjects())

            //BottomSheet to add/edit elements , Triggered on floatingActionButton
            IteamEntryScreen(isSheetOpen = isSheetOpen)
        }
    }
}


@Composable
fun LessonList(
    modifier: Modifier = Modifier,
    subjectList: List<Lesson>
) {

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

        subjectList.forEach { subject ->
            LessonItem(
                subject = subject,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

    }


}


@Composable
fun LessonItem(subject: Lesson, modifier: Modifier = Modifier) {

    val progressBarColor = if (subject.attendancePer < 0.75) Color.Red else Color.Green
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
        label = "",
    )

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.animateContentSize(
                         animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessMedium
                           )
                        ),
        colors = CardDefaults.cardColors(containerColor = color),
        ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = subject.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "${(subject.attendancePer * 100).toInt()}%",
                    style = MaterialTheme.typography.titleMedium,
                    color = progressBarColor
                )
            }
            DropDownButton(expanded = expanded, onClick = { expanded = !expanded })
        }

        LinearProgressIndicator(
            progress = subject.attendancePer,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 10.dp)
                .fillMaxWidth(),
            color = progressBarColor
        )
        //To select if PRESENT , ABSENT or UNMARKED
        if (expanded) {
            LessonAttendanceStatus(modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun LessonAttendanceStatus(modifier: Modifier = Modifier) {
    Row (modifier = modifier.fillMaxWidth() ,horizontalArrangement  =  Arrangement.SpaceAround) {

        AttendanceStatus.values()
                         .forEach {
                             StatusFilterChip(onClick = it.onClick, label =  it.label ,initiallySelected = it.initialSelected)
                         }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusFilterChip(modifier: Modifier = Modifier,
                     initiallySelected: Boolean ,
                     onClick: () -> Unit ,
                     label: String){

    var selected by remember{ mutableStateOf(initiallySelected) }
    FilterChip(selected = selected ,
               onClick = {
                           onClick()
                           selected = !selected
                        },
              label = { Text(text = label) })

}

@Composable
fun DropDownButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(

            imageVector = if (!expanded) Icons.Filled.ExpandMore else Icons.Filled.ExpandLess,
            contentDescription = stringResource(R.string.expand_button_lesson_status),
            tint = MaterialTheme.colorScheme.secondary
        )


    }

}

// Scrollable dates  for the user to select from
@Composable
fun DatesHeader(
    //  analyticsHelper: AnalyticsHelper,
    onDateSelected: (CalendarModel.DateModel) -> Unit // Callback to pass the selected date
) {
    val dataSource = CalendarDataSource()
    var calendarModel by remember { mutableStateOf(dataSource.getData(lastSelectedDate = dataSource.today)) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        DateHeader(
            data = calendarModel,
            onPrevClickListener = { startDate ->
                // refresh the CalendarModel with new data
                // by get data with new Start Date (which is the startDate-1 from the visibleDates)
                val calendar = Calendar.getInstance()
                calendar.time = startDate

                calendar.add(Calendar.DAY_OF_YEAR, -2) // Subtract one day from startDate
                val finalStartDate = calendar.time

                calendarModel = dataSource.getData(
                    startDate = finalStartDate,
                    lastSelectedDate = calendarModel.selectedDate.date
                )
                //analyticsHelper.logEvent(AnalyticsEvents.HOME_CALENDAR_PREVIOUS_WEEK_CLICKED)
            },
            onNextClickListener = { endDate ->
                // refresh the CalendarModel with new data
                // by get data with new Start Date (which is the endDate+2 from the visibleDates)
                val calendar = Calendar.getInstance()
                calendar.time = endDate

                calendar.add(Calendar.DAY_OF_YEAR, 2)
                val finalStartDate = calendar.time

                calendarModel = dataSource.getData(
                    startDate = finalStartDate,
                    lastSelectedDate = calendarModel.selectedDate.date
                )
                //  analyticsHelper.logEvent(AnalyticsEvents.HOME_CALENDAR_NEXT_WEEK_CLICKED)
            }
        )
        DateList(
            data = calendarModel,
            onDateClickListener = { date ->
                calendarModel = calendarModel.copy(
                    selectedDate = date,
                    visibleDates = calendarModel.visibleDates.map {
                        it.copy(
                            isSelected = it.date.toFormattedDateString() == date.date.toFormattedDateString()
                        )
                    }
                )
                onDateSelected(date)
            }
        )
    }
}


@Composable
fun DateHeader(
    data: CalendarModel,
    onPrevClickListener: (Date) -> Unit,
    onNextClickListener: (Date) -> Unit
) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = if (data.selectedDate.isToday) {
                stringResource(R.string.today)
            } else {
                data.selectedDate.date.toFormattedMonthDateString()
            },
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            // color = MaterialTheme.colorScheme.tertiary
        )
        IconButton(onClick = {
            onPrevClickListener(data.startDate.date)
        }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                // tint = MaterialTheme.colorScheme.tertiary,
                contentDescription = "Back"
            )
        }
        IconButton(onClick = {
            onNextClickListener(data.endDate.date)
        }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                //   tint = MaterialTheme.colorScheme.tertiary,
                contentDescription = "Next"
            )
        }
    }
}


@Composable
fun DateList(
    data: CalendarModel,
    onDateClickListener: (CalendarModel.DateModel) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(items = data.visibleDates) { date ->
            DateItem(date, onDateClickListener)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateItem(
    date: CalendarModel.DateModel,
    onClickListener: (CalendarModel.DateModel) -> Unit,
) {
    Column {
        Text(
            text = date.day, // day "Mon", "Tue"
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.outline
        )
        Card(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 4.dp),
            onClick = { onClickListener(date) },
            colors = CardDefaults.cardColors(
                // background colors of the selected date
                // and the non-selected date are different
                containerColor = if (date.isSelected) {
                    MaterialTheme.colorScheme.tertiary
                } else {
                    MaterialTheme.colorScheme.surface
                }
            ),
        ) {
            Column(
                modifier = Modifier
                    .width(42.dp)
                    .height(42.dp)
                    .padding(8.dp)
                    .fillMaxSize(), // Fill the available size in the Column
                verticalArrangement = Arrangement.Center, // Center vertically
                horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
            ) {
                Text(
                    text = date.date.toFormattedDateShortString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = if (date.isSelected) {
                        FontWeight.Medium
                    } else {
                        FontWeight.Normal
                    }
                )
            }
        }
    }
}

fun createRandomSubjects(): List<Lesson> {
    val subjectNames = listOf(
        "Math",
        "English",
        "Science",
        "History",
        "Computer Science",
        "Physics",
        "Chemistry",
        "Biology"
    )

    return subjectNames.map {
        val numberOfPresent = Random.nextInt(1, 25)

        Lesson(
            name = it,
            numberOfPresent = numberOfPresent,
            totalClasses = Random.nextInt(numberOfPresent, 30)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyAttendanceTheme(darkTheme = false) {
        // HomeScreen()
        DatesHeader({})
    }
}

@Preview(showBackground = true)
@Composable
fun LessonListPreview() {
    MyAttendanceTheme(darkTheme = true) {
        LessonList(subjectList = createRandomSubjects())
//        SubjectItem(subject = Subject(
//            name = "Math",
//            numberOfPresent = 20,
//            totalClasses = 20
//        )
//        )
    }

}


@Preview(showBackground = true)
@Composable
fun LessonListItemPreview() {
    MyAttendanceTheme(darkTheme = true) {

        LessonItem(
            subject = Lesson(
                name = "Math",
                numberOfPresent = 20,
                totalClasses = 20
            )
        )
    }

}