package com.yaman.belediyehizmet.feature.location

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LocationTabScreen(navController: NavController, darkTheme: MutableState<Boolean>) {
    val tabs = listOf("Taziye Evi", "Harita")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        LocationRoundTabBar(
                            darkTheme,
                            tabs = tabs,
                            selectedTab = tabs[pagerState.currentPage],
                        ) { tab ->
                            scope.launch {
                                pagerState.scrollToPage(tabs.indexOf(tab))
                            }
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(paddingValues)
            ) {
                LocationRoundTabViewPager(
                    navController,
                    tabs = tabs,
                    pagerState = pagerState,
                    darkTheme
                )
            }
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LocationRoundTabViewPager(
    navController: NavController,
    tabs: List<String>,
    pagerState: PagerState,
    darkTheme: MutableState<Boolean>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (darkTheme.value) Color.Black else Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
            when (page) {
                0 -> LocationScreen(navController = navController, darkTheme)
                1 -> GoogleMapViewScreen(navController = navController, darkTheme)
            }
        }
    }
}

@Composable
fun LocationRoundTabBar(
    darkTheme: MutableState<Boolean>,
    tabs: List<String>,
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .background(Color.Transparent)
            .wrapContentWidth()
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(25.dp))
    ) {
        tabs.forEach { tab ->
            LocationRoundTabItem(
                text = tab,
                isSelected = tab == selectedTab,
                onClick = { onTabSelected(tab) },
                darkTheme
            )
        }
    }
}

@Composable
fun LocationRoundTabItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    darkTheme: MutableState<Boolean>
) {
    val targetColor = if (isSelected) {
        if (darkTheme.value) Color.White else Color.Black
    } else {
        Color.Gray
    }

    val color by animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(300, easing = LinearEasing)
    )

    Box(
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = color,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}
