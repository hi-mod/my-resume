package com.aaronchancey.myresume.domain.mappers

import com.aaronchancey.myresume.data.ProfileResponse
import com.aaronchancey.myresume.database.model.ProfileEntity
import com.aaronchancey.myresume.domain.Profile

fun ProfileEntity.toProfile() = Profile(
    profileText = profile,
)

fun ProfileResponse.toProfileEntity() = ProfileEntity(
    id = id,
    profile = profileText,
)

fun ProfileResponse.toProfile() = Profile(
    profileText = profileText,
)
