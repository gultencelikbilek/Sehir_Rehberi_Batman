package com.yaman.belediyehizmet.feature.homescreen

import android.widget.Toast
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yaman.belediyehizmet.feature.homescreen.listscreen.ListLocationsScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen(navController: NavController) {
    val tabs = listOf("Liste", "Harita")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    Toast.makeText(LocalContext.current, "", Toast.LENGTH_LONG).show()
    RoundTabWithViewPager(navController, tabs = tabs, pagerState = pagerState)
}


@Composable
fun RoundTabBar(
    tabs: List<String>, selectedTab: String, onTabSelected: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(Color.Red)
            .wrapContentWidth()
            .padding(horizontal = 2.dp)
    ) {
        tabs.forEach {tab ->
            RoundTabItem(text = tab,
                isSelected = tab == selectedTab,
                onClick = { onTabSelected(tab) })
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RoundTabWithViewPager(
    navController: NavController, tabs: List<String>, pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 4.dp)
        ) {
            Card(
                shape = RoundedCornerShape(25.dp)
            ) {
                RoundTabBar(tabs = tabs,
                    selectedTab = tabs[pagerState.currentPage],
                    onTabSelected = { tab ->
                        coroutineScope.launch {
                            pagerState.scrollToPage(tabs.indexOf(tab))
                        }
                    })
            }
        }

        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
            when (page) {
              //  0 -> NewsScreen(navController = navController)
                1 -> ListLocationsScreen(navController = navController)
            }
        }
    }
}

@Composable
fun RoundTabItem(
    text: String, isSelected: Boolean, onClick: () -> Unit
) {
    val targetColor = if (isSelected) Color.Blue else Color.LightGray

    val color by animateColorAsState(
        targetValue = targetColor, animationSpec = tween(
            300, easing = LinearEasing
        )
    )

    Box(modifier = Modifier
        .clickable { onClick() }
        .padding(12.dp)
        .background(
            color = color, shape = CircleShape
        )) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)
        )
    }
}


