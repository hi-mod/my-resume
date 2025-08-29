package com.aaronchancey.myresume.database.model

data class ExperienceEntryEntity(
    val experienceId: Int,
    val company: String,
    val role: String,
    val duration: String,
    val description: String,
)
