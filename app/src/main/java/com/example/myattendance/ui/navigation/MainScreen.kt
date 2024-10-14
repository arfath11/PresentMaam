package com.example.myattendance.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myattendance.AttendanceTopAppBar
import com.example.myattendance.MyBottomBar
import com.example.myattendance.R


enum class AttendanceScreen(@StringRes val title: Int) {
    ClassDetailDestination(R.string.class_detail),
    ClassEntryDestination(R.string.add_subject),
    ClassEditDestination(R.string.edit_subject),
}

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    @StringRes val titleTextId: Int,
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
        iconTextId = R.string.summary,
        titleTextId = R.string.all_subjects,
    ),
    HistoryDestination(
        selectedIcon = Icons.Filled.DateRange,
        unselectedIcon = Icons.Outlined.DateRange,
        iconTextId = R.string.barhistory,
        titleTextId = R.string.history,
    ),
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceNavHost(navController: NavHostController, modifier: Modifier = Modifier) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    // currentScreen has default value of HomeDestination
    val currentScreen: TopLevelDestination = TopLevelDestination.valueOf(
        backStackEntry?.destination?.route ?: TopLevelDestination.HomeDestination.name
    )

    Scaffold(
        topBar = { AttendanceTopAppBar(title = currentScreen.titleTextId) },
        bottomBar = {
            MyBottomBar(
                onNavigateToTLDClick = {
                    navController.navigate(it.name) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                currentDestination = currentScreen
            )
        }
    ) {
        AppNavGraph(navController = navController, modifier = Modifier.padding(it))
    }


}
