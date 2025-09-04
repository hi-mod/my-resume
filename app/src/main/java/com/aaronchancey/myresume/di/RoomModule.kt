package com.aaronchancey.myresume.di

import android.content.Context
import androidx.room.Room
import com.aaronchancey.myresume.database.ResumeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun provideResumeDatabase(@ApplicationContext applicationContext: Context): ResumeDatabase = Room.databaseBuilder(
        context = applicationContext,
        klass = ResumeDatabase::class.java,
        name = "resume-database",
    ).build()
}
