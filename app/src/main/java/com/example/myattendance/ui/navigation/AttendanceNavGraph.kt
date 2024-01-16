package com.example.myattendance.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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



enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HomeDestination(
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = R.string.home_bar,
        titleTextId = R.string.app_name,
    ),
    AllClassesDestination(
        selectedIcon = Icons.Filled.List,
        unselectedIcon = Icons.Outlined.List,
        iconTextId = R.string.all_Class,
        titleTextId = R.string.all_class,
    ),
    HistoryDestination(
        selectedIcon = Icons.Filled.DateRange,
        unselectedIcon = Icons.Outlined.DateRange,
        iconTextId = R.string.barhistory,
        titleTextId = R.string.history,
    ),
}




@Composable
fun AttendanceNavHost(navController: NavHostController ,  modifier: Modifier = Modifier,){

    NavHost( navController = navController,
             startDestination = AttendanceScreen.HomeDestination.name ,
             modifier = modifier ){


            composable(route = TopLevelDestination.HomeDestination.name ){
                HomeScreen()
            }

            composable(route = AttendanceScreen.ClassEntryDestination.name ){
                TODO()
            }


            composable(route = AttendanceScreen.ClassDetailDestination.name ){
                TODO()
            }



    }





}
