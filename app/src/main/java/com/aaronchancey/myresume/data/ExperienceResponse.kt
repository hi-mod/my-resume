package com.aaronchancey.myresume.data

import kotlinx.serialization.Serializable

@Serializable
data class ExperienceResponse(
    val experienceId: Int,
    val company: String,
    val role: String,
    val duration: String,
    val description: String,
)
