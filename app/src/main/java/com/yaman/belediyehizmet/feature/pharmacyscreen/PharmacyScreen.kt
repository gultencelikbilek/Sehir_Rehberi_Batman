package com.yaman.belediyehizmet.feature.pharmacyscreen

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.yaman.belediyehizmet.R
import kotlinx.coroutines.launch

@Composable
fun PharmacyScreen(
    navController: NavController,
    darkTheme: MutableState<Boolean>,
    viewModel: PharmacyViewModel = hiltViewModel()


) {
    val pharmacyList = viewModel.pharmacyState.value
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        if (pharmacyList.isLoading) {
            CircularProgressIndicator()
        } else {
            val pharmacyListData = pharmacyList.data
            val filterList = pharmacyListData?.filter { it.city.equals("BATMAN", false) }
            PharmacyScreen(navController = navController, pharmacyListData,darkTheme)
            Log.d("phaermacylist:", pharmacyList.toString())
        }
    }
}

@Composable
fun PharmacyScreen(
    navController: NavController, pharmacyListData: List<PharmacyItem>?, darkTheme: MutableState<Boolean>
) {
    pharmacyListData?.reversed()?.forEach { pharmacyItem ->
        PharmacyCard(pharmacyItem, navController,darkTheme)
        Log.d("condolencehouse:", pharmacyItem.toString())
        Spacer(modifier = Modifier.height(4.dp))
    }
}


@Composable
fun PharmacyCard(pharmacy: PharmacyItem, navController: NavController, darkTheme: MutableState<Boolean>) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (darkTheme.value) Color.Black else Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            val painter = rememberAsyncImagePainter(
                model = "https://www.batmaneczaciodasi.org.tr/images/epano.gif"
            )
            Image(
                painter = painter,
                contentDescription = "Pharmacy image",
                modifier = Modifier
                    .align(Alignment.Top)
                    .size(90.dp)
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = pharmacy.name,
                    color = Color(0xFFFF5620),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = pharmacy.address, fontSize = 10.sp, color = if (darkTheme.value) Color.White else Color.Black
                )
                Row(

                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.phone_call),
                        contentDescription = "Phone",
                        tint = Color(0xFFFF5620),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                // Arama işlevselliği
                                val intent = Intent(Intent.ACTION_DIAL)
                                intent.data = Uri.parse("tel:${pharmacy.phone}")
                                context.startActivity(intent)
                            }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = pharmacy.phone, fontSize = 10.sp, color = if (darkTheme.value) Color.White else Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    OutlinedButton(
                        onClick = {
                            coroutineScope.launch {
                                val latitude = pharmacy.latLong!!.latitude
                                val longitude = pharmacy.latLong.longitude
                                val markerTitle = pharmacy.name
                                val gmmIntentUri =
                                    Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude($markerTitle)")
                                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                                    setPackage("com.google.android.apps.maps")
                                }
                                if (mapIntent.resolveActivity(context.packageManager) != null) {
                                    context.startActivity(mapIntent)
                                }
                            }
                        }, modifier = Modifier.wrapContentWidth()
                    ) {
                        Text(
                            text = "Yol Tarifi", fontSize = 12.sp, color = Color(0xFFFF5620)
                        )
                    }
                }
            }
        }
    }
}
