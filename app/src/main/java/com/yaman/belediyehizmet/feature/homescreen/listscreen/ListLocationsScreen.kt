package com.yaman.belediyehizmet.feature.homescreen.listscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yaman.belediyehizmet.R
import kotlinx.coroutines.launch

@Composable
fun ListLocationsScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)) {
        places.forEach { place ->
            PlaceCard(place = place, navController = navController)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PlaceCard(place: Place, navController: NavController) {
    Card(
        onClick = {
           // navController.navigate("place_detail/${place.id}")
        }, shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = place.imageResId),
                contentDescription = place.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = place.name,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = place.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            val coroutineScope = rememberCoroutineScope()
            OutlinedButton(
                onClick = {
                    coroutineScope.launch {
                        // Handle navigation to map screen here
                    }
                }, modifier = Modifier.padding(8.dp)
            ) {
                Text(text = stringResource(id = R.string.go_to_map))
            }
        }
    }
}


data class Place(
    val id: Int, val name: String, val description: String, val imageResId: Int
)

val places = listOf(
    Place(1, "Place 1", "Description 1", R.drawable.batman_bel),
    Place(2, "Place 2", "Description 2", R.drawable.batman_bel),
    Place(3, "Place 3", "Description 3", R.drawable.batman_bel),
    Place(4, "Place 4", "Description 4", R.drawable.batman_bel),
    Place(5, "Place 5", "Description 5", R.drawable.batman_bel),
    Place(6, "Place 6", "Description 6", R.drawable.batman_bel),
    Place(7, "Place 7", "Description 7", R.drawable.batman_bel)
)
