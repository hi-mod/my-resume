package com.aaronchancey.myresume.presentation.skills

import com.aaronchancey.myresume.domain.SkillGroup

data class SkillsState(
    val skills: List<SkillGroup> = emptyList(),
)
