package com.example.myattendance.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myattendance.R
import com.example.myattendance.ui.home.HomeScreen


enum class AttendanceScreen(@StringRes val title: Int){

    HomeDestination(R.string.home_screen),
    ClassDetailDestination(R.string.class_detail),
    ClassEntryDestination(R.string.add_class),
    ClassEditDestination(R.string.edit_class),

    AllClassesDestination(R.string.all_class),
    HistoryDestination(R.string.history)


}
@Composable
fun AttendanceNavHost(navController: NavHostController ,  modifier: Modifier = Modifier,){


    NavHost(navController = navController,
          startDestination = AttendanceScreen.HomeDestination.name ,
          modifier = modifier ){


        composable(route = AttendanceScreen.HomeDestination.name ){

            HomeScreen()


        }


    }





}
