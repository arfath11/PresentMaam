package com.example.myattendance.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myattendance.AttendanceTopAppBar
import com.example.myattendance.MyBottomBar
import com.example.myattendance.ui.navigation.TopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Summary(onNavigateToTLDClick: (TopLevelDestination) -> Unit,
            modifier: Modifier = Modifier,
            @StringRes title : Int ){



    Text(text = "Summary" )








}