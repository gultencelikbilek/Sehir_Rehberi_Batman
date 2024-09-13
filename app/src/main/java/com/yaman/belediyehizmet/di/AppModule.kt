package com.yaman.belediyehizmet.di

import android.content.Context
import androidx.room.Room
import com.yaman.belediyehizmet.db.NotificationDatabase
import com.yaman.belediyehizmet.di.repoimpl.NotificationRepoImpl
import com.yaman.belediyehizmet.feature.news.NewsViewModel
import com.yaman.belediyehizmet.firebase.NotificationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMessageRoom(@ApplicationContext app: Context): NotificationDatabase =
        Room.databaseBuilder(app, NotificationDatabase::class.java, "note_db").build()
    @Provides
    @Singleton
    fun provideNewsViewModel(): NewsViewModel {
        return NewsViewModel()
    }
    @Provides
    @Singleton
    fun provideNotificationRepository(@ApplicationContext  app: Context) : NotificationRepository = NotificationRepoImpl(app)
}
