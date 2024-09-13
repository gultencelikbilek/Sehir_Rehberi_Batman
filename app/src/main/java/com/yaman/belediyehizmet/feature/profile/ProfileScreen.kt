package com.yaman.belediyehizmet.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yaman.belediyehizmet.R
import com.yaman.belediyehizmet.components.PageHeader
import com.yaman.belediyehizmet.firebase.NotificationViewModel
import com.yaman.belediyehizmet.navigation.Screen
import com.yaman.belediyehizmet.navigation.dataStore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    darkTheme: MutableState<Boolean>,
    viewModel: NotificationViewModel = hiltViewModel()
) {
    val profileFromNavigate = listOf(
        GoToScreen(R.drawable.notification, "Bildirimler"),
        GoToScreen(R.drawable.restaurant, "Restorantlar"),
        GoToScreen(R.drawable.travel, "Gezilecek Yerler")
        //GoToScreen(R.drawable.weather, "Hava Durumu"),
        //  GoToScreen(R.drawable.businessman,"İş İlanları"),
    )
    var selectedItem by remember { mutableStateOf(profileFromNavigate[0]) }
    val context = LocalContext.current
    val appSettings by context.dataStore.data.collectAsState(initial = ThemeAppSettings())
    darkTheme.value = appSettings.isDarkTheme

    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                    PageHeader(text = stringResource(id = R.string.profile))
                },
                navigationIcon = {
                    // Switch(
                    //     checked = darkTheme.value,
                    //     onCheckedChange ={
                    //         darkTheme.value = !darkTheme.value
                    //         scope.launch {
                    //             context.dataStore.updateData { settings ->
                    //                 settings.copy(isDarkTheme = darkTheme.value)
                    //             }
                    //         }
                    // } )


                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            profileFromNavigate.forEach {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = {
                        selectedItem = it
                        navigateToDestinationProfile(navController, it)
                    }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(id = it.icon),
                            contentDescription = "",
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.CenterVertically)
                                .padding(start = 8.dp)
                        )
                        Spacer(modifier = Modifier.width(25.dp))
                        Text(
                            modifier = Modifier.padding(25.dp),
                            text = it.name,
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize
                            )
                        )
                    }
                }
            }
            Text(text = viewModel.notificationState.value.data.toString())
        }
    }
}

fun navigateToDestinationProfile(navController: NavController, item: GoToScreen) {
    when (item.name) {
      //  "Hava Durumu" -> navController.navigate(Screen.WeatherScreen.route)
        "Gezilecek Yerler" -> navController.navigate(Screen.TravelScreen.route)
        "Bildirimler" -> navController.navigate(Screen.NotificationScreen.route)
        "Restorantlar" -> navController.navigate(Screen.RestaurantScreen.route)
        // "İş İlanları" -> navController.navigate(Screen.JobPostingScreen.route)
    }
}

data class GoToScreen(
    val icon: Int,
    val name: String
)