package com.yaman.belediyehizmet.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNotification(messageNotification: MessageNotification)

    @Query("SELECT * FROM messagenotification ORDER BY id DESC")
     fun getAllNotification() : List<MessageNotification>

     @Delete
     suspend fun delete(messageNotification: MessageNotification)
}