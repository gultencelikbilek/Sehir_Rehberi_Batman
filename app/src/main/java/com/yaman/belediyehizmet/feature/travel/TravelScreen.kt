package com.yaman.belediyehizmet.feature.travel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.yaman.belediyehizmet.R
import com.yaman.belediyehizmet.components.BackIcon
import com.yaman.belediyehizmet.components.Header

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelScreen(navController: NavController,darkTheme: MutableState<Boolean>) {
    Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                navigationIcon = {
                    BackIcon(
                        onClick = {
                            navController.navigateUp()
                        })
                },

                title = {
                    Header(text = stringResource(id = R.string.travel_header))
                },
                backgroundColor = Color.White
            )
        }){paddingValues ->

        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(travelList) {
                TravelCard(it,darkTheme)
            }
        }
    }

}

@Composable
fun TravelCard(travelItem: TravelItem,darkTheme: MutableState<Boolean>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (darkTheme.value) Color.Black else Color.White
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row {
            val painter = rememberAsyncImagePainter(model = travelItem.image)
            Image(
                painter = painter,
                contentDescription = travelItem.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))

            )
            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(80.dp),
            ) {
                Text(
                    text = travelItem.name,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    color = if (darkTheme.value) Color.White else Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = travelItem.adress,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    maxLines = 2
                )
            }
        }
    }
}


