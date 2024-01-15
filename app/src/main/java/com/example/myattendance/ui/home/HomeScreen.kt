package com.example.myattendance.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.example.myattendance.AttendanceTopAppBar
import com.example.myattendance.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier){




    // Need to experiment with this scrool behaviour
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()



    Scaffold(
        //need to be experimented with this scrool behavoiur
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AttendanceTopAppBar(
                title = stringResource(R.string.home_screen),
            canNavigateBack = false,
            scrollBehavior = scrollBehavior
            )
        },


        ) {


        Text(text = "arfath" , modifier = modifier.padding(it))

    }





}