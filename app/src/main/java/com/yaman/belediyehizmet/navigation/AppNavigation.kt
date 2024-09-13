package com.yaman.belediyehizmet.navigation

import NewsDetailScreen
import ThemeAppSettingSerializer
import android.content.Context
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.datastore.dataStore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.yaman.belediyehizmet.R
import com.yaman.belediyehizmet.feature.homescreen.MapScreen
import com.yaman.belediyehizmet.feature.homescreen.listscreen.ListLocationsScreen
import com.yaman.belediyehizmet.feature.jobposting.JobPostingScreen
import com.yaman.belediyehizmet.feature.location.CondolenceHouseScreen
import com.yaman.belediyehizmet.feature.location.GoogleMapViewScreen
import com.yaman.belediyehizmet.feature.location.LocationScreen
import com.yaman.belediyehizmet.feature.location.LocationTabScreen
import com.yaman.belediyehizmet.feature.news.NewsScreen
import com.yaman.belediyehizmet.feature.news.NewsViewModel
import com.yaman.belediyehizmet.feature.notification.MessageNotification
import com.yaman.belediyehizmet.feature.pharmacyscreen.GoogleMapViewPharmacyScreen
import com.yaman.belediyehizmet.feature.pharmacyscreen.PharmacyScreen
import com.yaman.belediyehizmet.feature.pharmacyscreen.PharmacyTabScreen
import com.yaman.belediyehizmet.feature.profile.ProfileScreen
import com.yaman.belediyehizmet.feature.profile.ThemeAppSettings
import com.yaman.belediyehizmet.feature.restaurant.RestaurantScreen
import com.yaman.belediyehizmet.feature.travel.TravelScreen
import com.yaman.belediyehizmet.feature.weather.WeatherScreen
import com.yaman.belediyehizmet.feature.webviewscreen.WebViewScreen

sealed class BottomNavItem(
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val label: String
) {
    object Home :
        BottomNavItem(Screen.HomeScreen.route, R.drawable.home_full, R.drawable.home, "Anasayfa")

    object NewsScreen :
        BottomNavItem(Screen.NewsScreen.route, R.drawable.news_full, R.drawable.news, "Haberler")

    object Condolence : BottomNavItem(
        Screen.LocationTabScreen.route,
        R.drawable.location_full,
        R.drawable.location,
        "TaziyeEvleri"
    )

    object Pharmacy : BottomNavItem(
        Screen.PharmacyTabScreen.route,
        R.drawable.pharmacy_full,
        R.drawable.pharmacy,
        "Eczane"
    )

    object Profile : BottomNavItem(
        Screen.ProfileScreen.route,
        R.drawable.profile_full,
        R.drawable.profile,
        "Profil"
    )

}

@Composable
fun AppBottomNavigation(navController: NavController, darkTheme: MutableState<Boolean>,  newsViewModel: NewsViewModel = hiltViewModel()) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.NewsScreen,
        BottomNavItem.Condolence,
        BottomNavItem.Pharmacy,
        BottomNavItem.Profile
    )

    androidx.compose.material.BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBar(
            modifier = Modifier
                .width(500.dp)
                .height(56.dp),
            containerColor = if (darkTheme.value) Color.Black else Color.White,
        ) {
            items.forEach { item ->
                val isSelected = currentRoute == item.route
                val color = if (isSelected) {
                    if (darkTheme.value) Color.White else Color.Black
                } else {
                    Color.Gray
                }
                val targetColor by animateColorAsState(targetValue = color)
                val targetFontWeight =
                    if (isSelected) FontWeight.ExtraBold else FontWeight.Bold
                val icon = if (isSelected) item.selectedIcon else item.unselectedIcon

                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = item.label,
                            tint = targetColor,
                        )
                    },
                    modifier = Modifier.size(18.dp),
                    label = {
                        Text(
                            text = item.label,
                            fontSize = 7.sp,
                            color = targetColor,
                            fontWeight = targetFontWeight
                        )
                    },
                    selectedContentColor = if (darkTheme.value) Color.White else Color.Black,
                    unselectedContentColor = Color.Gray,
                    alwaysShowLabel = true,
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                        if (item.route == BottomNavItem.NewsScreen.route) {
                            newsViewModel.fetchNews()
                        }
                    }
                )
            }
        }
    }
}

