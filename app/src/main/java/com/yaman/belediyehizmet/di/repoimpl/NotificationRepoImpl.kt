package com.yaman.belediyehizmet.di.repoimpl

import android.content.Context
import com.yaman.belediyehizmet.db.MessageNotification
import com.yaman.belediyehizmet.di.AppModule
import com.yaman.belediyehizmet.firebase.NotificationRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotificationRepoImpl @Inject constructor(@ApplicationContext val app: Context) :
    NotificationRepository {
    override suspend fun addNotification(messageNotification: MessageNotification) {
        AppModule.provideMessageRoom(app).messageDao().addNotification(messageNotification)
    }

    override suspend fun deleteNotification(messageNotification: MessageNotification) {
        AppModule.provideMessageRoom(app).messageDao().delete(messageNotification)
    }

    override suspend fun getAllNotificaiton(): List<MessageNotification> {
       return withContext(Dispatchers.IO){
           AppModule.provideMessageRoom(app).messageDao().getAllNotification()
       }
    }
}