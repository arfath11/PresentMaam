package com.example.myattendance.ui.home

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myattendance.ui.navigation.TopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun History(
    onNavigateToTLDClick: (TopLevelDestination) -> Unit,
    modifier: Modifier = Modifier,
    @StringRes title: Int
) {
    Text(text = "History")
}