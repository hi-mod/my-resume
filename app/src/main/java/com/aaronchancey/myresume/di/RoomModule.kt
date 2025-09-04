package com.aaronchancey.myresume.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun provideRoomDatabase(@ApplicationContext applicationContext: Context) = Room.databaseBuilder(
        context = applicationContext,
        klass = RoomDatabase::class.java,
        name = "resume-database",
    ).build()
}
