package io.versology.moodleclient.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.versology.moodleclient.core.domain.model.SiteInfo
import io.versology.moodleclient.core.domain.repository.AuthRepository
import io.versology.moodleclient.core.domain.usecase.LoginWithCredentialsUseCase
import io.versology.moodleclient.core.domain.usecase.LoginWithTokenUseCase
import io.versology.moodleclient.core.domain.usecase.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(val siteInfo: SiteInfo) : LoginState()
    data class Error(val message: String) : LoginState()
}

class LoginViewModel(
    private val loginWithCredentialsUseCase: LoginWithCredentialsUseCase,
    private val loginWithTokenUseCase: LoginWithTokenUseCase,
    private val logoutUseCase: LogoutUseCase,
    authRepository: AuthRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    val isLoggedIn = authRepository.isLoggedIn

    fun loginWithCredentials(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            _state.value = LoginState.Error("Please enter both username and password")
            return
        }

        viewModelScope.launch {
            _state.value = LoginState.Loading

            loginWithCredentialsUseCase(username, password)
                .onSuccess { siteInfo ->
                    _state.value = LoginState.Success(siteInfo)
                }
                .onFailure { error ->
                    _state.value = LoginState.Error(
                        error.message ?: "Login failed"
                    )
                }
        }
    }

    fun loginWithToken(token: String) {
        if (token.isBlank()) {
            _state.value = LoginState.Error("Please enter a valid token")
            return
        }

        viewModelScope.launch {
            _state.value = LoginState.Loading

            loginWithTokenUseCase(token)
                .onSuccess { siteInfo ->
                    _state.value = LoginState.Success(siteInfo)
                }
                .onFailure { error ->
                    _state.value = LoginState.Error(
                        error.message ?: "Token validation failed"
                    )
                }
        }
    }

    fun logout() {
        logoutUseCase()
        _state.value = LoginState.Idle
    }

    fun clearError() {
        if (_state.value is LoginState.Error) {
            _state.value = LoginState.Idle
        }
    }
}
