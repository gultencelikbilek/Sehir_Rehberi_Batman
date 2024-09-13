import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.yaman.belediyehizmet.R
import com.yaman.belediyehizmet.components.BackIcon
import com.yaman.belediyehizmet.feature.news.NewsDetailViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NewsDetailScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    navController: NavController,
    imageUrl: String?,
    title: String?,
    description: String?,
    date: String?,
    pageDetailUrl: String?,
    viewModel: NewsDetailViewModel = hiltViewModel(),

    ) {
    val sheetState = rememberModalBottomSheetState()
    val isSheetOpen = rememberSaveable {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(topBar = {
        TopAppBar(title = {}, navigationIcon = {
            BackIcon(onClick = {
                navController.navigateUp()
            })
        }, actions = {
            IconButton(onClick = {
                val uri = Uri.parse(pageDetailUrl)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.worldwide),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
            IconButton(onClick = {
                isSheetOpen.value = true
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = "Share",
                    modifier = Modifier.size(20.dp)
                )
            }
        })
    }, content = {
        NewsDetailContent(
            animatedVisibilityScope, it, imageUrl, title, description, date
        )
    })
    if (isSheetOpen.value) {
        ModalBottomSheet(sheetState = sheetState, onDismissRequest = {
            isSheetOpen.value = false
            scope.launch {
                sheetState.hide()
            }
        }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.share),
                    style = androidx.compose.material.MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        val contentToShare = pageDetailUrl
                        shareViaWhatsApp(context, contentToShare.toString())
                    }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_whatsapp),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    androidx.compose.material.Text(text = stringResource(id = R.string.whatsapp))
                }

                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier
                    .padding(start = 8.dp, bottom = 20.dp)
                    .clickable {
                        val contentToShare = pageDetailUrl
                        shareViaEmail(context, "", contentToShare.toString())
                    }) {
                    Image(
                        painter = painterResource(id = R.drawable.gmail),
                        contentDescription = stringResource(id = R.string.share_with_email),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    androidx.compose.material.Text(text = stringResource(id = R.string.email))
                }
            }
        }
    }
}

fun shareViaWhatsApp(context: Context, contentToShare: String) {
    val whatsappIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        setPackage("com.whatsapp")
        putExtra(Intent.EXTRA_TEXT, contentToShare)
    }
    try {
        context.startActivity(whatsappIntent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
fun shareViaEmail(context: Context, subject: String, contentToShare: String) {
    val emailIntent = Intent(Intent.ACTION_SEND).apply {
        type = "message/rfc822"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, contentToShare)
    }
    try {
        context.startActivity(Intent.createChooser(emailIntent, "E-posta ile paylaş"))
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "E-posta uygulaması bulunamadı", Toast.LENGTH_SHORT).show()
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NewsDetailContent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    paddingValues: PaddingValues,
    imageUrl: String?,
    title: String?,
    description: String?,
    date: String?
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth(), contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "Article Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .sharedBounds(
                        rememberSharedContentState(key = "image/$imageUrl"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        enter = fadeIn(),
                        exit = fadeOut(),
                        resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds()
                    )
            )
            Icon(
                painter = painterResource(id = R.drawable.batman_bel),
                contentDescription = "CNN Logo",
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp),
                tint = Color.Unspecified
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = title ?: "N/A",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.sharedBounds(
                    rememberSharedContentState(key = "title/$title"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds()
                )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.calendar),
                contentDescription = "Time Icon",
                modifier = Modifier.size(20.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = date ?: "N/A", color = Color.Gray, modifier = Modifier.sharedBounds(
                        rememberSharedContentState(key = "date/$date"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        enter = fadeIn(),
                        exit = fadeOut(),
                        resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds()
                    )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = description ?: "N/A",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.sharedBounds(
                    rememberSharedContentState(key = "description/$description"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds()
                )
        )
    }
}
