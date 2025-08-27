package com.aaronchancey.myresume.presentation.app

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Home : Route
}
