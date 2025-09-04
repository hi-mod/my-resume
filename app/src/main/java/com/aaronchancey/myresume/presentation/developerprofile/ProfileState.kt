package com.aaronchancey.myresume.presentation.developerprofile

import com.aaronchancey.myresume.domain.Profile

data class ProfileState(
    val loading: Boolean = true,
    val profile: Profile = Profile(profileText = ""),
)
