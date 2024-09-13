package com.yaman.belediyehizmet.di.usecase

import android.util.Log
import com.yaman.belediyehizmet.db.MessageNotification
import com.yaman.belediyehizmet.di.repoimpl.NotificationRepoImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AllNotificationUseCase @Inject constructor(private val repoImpl: NotificationRepoImpl) {

   operator suspend fun invoke() : Flow<List<MessageNotification>> = flow {
      try {
          emit(repoImpl.getAllNotificaiton())
         Log.d("repoimpl",repoImpl.getAllNotificaiton().toString())
      }catch (e:Exception){
         Log.d("allnotificationusecase:",e.message.toString())
      }
   }
}