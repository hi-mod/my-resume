package com.aaronchancey.myresume.domain.mappers

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import com.aaronchancey.myresume.database.model.ExperienceEntryEntity
import com.aaronchancey.myresume.domain.ExperienceEntry

fun ExperienceEntryEntity.toExperienceEntry(): ExperienceEntry = ExperienceEntry(
    experienceId = experienceId,
    company = company,
    role = role,
    duration = duration,
    description = AnnotatedString.fromHtml(description),
)
