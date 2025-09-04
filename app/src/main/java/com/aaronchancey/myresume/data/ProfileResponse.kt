package com.aaronchancey.myresume.data

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val id: Int,
    val profileText: String,
)
