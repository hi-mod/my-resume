package com.aaronchancey.myresume.presentation.app

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Profile : Route

    @Serializable
    data object Experience : Route

    @Serializable
    data object ExperienceList : Route

    @Serializable
    data class ExperienceDetails(val experienceId: Int? = null) : Route

    @Serializable
    data object References : Route

    @Serializable
    data object Skills : Route
}
