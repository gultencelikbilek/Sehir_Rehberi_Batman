package com.yaman.belediyehizmet.feature.notification

import android.util.Log
import android.view.View
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaman.belediyehizmet.db.MessageNotification
import com.yaman.belediyehizmet.di.usecase.AllNotificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationListViewModel @Inject constructor(private val allNotificationUseCase: AllNotificationUseCase) :
    ViewModel() {
    private val _notificationState: MutableState<NotificationState> =
        mutableStateOf(NotificationState())
    val notificationState: State<NotificationState> = _notificationState
    fun getAllNotification() {
        viewModelScope.launch {
            try {
                allNotificationUseCase.invoke().collect { response ->
                    _notificationState.value = NotificationState(data = response)
                    Log.d("success:", _notificationState.value.toString())
                }
            } catch (e: Exception) {
                Log.e("NoteListViewModel", "Hata: ${e.message}")
            }
        }

    }
}

data class NotificationState(
    val data: List<MessageNotification> = emptyList()
)