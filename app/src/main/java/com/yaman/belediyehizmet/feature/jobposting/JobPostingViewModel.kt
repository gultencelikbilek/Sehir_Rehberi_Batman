package com.yaman.belediyehizmet.feature.jobposting

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class JobPostingViewModel @Inject constructor() : ViewModel() {

    private val _jobState = mutableStateOf(JobState())
    val jobState: State<JobState> = _jobState

    init {
        jobPostings()
    }

    fun jobPostings() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val jobList = mutableListOf<JobPostingItem>()
                val doc = Jsoup.connect("https://www.isinolsun.com/is-ilanlari/batman")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .get()
                val jobCards = doc.select(".job-card")

                if (jobCards.isEmpty()) {
                    Log.d("JobPostingViewModel", "No job cards found")
                } else {
                    for (jobCard in jobCards) {
                        val title = jobCard.select(".job-card-title a").text()
                        val company = jobCard.select(".job-card-company").text()
                        val location = jobCard.select(".job-card-location").text()
                        val url = jobCard.select(".job-card-title a").attr("href")
                        val jobPostingItem = JobPostingItem(title, company, location, url)

                        Log.d("title", title)
                        Log.d("company", company)
                        Log.d("location", location)
                        Log.d("url", url)

                        jobList.add(jobPostingItem)
                    }
                }

                _jobState.value = JobState(data = jobList, isError = "", isSuccess = true, isLoading = false)
            } catch (e: UnknownHostException) {
                e.printStackTrace()
                _jobState.value = JobState(isError = "Unknown Host Exception: ${e.message}", isLoading = false)
            } catch (e: Exception) {
                e.printStackTrace()
                _jobState.value = JobState(isError = "Error fetching jobs: ${e.message}", isLoading = false)
                Log.e("JobPostingViewModel", "Error fetching jobs", e)
            }
        }
    }
}

data class JobState(
    val data: List<JobPostingItem>? = null,
    val isError: String? = null,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false
)
