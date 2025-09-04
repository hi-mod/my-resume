package com.aaronchancey.myresume.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aaronchancey.myresume.database.model.ProfileEntity

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile")
    fun getAll(): List<ProfileEntity>

    @Insert
    fun insertAll(vararg users: ProfileEntity)

    @Delete
    fun delete(user: ProfileEntity)
}
