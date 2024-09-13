package com.yaman.belediyehizmet

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.yaman.belediyehizmet.db.MessageNotification
import com.yaman.belediyehizmet.feature.mainscreen.MainScreen
import com.yaman.belediyehizmet.firebase.NotificationViewModel
import com.yaman.belediyehizmet.ui.theme.BatmanBelediyeHizmetTheme
import dagger.hilt.android.AndroidEntryPoint

var loadURdL = "https://webportal.batman.bel.tr/web/guest/2"
var loadURL = "https://www.batman.bel.tr/"
val TAG = "logChat"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val notificationPermissionRequestCode = 1001

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint(
        "UnusedMaterialScaffoldPaddingParameter",
        "UnusedMaterial3ScaffoldPaddingParameter"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkAndRequestNotificationPermission()

        setContent {
            val darkTheme = remember { mutableStateOf(false) }
            val notificationViewModel: NotificationViewModel by viewModels()

            FirebaseMessaging.getInstance().token
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                        return@addOnCompleteListener
                    }

                    // Get new FCM registration token
                    val token = task.result
                    Log.d(TAG, "FCM Registration token: $token")
                }
            val title = intent.getStringExtra("title")
            val body = intent.getStringExtra("body")

            if (title != null && body != null) {
                val notification = MessageNotification(0, title, body, R.drawable.batman_bel)
                notificationViewModel.addNotification(notification)
            }

            BatmanBelediyeHizmetTheme(darkTheme = darkTheme.value) {
                Scaffold(
                    content = { paddingValues ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(if (darkTheme.value) Color.Black else Color.White)
                                .padding(paddingValues)
                        ) {
                            MainScreen(darkTheme)
                        }
                    }
                )
            }
        }
    }

    private fun checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED -> {
                    // İzin zaten verilmiş
                }
                shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                    showPermissionRationale()
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }

    private fun showPermissionRationale() {
        AlertDialog.Builder(this)
            .setTitle("Bildirim İzni Gerekiyor")
            .setMessage("Bu uygulama bildirim gönderebilmek için izin gerektirir. Lütfen bildirim iznini verin.")
            .setPositiveButton("Tamam") { _, _ ->
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
            .setNegativeButton("İptal", null)
            .show()
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // İzin verildi
        } else {
            // İzin reddedildi
        }
    }
}
