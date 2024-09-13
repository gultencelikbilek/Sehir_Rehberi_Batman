package com.yaman.belediyehizmet.firebase

import com.yaman.belediyehizmet.db.MessageNotification

interface NotificationRepository  {

    suspend fun addNotification(messageNotification: MessageNotification)
    suspend fun deleteNotification(messageNotification: MessageNotification)
    suspend fun getAllNotificaiton() : List<MessageNotification>

}