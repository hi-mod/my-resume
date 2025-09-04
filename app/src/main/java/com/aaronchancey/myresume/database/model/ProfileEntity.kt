package com.aaronchancey.myresume.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val profile: String,
)
