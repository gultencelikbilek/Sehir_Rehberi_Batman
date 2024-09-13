package com.yaman.belediyehizmet.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MessageNotification::class], version = 1)
abstract class NotificationDatabase :RoomDatabase(){

    abstract fun messageDao() : NotificationDao
}