package com.aaronchancey.myresume.data

import kotlinx.serialization.Serializable

@Serializable
data class ReferencesResponse(
    val name: String,
    val phoneNumber: String,
    val email: String,
)
