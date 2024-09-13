package com.yaman.belediyehizmet.feature.news

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor() : ViewModel() {


    private val _newsDetailState = mutableStateOf(NewsDetailState())
    val newsDetailState: State<NewsDetailState> = _newsDetailState



    fun fetchNewsDetail(newsDetailUrl: String) {
        Log.v("newsDetailItem:fetch:", newsDetailUrl.toString())
        viewModelScope.launch {
            try {
                val newsDetail = mutableListOf<NewsItem>()
                val doc = Jsoup.connect(newsDetailUrl).get()
                Log.v("newsDetailItem:doc:", doc.toString())
                val imageUrl = doc.select(".news-details-box-image img").attr("src")
                val date = doc.select(".news-details-box-date").text()
                val title = doc.select(".news-details-content-box h4").text()
                val description = doc.select(".news-details-content-box p").text()
                val newsItem = NewsItem(imageUrl, date, title, description)
                Log.v("newsDetailItem", newsItem.toString())
                newsDetail.add(newsItem)
                _newsDetailState.value =
                    NewsDetailState(data = newsDetail, isLoading = false, isSuccess = true)
                Log.d("desc",description)
            } catch (e: Exception) {
                e.printStackTrace()
                _newsDetailState.value =
                    NewsDetailState(isError = "Unknown Host Exception", isLoading = false)
                null
            }
        }
    }
}

data class NewsDetailState(
    val data: List<NewsItem>? = null,
    val isError: String? = null,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false
)