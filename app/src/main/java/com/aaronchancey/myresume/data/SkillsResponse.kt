package com.aaronchancey.myresume.data

import kotlinx.serialization.Serializable

@Serializable
data class SkillsResponse(
    val title: String,
    val skills: List<String>,
)
