package com.yaman.belediyehizmet.feature.restaurant

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImageComponent(restaurant: Restaurant) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .padding(5.dp)
    ) {
        val painter = rememberAsyncImagePainter(model = restaurant.image)
        Image(
            painter = painter,
            contentDescription = restaurant.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    }
}

@Composable
fun RestaurantNameText(restaurant: Restaurant, darkTheme: MutableState<Boolean>) {
    Text(
        text = restaurant.name,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        maxLines = 2,
        color = if (darkTheme.value) Color.White else Color.Black,
        modifier = Modifier.padding(top = 8.dp).width(205.dp)
    )
}
@Composable
fun LocationButton(modifier: Modifier = Modifier) {
    IconButton(
        modifier = modifier,
        onClick = {}) {
        Icon(
            imageVector = Icons.Filled.LocationOn,
            contentDescription = "",
            tint = Color.LightGray,
            modifier = Modifier.size(20.dp)
        )
    }

}