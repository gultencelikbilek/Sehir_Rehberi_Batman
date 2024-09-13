package com.yaman.belediyehizmet.feature.news

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.yaman.belediyehizmet.R
import com.yaman.belediyehizmet.components.PageHeader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SharedTransitionScope.NewsScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    navController: NavController,
    onShowDetails: (String) -> Unit,
    darkTheme: MutableState<Boolean>,
    newsViewModel: NewsViewModel = hiltViewModel()
) {

    val searchText by newsViewModel.searchText.collectAsState()
    val news by newsViewModel.news.collectAsState()
    val isSearching by newsViewModel.searchingText.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                   PageHeader(text = stringResource(id = R.string.news_header))
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
                    .background(if (darkTheme.value) Color.Black else Color.White)
                    .padding(10.dp)
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = newsViewModel::onSearchTextChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(horizontal = 10.dp),
                    shape = RoundedCornerShape(10.dp),
                    placeholder = {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "",
                            modifier = Modifier.size(48.dp)
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            keyboardController?.hide()
                        }
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF0D8AEE),
                        cursorColor = Color(0xFF0D8AEE),
                        focusedLabelColor = Color(0xFF0D8AEE),
                        unfocusedBorderColor = Color.Black
                    )
                )

                if (isSearching) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 8.dp)
                    ) {

                        items(news) { newsItem ->
                            TrendNewsItem(
                                animatedVisibilityScope = animatedVisibilityScope,
                                onShowDetails = onShowDetails,
                                title = newsItem.title,
                                description = newsItem.description,
                                pageDetailUrl = newsItem.pageDetailUrl,
                                imageUrl = newsItem.imageUrl,
                                date = newsItem.date,
                                navController = navController

                            )
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TrendNewsItem(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onShowDetails: (String) -> Unit,
    title: String,
    description: String,
    pageDetailUrl: String,
    imageUrl: String,
    date: String,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .clickable {
                navController.navigate(
                    "news_detail_screen" +
                            "?imageUrl=${Uri.encode(imageUrl)}" +
                            "&title=${Uri.encode(title)}" +
                            "&description=${Uri.encode(description)}" +
                            "&date=${Uri.encode(date)}" +
                            "&pageDetailUrl=${Uri.encode(pageDetailUrl)}"
                )
            }
            .fillMaxWidth()
            .padding(8.dp)
            .aspectRatio(16 / 9f),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = imageUrl)
                    .apply { crossfade(true) }.build()
            )
            Image(
                painter = painter,
                contentDescription = "News Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x60000000))
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}