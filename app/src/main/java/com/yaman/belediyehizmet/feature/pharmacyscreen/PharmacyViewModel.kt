package com.yaman.belediyehizmet.feature.pharmacyscreen

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class PharmacyViewModel @Inject constructor() : ViewModel() {

    private val _pharmacyState = mutableStateOf(PharmacyState())
    val pharmacyState: State<PharmacyState> = _pharmacyState

    init {
        fetchPharmacyData()
    }

    private fun fetchPharmacyData() {
        Log.v("eczaneler", "fetchPharmacyData")

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val pharmacyList = mutableListOf<PharmacyItem>()
                val doc =
                    Jsoup.connect("https://www.batmaneczaciodasi.org.tr/nobetci-eczaneler").get()
                val div12 = doc.select("div.col-md-12")

                for (blogCard in div12) {
                    val name = blogCard.select("div.col-md-2 img").attr("title")
                    val imageUrl = blogCard.select("div.col-md-2 img").attr("src")
                    val city = blogCard.select("div.col-md-12.nobetci h4.tred").text()
                    val address = blogCard.select("p").text()
                        .replace("Haritada görüntülemek için tıklayınız...", "")
                        .trim()
                    Log.d("adress:",address)
                    val phoneNumber =
                        blogCard.select("i.fa.fa-phone.main-color").next().attr("href").toString()
                    val locationUrl =
                        blogCard.select("i.fa.fa-map-marker.main-color").next().attr("href")
                            .toString()

                    if (name.isNullOrEmpty()
                            .not() && address.length <= 200 && city.contains("BATMAN") && locationUrl.isEmpty()
                            .not()
                    ) {
                        val uri = Uri.parse(locationUrl)
                        val queryParameter = uri.getQueryParameter("q")
                        val coordinates = queryParameter?.split(",")
                        val latitude = coordinates?.get(0)?.toDouble()
                        val longitude = coordinates?.get(1)?.toDouble()
                        val pharmacyItem = PharmacyItem(
                            name,
                            phoneNumber,
                            address,
                            locationUrl,
                            LatLng(latitude!!, longitude!!),
                            imageUrl = imageUrl
                        )
                        pharmacyList.add(pharmacyItem)
                        Log.v("city:", city.toString())
                    }
                }
                val distincList = pharmacyList.distinct()
                _pharmacyState.value =
                    PharmacyState(data = distincList, isLoading = false, isSuccess = true)
            } catch (e: UnknownHostException) {
                e.printStackTrace()
                _pharmacyState.value =
                    PharmacyState(isError = "Unknown Host Exception", isLoading = false)
            } catch (e: Exception) {
                e.printStackTrace()
                _pharmacyState.value =
                    PharmacyState(isError = "Error fetching news", isLoading = false)
            }
        }

    }
}


data class PharmacyState(
    val data: List<PharmacyItem>? = null,
    val isError: String? = null,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false
)
