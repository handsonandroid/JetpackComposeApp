package com.example.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    // UI state holder
    data class UiState(
        val email: String = "",
        val password: String = "",
        val showPassword: Boolean = false,
        val emailError: String? = null,
        val passwordError: String? = null,
        val loading: Boolean = false,
        val success: Boolean? = null // Add success flag for navigation
    )

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    fun onEmailChanged(email: String) {
        _state.update { it.copy(email = email, emailError = null) }
    }

    fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password, passwordError = null) }
    }

    fun togglePasswordVisibility() {
        _state.update { it.copy(showPassword = !it.showPassword) }
    }

    fun onLoginClick() {
        val current = _state.value

        // Basic validation
        if (current.email.isBlank()) {
            _state.update { it.copy(emailError = "Email required") }
            return
        }
        if (current.password.isBlank()) {
            _state.update { it.copy(passwordError = "Password required") }
            return
        }

        // Simulate loading state
        _state.update { it.copy(loading = true) }
        viewModelScope.launch {
            delay(1500) // Fake API call
            _state.update { it.copy(loading = false, success = true) } // Set success to true

            // You can trigger navigation here or set a success flag
        }
    }
}
