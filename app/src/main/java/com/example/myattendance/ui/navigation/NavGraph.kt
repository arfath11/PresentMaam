package com.example.myattendance.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myattendance.ui.home.History
import com.example.myattendance.ui.home.HomeScreen
import com.example.myattendance.ui.home.Summary

@Composable
fun NavGraph(navController: NavHostController , padding:PaddingValues ,modifier:Modifier = Modifier){
    NavHost(
        navController = navController,
        startDestination = TopLevelDestination.HomeDestination.name,
        modifier = modifier
    ) {
        composable(route = TopLevelDestination.HomeDestination.name) {
            HomeScreen(
                onNavigateToTLDClick = {
                    navController.navigate(it.name)
                },

                title = TopLevelDestination.HomeDestination.titleTextId
            )
        }
        composable(route = TopLevelDestination.AllClassesDestination.name) {

            Summary(title = TopLevelDestination.AllClassesDestination.titleTextId,
                onNavigateToTLDClick = {
                    navController.navigate(it.name)
                }
            )

        }

        composable(route = TopLevelDestination.HistoryDestination.name) {
            History(title = TopLevelDestination.HistoryDestination.titleTextId,
                onNavigateToTLDClick = {
                    navController.navigate(it.name)
                }
            )
        }

        composable(route = AttendanceScreen.ClassEntryDestination.name) {
            TODO()
        }


        composable(route = AttendanceScreen.ClassDetailDestination.name) {
            TODO()
        }
    }


}