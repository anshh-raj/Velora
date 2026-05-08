package com.example.velora.velora.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.velora.velora.domain.usecase.AddReminderUseCase
import com.example.velora.velora.domain.usecase.DeleteReminderUseCase
import com.example.velora.velora.domain.usecase.GetReminderUseCase
import com.example.velora.velora.domain.usecase.UpdateReminderUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ReminderViewModel(
    private val addReminderUseCase: AddReminderUseCase,
    private val getReminderUseCase: GetReminderUseCase,
    private val updateReminderUseCase: UpdateReminderUseCase,
    private val deleteReminderUseCase: DeleteReminderUseCase
): ViewModel(){

    private val _uiState = MutableStateFlow(ReminderUiState())
    val uiState: StateFlow<ReminderUiState> = _uiState.asStateFlow()

    val reminders = getReminderUseCase()
        .onStart {
            _uiState.value = _uiState.value.copy(isLoading = true)
        }
        .onEach {
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


}

data class ReminderUiState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val error: String? = null,
    val isEmpty: Boolean? = false
)