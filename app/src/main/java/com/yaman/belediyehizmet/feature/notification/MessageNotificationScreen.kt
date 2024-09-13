package com.yaman.belediyehizmet.feature.notification

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.yaman.belediyehizmet.R
import com.yaman.belediyehizmet.components.BackIcon
import com.yaman.belediyehizmet.components.Header
import com.yaman.belediyehizmet.db.MessageNotification
import com.yaman.belediyehizmet.firebase.NotificationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MessageNotification(
    viewModel: NotificationListViewModel = hiltViewModel(),
    navController: NavHostController,
    darkTheme: MutableState<Boolean>,
    viewModelnoti: NotificationViewModel = hiltViewModel()
) {

    val notificationListState = viewModel.notificationState.value.data
    Log.d("getallno", notificationListState.toString())

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllNotification()
        Log.d("getallno", viewModel.getAllNotification().toString())
    }
        Scaffold(
            topBar = {
                androidx.compose.material.TopAppBar(
                    navigationIcon = {
                        BackIcon(
                            onClick = {
                                navController.navigateUp()
                            })
                    },

                    title = {
                        Header(text = stringResource(id = R.string.noti))
                    },
                    backgroundColor = Color.White
                )
            },
            content = { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(if (darkTheme.value) Color.Black else Color.White)
                ) {
                    items(notificationListState) {
                        NotificationCard(it,darkTheme,viewModelnoti)
                    }
                }
            }
        )
    }

@Composable
fun NotificationCard(
    it: MessageNotification,
    darkTheme: MutableState<Boolean>,
    viewModel: NotificationViewModel
) {
    Column(Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (darkTheme.value) Color.Black else Color.White
            ),
            border = BorderStroke(0.dp, Color.Gray),
            shape = RoundedCornerShape(15.dp)
          //,onClick = {
          //    val delet = MessageNotification(it.id,it.notificaitonTitle,it.notificationDesc,it.icon)
          //    viewModel.delete(delet)
          //}
           // elevation = CardDefaults.cardElevation()
        ){
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.batman_bel),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(85.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))

                )
                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .height(80.dp),
                ) {
                    androidx.compose.material.Text(
                        text = it.notificaitonTitle,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        color = if (darkTheme.value) Color.White else Color.Black
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    androidx.compose.material.Text(
                        text = it.notificationDesc,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray,
                        maxLines = 2
                    )
                }
            }
        }
    }
}
