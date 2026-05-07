package com.example.velora.velora.presentation.route

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute{

    @Serializable
    data object Login: NavRoute()

    @Serializable
    data object Home: NavRoute()

    @Serializable
    data object AddReminder: NavRoute()

    @Serializable
    data class ReminderDetails(val reminderId: String? = null): NavRoute()

}