val Context.dataStore: DataStore<ThemeAppSettings> by dataStore(
    fileName = "theme_app_settings.json",
    serializer = ThemeAppSettingSerializer
)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    darkTheme: MutableState<Boolean>
) {

    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {

            composable(Screen.HomeScreen.route) {
                WebViewScreen(navController = navController)
            }

            composable(Screen.NewsScreen.route) {
                NewsScreen(
                    animatedVisibilityScope = this,
                    navController = navController,
                    onShowDetails = { route ->
                        navController.navigate(route)
                    },
                    darkTheme
                )
            }
            composable(
                route = Screen.MapScreen.route,
            ) {
                MapScreen(navController = navController)
            }

            composable(
                route = Screen.ListLocationScreen.route,
            ) {
                ListLocationsScreen(navController = navController)
            }

            composable(Screen.NotificationScreen.route) {
                MessageNotification(
                    navController = navController,
                    darkTheme = darkTheme
                )
            }

            composable(route = Screen.CondolenceHouseScreen.route) {
                CondolenceHouseScreen(navController = navController, darkTheme)
            }
            composable(
                route = Screen.GoogleMapViewScreen.route
            ) { navBackStackEntry ->
                GoogleMapViewScreen(navController = navController, darkTheme)
            }
            composable(route = Screen.LocationScreen.route) {
                LocationScreen(navController = navController, darkTheme)
            }
            composable(route = Screen.LocationTabScreen.route) {
                LocationTabScreen(navController = navController, darkTheme)
            }
            composable(route = Screen.PharmacyScreen.route) {
                PharmacyScreen(navController = navController, darkTheme)
            }
            composable(route = Screen.PharmacyTabScreen.route) {
                PharmacyTabScreen(navController = navController, darkTheme)
            }
            composable(route = Screen.GoogleMapViewPharmacyScreen.route) {
                GoogleMapViewPharmacyScreen(navController = navController, darkTheme)
            }
            composable(
                route = Screen.NewsDetailScreen.route +
                        "?imageUrl={imageUrl}&title={title}&description={description}&date={date}&pageDetailUrl={pageDetailUrl}",
                arguments = listOf(
                    navArgument("imageUrl") { type = NavType.StringType },
                    navArgument("title") { type = NavType.StringType },
                    navArgument("description") { type = NavType.StringType },
                    navArgument("date") { type = NavType.StringType },
                    navArgument("pageDetailUrl") { type = NavType.StringType },
                )
            ) { navBackEntry ->
                NewsDetailScreen(
                    animatedVisibilityScope = this,
                    navController = navController,
                    imageUrl = navBackEntry.arguments?.getString("imageUrl"),
                    title = navBackEntry.arguments?.getString("title"),
                    description = navBackEntry.arguments?.getString("description"),
                    date = navBackEntry.arguments?.getString("date"),
                    pageDetailUrl = navBackEntry.arguments?.getString("pageDetailUrl")
                )
            }
            composable(Screen.WeatherScreen.route) {
                WeatherScreen(navController, darkTheme)
            }

            composable(Screen.TravelScreen.route) {
                TravelScreen(navController, darkTheme)
            }
            composable(Screen.ProfileScreen.route) {
                ProfileScreen(navController = navController, darkTheme = darkTheme)
            }
            composable(Screen.RestaurantScreen.route) {
                RestaurantScreen(navController, darkTheme)
            }
            composable(Screen.JobPostingScreen.route) {
                JobPostingScreen(navController = navController, darkTheme = darkTheme)
            }
        }
    }
}