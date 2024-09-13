package com.yaman.belediyehizmet.firebase

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaman.belediyehizmet.db.MessageNotification
import com.yaman.belediyehizmet.di.usecase.AddNoteUseCase
import com.yaman.belediyehizmet.di.usecase.DeleteNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val repository: NotificationRepository,
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _notificationState: MutableState<NotificationState> = mutableStateOf(NotificationState())
    val notificationState: State<NotificationState> = _notificationState

    fun addNotification(messageNotification: MessageNotification) {
        viewModelScope.launch {
            addNoteUseCase.invoke(messageNotification)
            _notificationState.value = _notificationState.value.copy(
                data = _notificationState.value.data?.plus(messageNotification),
                isLoading = false,
                isSuccess = true
            )
        }
    }

    fun delete(messageNotification: MessageNotification) {
        viewModelScope.launch {
            deleteNoteUseCase.invoke(messageNotification)
            _notificationState.value = _notificationState.value.copy(
                data = _notificationState.value.data?.plus(messageNotification),
                isLoading = false,
                isSuccess = true
            )
        }
    }
}

data class NotificationState(
    val data: List<MessageNotification>? = emptyList(),
    val isError: String? = null,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false
)
