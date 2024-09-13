package com.yaman.belediyehizmet.feature.weather

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yaman.belediyehizmet.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherScreen(navController: NavController, darkTheme: MutableState<Boolean>) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        androidx.compose.material.Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0D8AEE)
                ),
                title = {
                    androidx.compose.material.Text(
                        text = "Hava Durumu",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }

            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                Color(0xFF0D8AEE),
                                Color(0xFF167CCC),
                                Color(0xFF0A5EA2),
                                Color(0xFF022744)
                            )
                        )
                    )
            ) {
                Header()
                Spacer(modifier = Modifier.height(50.dp))
                Degree()
                Spacer(modifier = Modifier.height(30.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    LazyRow {
                        items(weatherList) {
                            WeatherList(it)
                        }
                    }
                }
                WeekendWeather(weatherList)
            }
        }
    )
}


@Composable
fun WeekendWeather(weatherList: List<WeatherItem>) {
    Column(
        modifier = Modifier.padding(start = 30.dp)

    ) {
        Row(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(text = "Pazartesi", color = Color.White)
            Spacer(modifier = Modifier.width(80.dp))
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(50.dp))
            Text(text = "15°C", color = Color.White)
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "11", color = Color.White)

        }
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(text = "Salı", color = Color.White)
            Spacer(modifier = Modifier.width(125.dp))
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(50.dp))
            Text(text = "15°C", color = Color.White)
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "11", color = Color.White)

        }
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(text = "Çarşamba", color = Color.White)
            Spacer(modifier = Modifier.width(80.dp))
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(50.dp))
            Text(text = "15°C", color = Color.White)
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "11", color = Color.White)

        }
    }
}

@Composable
fun WeatherList(weatherItem: WeatherItem) {

    Row(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xFF7DC2F8),
                        Color(0xFF7DC2F8),
                        Color(0xFF7DC2F8),
                        Color(0xFF7DC2F8)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = weatherItem.hour, color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            Icon(
                painter = painterResource(id = weatherItem.icon),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 5.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = weatherItem.degree, color = Color.White)
        }
    }
}


@Composable
fun Degree() {
    Row {

        Column {
            Text(
                text = "Bugün",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.padding(start = 25.dp),
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "Hissedilen 8°",
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                modifier = Modifier.padding(start = 25.dp),
                fontWeight = FontWeight.ExtraBold
            )
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.wind),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 25.dp)
                        .size(24.dp)
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = "12 km / s", color = Color.White)
            }
        }
        Spacer(modifier = Modifier.width(50.dp))
        Image(
            painter = painterResource(id = R.drawable.cloudy),
            contentDescription = "",
            modifier = Modifier.size(130.dp)
        )
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Text(
        text = "Batman",
        style = MaterialTheme.typography.headlineSmall,
        color = Color.White,
        modifier = Modifier.padding(start = 25.dp, top = 50.dp),
        fontWeight = FontWeight.ExtraBold
    )
    Text(
        text = "19 Mayıs",
        style = MaterialTheme.typography.bodyMedium,
        color = Color.White,
        modifier = Modifier.padding(start = 25.dp),
        fontWeight = FontWeight.Bold
    )
}


