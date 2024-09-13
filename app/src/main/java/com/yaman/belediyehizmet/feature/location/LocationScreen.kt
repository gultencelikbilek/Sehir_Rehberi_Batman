package com.yaman.belediyehizmet.feature.location

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun LocationScreen(navController: NavController,darkTheme: MutableState<Boolean>) {
    CondolenceHouseScreen(navController = navController,darkTheme)
}

@Composable
fun CondolenceHouseScreen(
    navController: NavController,
    darkTheme: MutableState<Boolean>
) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(if (darkTheme.value) Color.Black else Color.White )

    ) {
        items(condolenceHouseList){condolenceHouse ->
            CondolenceCard(
                condolenceHouse, navController,
                darkTheme
            )
            Log.d("condolencehouse:", condolenceHouse.toString())
            Spacer(modifier = Modifier.height(16.dp))
        }

        }
    }


@Composable
fun CondolenceCard(
    condolenceHouse: CondolenceHouse, navController: NavController,darkTheme: MutableState<Boolean>
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Card(
        onClick = {
                val latitude = condolenceHouse.latitude
                val longitude = condolenceHouse.longtude
                val markerTitle = condolenceHouse.name
                val gmmIntentUri =
                    Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude($markerTitle)")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")

                if (mapIntent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(mapIntent)
                }
        }, colors = CardDefaults.cardColors(
            containerColor = if (darkTheme.value) Color.Black else Color.White
        ), elevation = CardDefaults.cardElevation(0.dp)
    ) {
        val painter = rememberAsyncImagePainter(model = condolenceHouse.image)

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(85.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
            Column(
                Modifier.weight(1f), verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = condolenceHouse.name,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .animateContentSize(animationSpec = tween(3000)),
                    color =  if (darkTheme.value) Color.White else Color.Black
                )
                Text(
                    text = condolenceHouse.adress,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    modifier = Modifier.animateContentSize(animationSpec = tween(9000))
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "",
                    tint = Color.LightGray,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        HorizontalDivider(modifier = Modifier.padding(top = 6.dp), color = Color.LightGray)
    }
}
