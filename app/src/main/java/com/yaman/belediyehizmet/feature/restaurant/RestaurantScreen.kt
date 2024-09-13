package com.yaman.belediyehizmet.feature.restaurant

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yaman.belediyehizmet.R
import com.yaman.belediyehizmet.components.BackIcon
import com.yaman.belediyehizmet.components.Header

@Composable
fun RestaurantScreen(navController: NavHostController, darkTheme: MutableState<Boolean>) {

   Scaffold(
       topBar = {
                TopAppBar(
                    navigationIcon = {
                        BackIcon(
                            onClick = {
                                navController.navigateUp()
                            })
                    },

                    title = {
                            Header(text = stringResource(id = R.string.restaurant))
                      },
                    backgroundColor = Color.White
                )
       },
       modifier = Modifier.fillMaxSize()
   ) {paddingValues ->
       LazyColumn(
           modifier = Modifier.padding(paddingValues)
       ) {
           items(restaurants){
               RestuarantCard(restuarant = it,darkTheme)
           }
       }
   }
}

@Composable
fun RestuarantCard(restuarant: Restaurant, darkTheme: MutableState<Boolean>) {
    val context = LocalContext.current
    androidx.compose.material3.Card(
        onClick = {
            val latitude = restuarant.latitude
            val longitude = restuarant.longtude
            val markerTitle = restuarant.name
            val gmmIntentUri =
                Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude($markerTitle)")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")

            if (mapIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(mapIntent)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (darkTheme.value) Color.Black else Color.White
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageComponent(restuarant)
            RestaurantNameText(restaurant = restuarant, darkTheme)
            LocationButton(modifier = Modifier.padding(end = 10.dp))


        }
    }
}
