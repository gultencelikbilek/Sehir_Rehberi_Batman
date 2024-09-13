package com.yaman.belediyehizmet.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MessageNotification(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val notificaitonTitle : String = "",
    val notificationDesc : String = "",
    val icon : Int = 0
)
