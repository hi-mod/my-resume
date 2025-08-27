package com.aaronchancey.myresume.presentation.app

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Profile : Route

    @Serializable
    data object Experience : Route

    @Serializable
    data object References : Route

    @Serializable
    data object Skills : Route
}
