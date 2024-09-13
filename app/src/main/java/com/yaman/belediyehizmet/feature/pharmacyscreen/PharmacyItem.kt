package com.yaman.belediyehizmet.feature.pharmacyscreen

import com.google.android.gms.maps.model.LatLng


data class PharmacyItem(
    val name: String = "",
    val phone: String = "",
    val address: String = "",
    val locationUrl: String = "",
    val latLong:LatLng? = null,
    val city: String = "",
    val imageUrl:String="",
    )
