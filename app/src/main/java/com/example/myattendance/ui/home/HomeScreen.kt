package com.example.myattendance.ui.home


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myattendance.AttendanceTopAppBar
import com.example.myattendance.MyBottomBar
import com.example.myattendance.R
import com.example.myattendance.ui.navigation.TopLevelDestination
import com.example.myattendance.ui.theme.MyAttendanceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateToTLDClick: (TopLevelDestination) -> Unit,
               modifier: Modifier = Modifier,
               @StringRes title : Int ){

    // Need to experiment with this Scroll behaviour
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        //need to be experimented with this Scroll behaviour
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AttendanceTopAppBar(
                title = stringResource(R.string.home_screen),
            canNavigateBack = false,
            scrollBehavior = scrollBehavior
            )
        },

        bottomBar =  {
            MyBottomBar(onNavigateToTLDClick =  { onNavigateToTLDClick(it) })
        }) {


        Text(text = "arfath" , modifier = modifier.padding(it).padding(40.dp))

    }

}

@Preview(showBackground = true )
@Composable
fun HomeScreenPreview(){
    MyAttendanceTheme(darkTheme = true){
       // HomeScreen()
    }


}