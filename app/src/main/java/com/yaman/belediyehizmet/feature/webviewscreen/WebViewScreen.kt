package com.yaman.belediyehizmet.feature.webviewscreen

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.http.SslError
import android.util.Log
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.mryaman.batmanbelediye.util.isOnline
import com.yaman.belediyehizmet.R
import com.yaman.belediyehizmet.TAG
import com.yaman.belediyehizmet.loadURL

@Composable
fun WebViewScreen(navController: NavHostController) {
    //TestView()
    WebViewPage(loadURL)
    //WebViewPage("file:///android_asset/shop.html") //OFFLINE
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewPage(url: String) {

    val openFullDialogCustom = remember { mutableStateOf(false) }
    if (openFullDialogCustom.value) {
        // Dialog function
        Dialog(
            onDismissRequest = {
                openFullDialogCustom.value = false
            }, properties = DialogProperties(
                usePlatformDefaultWidth = false // experimental
            )
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Image(
                        painter = painterResource(id = R.drawable.batman_bel),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth(),

                        )

                    Spacer(modifier = Modifier.height(20.dp))
                    //.........................Text: title
                    Text(
                        text = "Yükleniyor...",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        letterSpacing = 2.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    //.........................Text : description
                    Text(
                        text = "Lütfen bekleyin",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        letterSpacing = 2.sp,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    //.........................Spacer
                    Spacer(modifier = Modifier.height(24.dp))

                }

            }
        }

    }
    //..........................................................................

    val context = LocalContext.current

    //.................................................
    // Compose WebView Part 9 | Removes or Stop Ad in web

    var backEnabled by remember { mutableStateOf(false) }
    var webView: WebView? = null

    val mutableStateTrigger = remember { mutableStateOf(false) }
    val infoDialog = remember { mutableStateOf(false) }

    // Adding a WebView inside AndroidView
    // with layout as full screen
    AndroidView(modifier = Modifier.fillMaxSize(), factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            settings.userAgentString = System.getProperty("http.agent")
            settings.useWideViewPort = true
            addJavascriptInterface(WebAppInterface(context, infoDialog), "Android")
            webViewClient = object : WebViewClient() {

                override fun onReceivedSslError(
                    view: WebView?, handler: SslErrorHandler?, error: SslError?
                ) {
                    super.onReceivedSslError(view, handler, error)
                    handler?.proceed()
                    Log.v(TAG, "onReceivedSslError:${error.toString()}")
                    if (view != null) {
                        shouldOverrideUrlLoading(view = view, loadURL)
                    }

                }

                override fun onReceivedHttpError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    errorResponse: WebResourceResponse?
                ) {
                    super.onReceivedHttpError(view, request, errorResponse)
                    Log.v(TAG, "onReceivedHttpError:${errorResponse.toString()}")
                }

                override fun onReceivedError(
                    view: WebView?, request: WebResourceRequest?, error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    Log.v(TAG, "onReceivedError")
                    Log.d("test001", "error")

                    loadURL = if (isOnline(context)) {
                        "file:///android_asset/404.html" // other error
                    } else {
                        "file:///android_asset/error.html" // no internet
                    }
                    mutableStateTrigger.value = true
                }

                override fun shouldInterceptRequest(
                    view: WebView, request: WebResourceRequest
                ): WebResourceResponse? {
                    Log.v(TAG, "shouldInterceptRequest")
                    return super.shouldInterceptRequest(view, request)
                }

                override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                    Log.v(TAG, "onPageStarted")
                    openFullDialogCustom.value = true
                    backEnabled = view.canGoBack()
                }

                // Compose WebView Part 7 | Hide elements from web view
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    Log.v(TAG, "onPageFinished")
                    openFullDialogCustom.value = false
                    // removeElement(view!!)
                }

                // Compose WebView Part 5 | Should Override URL Loading
                @Deprecated("Deprecated in Java")
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    Log.v(TAG, "shouldOverrideUrlLoading:$url")
                    if (url.contains("facebook.com")) {
                        loadURL = "https://www.instagram.com/"
                        mutableStateTrigger.value = true
                        Toast.makeText(context, "Custom Action", Toast.LENGTH_SHORT).show()
                        return true
                    } else {
                        view.loadUrl(url)
                    }
                    return false
                }
            }

            loadUrl(url)
            webView = this
        }
    }, update = {
        webView = it
        // it.loadUrl(url)
    }

    )


    if (mutableStateTrigger.value) {
        // WebViewPage("https://www.instagram.com/muratyaman.72/")
        WebViewPage(loadURL)
    }
    if (infoDialog.value) {
        InfoDialog(title = "test", desc = "test", onDismiss = {
            infoDialog.value = false
        })
    }


    BackHandler(enabled = backEnabled) {
        removeElement(webView!!)
        webView?.goBack()
    }

}

fun removeElement(webView: WebView) {
    // hide element by id
    webView.loadUrl("javascript:(function() { document.getElementById('blog-pager').style.display='none';})()");
    // we can also hide class name
    webView.loadUrl("javascript:(function() { document.getElementsByClassName('mkTitleText').style.display='none';})()")
    webView.loadUrl("javascript:(function() { document.getElementsByClassName('btn')[1].style.display='none';})()")
    webView.loadUrl("javascript:(function() { document.getElementsByClassName('btn')[6].style.display='none';})()")
}

/** Instantiate the interface and set the context  */
class WebAppInterface(private val mContext: Context, var infoDialog: MutableState<Boolean>) {
    /** Show a toast from the web page  */
    @JavascriptInterface
    fun showToast(toast: String) {
        infoDialog.value = true
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }
}
