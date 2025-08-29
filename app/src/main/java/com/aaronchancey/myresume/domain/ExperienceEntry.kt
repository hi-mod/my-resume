package com.aaronchancey.myresume.domain

import androidx.compose.ui.text.AnnotatedString

data class ExperienceEntry(
    val experienceId: Int,
    val company: String,
    val role: String,
    val duration: String,
    val description: AnnotatedString,
)
