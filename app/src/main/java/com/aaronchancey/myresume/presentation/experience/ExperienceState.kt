package com.aaronchancey.myresume.presentation.experience

import com.aaronchancey.myresume.domain.ExperienceEntry

data class ExperienceState(
    val loading: Boolean = true,
    val experienceEntries: List<ExperienceEntry> = emptyList(),
)
