package com.yaman.belediyehizmet.feature.news

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.net.UnknownHostException
import javax.inject.Inject
@HiltViewModel
class NewsViewModel @Inject constructor() : ViewModel() {

    private val _newsState = mutableStateOf(NewsState())
    val newsState: State<NewsState> = _newsState

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val searchingText = _isSearching.asStateFlow()

    private val _originalNews = MutableStateFlow<List<NewsItem>>(emptyList())
    private val _news = MutableStateFlow<List<NewsItem>>(emptyList())

    val news = searchText
        .debounce(500L) // gecikme süresi
        .onEach { _isSearching.update { true } }
        .combine(_news) { text, news ->
            if (text.isBlank()) {
                _originalNews.value
            } else {
                news.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _news.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newsList = mutableListOf<NewsItem>()
                val doc = Jsoup.connect("https://www.batman.bel.tr/haber").get()
                val blogCards = doc.select(".blog-card")
                for (blogCard in blogCards) {
                    val title = blogCard.select(".blog-card-content a").text()
                    val description = blogCard.select(".blog-card-content p").text()
                    val imageUrl = blogCard.select(".blog-card-image img").attr("src")
                    val url = blogCard.select(".blog-card-content a").attr("href")
                    val date = blogCard.select(".blog-card-date a").text()
                    val newsItem = NewsItem(title, description, url, imageUrl, date)
                    newsList.add(newsItem)
                }
                _originalNews.value = newsList
                _news.value = newsList
                _newsState.value = NewsState(data = newsList, isLoading = false, isSuccess = true)
                Log.d("newsList:", _newsState.value.toString())
            } catch (e: UnknownHostException) {
                e.printStackTrace()
                _newsState.value = NewsState(isError = "Unknown Host Exception", isLoading = false)
            } catch (e: Exception) {
                e.printStackTrace()
                _newsState.value = NewsState(isError = "Error fetching news", isLoading = false)
            }
        }
    }
}

data class NewsState(
    val data: List<NewsItem>? = null,
    val isError: String? = null,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false
)
