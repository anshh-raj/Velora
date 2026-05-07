package com.example.velora.velora.data.models

data class Reminder (
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val isCompleted: Boolean = false
)


