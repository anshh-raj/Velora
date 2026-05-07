package com.example.velora.velora.domain.repository

import android.net.Uri
import com.example.velora.velora.data.models.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository{
    suspend fun addReminder(reminder: Reminder): Result<String>
    fun getReminders(): Flow<List<Reminder>>
    suspend fun updateReminder(reminder: Reminder): Result<Unit>
    suspend fun deleteReminder(id: String): Result<Unit>
    suspend fun uploadImage(uri: Uri): Result<String>
}