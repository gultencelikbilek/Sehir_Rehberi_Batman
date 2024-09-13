package com.yaman.belediyehizmet.navigation

sealed class Screen(val route: String) {

    object HomeScreen : Screen(route = "home_screen")
    object NotificationScreen : Screen(route = "notification_screen")
    object MapScreen : Screen(route = "maps_screen")
    object ListLocationScreen : Screen(route = "list_location_screen")
    object TabScreen : Screen(route = "tab_screen")
    object NewsScreen : Screen(route = "news_screen")
    object NewsDetailScreen : Screen(route = "news_detail_screen")
    object CondolenceHouseScreen : Screen(route = "condolence_house_screen")
    object GoogleMapViewScreen : Screen(route = "google_map_view_screen")
    object LocationScreen : Screen(route = "location_screen")
    object LocationTabScreen : Screen(route = "location_tab_screen")
    object PharmacyTabScreen : Screen(route = "pharmacy_tab_screen")
    object PharmacyScreen : Screen(route = "pharmacy_screen")
    object GoogleMapViewPharmacyScreen : Screen(route = "google_map_view_pharmacy_screen")
    object WeatherScreen : Screen(route = "weather_screen")
    object TravelScreen : Screen(route = "travel_screen")
    object ProfileScreen : Screen(route = "profile_screen")
    object RestaurantScreen : Screen(route = "restaurant_screen")
    object JobPostingScreen : Screen(route = "job_posting_screen")

    /*
        object VerifyPassword : Screen(route = "verify_password/?profileId={profileId}") {
           fun profileId(profileId: String) = "verify_password/?profileId=$profileId"
        }
    */
}