package com.aaronchancey.myresume.domain.mappers

import com.aaronchancey.myresume.data.SkillsResponse
import com.aaronchancey.myresume.database.model.SkillGroupEntity
import com.aaronchancey.myresume.domain.SkillGroup

fun SkillGroupEntity.toSkillGroup() = SkillGroup(
    title = title,
    skills = skills,
)

fun SkillsResponse.toSkillGroup() = SkillGroup(
    title = title,
    skills = skills,
)
