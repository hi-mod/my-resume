package com.aaronchancey.myresume.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aaronchancey.myresume.database.model.ProfileEntity

@Database(entities = [ProfileEntity::class], version = 1)
abstract class ResumeDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}
