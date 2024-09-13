package com.yaman.belediyehizmet.feature.jobposting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun JobPostingScreen(
    navController: NavController,
    darkTheme: MutableState<Boolean>,
    jobViewModel: JobPostingViewModel = hiltViewModel()
) {
    val jobState = jobViewModel.jobState.value

    Surface {
        if (jobState.isLoading) {
            Text(text = "Loading...")
        } else if (jobState.isError != null) {
            Text(text = "Error: ${jobState.isError}")
        } else {
            jobState.data?.let {
                LazyColumn {
                    items(it) {job ->
                        Text(text = "Title: ${job.title}")
                        Text(text = "Company: ${job.companyName}")
                        Text(text = "Location: ${job.location}")
                        Text(text = "URL: ${job.url}")
                        Text(text = "----")
                    }
                }
            }
        }
    }
}