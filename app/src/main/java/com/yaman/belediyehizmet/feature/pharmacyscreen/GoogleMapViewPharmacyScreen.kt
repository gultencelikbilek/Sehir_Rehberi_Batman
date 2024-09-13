package com.yaman.belediyehizmet.feature.pharmacyscreen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.yaman.belediyehizmet.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GoogleMapViewPharmacyScreen(
    navController: NavController,
    darkTheme: MutableState<Boolean>,
    viewModel: PharmacyViewModel = hiltViewModel()

) {
    var isLoading by remember { mutableStateOf(true) }

    val pharmacyList = viewModel.pharmacyState.value
    LaunchedEffect(key1 = Unit) {
        delay(1000)
        isLoading = false
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = LocalContentColor.current,
                    modifier = Modifier.size(50.dp),
                    strokeWidth = 3.dp
                )
            } else {
                GoogleMapViewContent(pharmacyList,darkTheme)
            }
        }
    }
}

@Composable
fun GoogleMapViewContent(pharmacyList: PharmacyState, darkTheme: MutableState<Boolean>) {

    val state = LatLng(37.891313, 41.128989)
    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(state, 13f)
    }

    val mapProperties = remember { MapProperties(mapType = MapType.NORMAL) }
    var selectedMarker: Marker? by remember { mutableStateOf(null) }
    var selectedPharmacy: PharmacyItem? by remember { mutableStateOf(null) }
    GoogleMap(
        cameraPositionState = cameraPosition,
        properties = mapProperties
    ) {
        for (pharmacy in pharmacyList.data!! ) {
            val locationPositon = LatLng(pharmacy.latLong!!.latitude,pharmacy.latLong.longitude)
            val locationState = rememberMarkerState(position = locationPositon)
            Marker(
                state = locationState,
                draggable = true,
                onClick = { marker ->
                    selectedMarker = marker
                    selectedPharmacy = pharmacy
                    true
                },
                title = pharmacy.name
            )
        }
    }
    selectedMarker?.let { marker ->
        val title = marker.title ?: "Bilgi Yok"
        val icon = R.drawable.map
        val position = marker.position
        MarkerInfoPopup(title = title, icon = icon,selectedPharmacy,darkTheme)
    }
}

@Composable
fun MarkerInfoPopup(
    title: String,
    icon: Int,
    selectedPharmacy: PharmacyItem?,
    darkTheme: MutableState<Boolean>
) {
    Surface(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        color = if (darkTheme.value) Color.Black else Color.White
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = if (darkTheme.value) Color.White else  Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall ,
                color =  if (darkTheme.value) Color.White else  Color.Black,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            selectedPharmacy?.let {
                Text(
                    text = selectedPharmacy.address,
                    color =  if (darkTheme.value) Color.White else  Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            GoToMapButton(selectedPharmacy,darkTheme)
        }
    }
}

@Composable
fun GoToMapButton(selectedPharmacy: PharmacyItem?, darkTheme: MutableState<Boolean>) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val selectedCondolence = selectedPharmacy
    OutlinedButton(
        onClick = {
            coroutineScope.launch {
                selectedCondolence?.let { selectedCondolence ->
                    val latitude = selectedCondolence.latLong!!.latitude
                    val longitude = selectedCondolence.latLong.longitude
                    val markerTitle = selectedCondolence.name

                    val gmmIntentUri =
                        Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude($markerTitle)")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")

                    if (mapIntent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(mapIntent)
                    }
                }
            }
        }, modifier = Modifier.padding(8.dp),

    ) {
        Text(text = "Haritaya  Git",color =if (darkTheme.value) Color.White else  Color.Black,)
    }
}