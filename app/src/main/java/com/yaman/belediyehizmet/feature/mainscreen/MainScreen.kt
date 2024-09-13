package com.yaman.belediyehizmet.feature.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.navigation.compose.rememberNavController
import com.yaman.belediyehizmet.navigation.AppBottomNavigation
import com.yaman.belediyehizmet.navigation.NavigationGraph


@Composable
fun MainScreen(darkTheme: MutableState<Boolean>) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            AppBottomNavigation(navController,darkTheme)
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            NavigationGraph(navController = navController, darkTheme,)
        }

    }
}