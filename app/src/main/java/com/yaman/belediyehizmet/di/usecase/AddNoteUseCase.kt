package com.yaman.belediyehizmet.di.usecase


import com.yaman.belediyehizmet.db.MessageNotification
import com.yaman.belediyehizmet.di.repoimpl.NotificationRepoImpl
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val repoImpl: NotificationRepoImpl) {

   operator suspend fun invoke(messageNotification: MessageNotification){
      repoImpl.addNotification(messageNotification)
   }

}