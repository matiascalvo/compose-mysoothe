package com.matias.mysoothe.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matias.mysoothe.domain.usecase.LoginUseCase
import com.matias.mysoothe.ui.screens.login.validator.EmailValidator
import com.matias.mysoothe.ui.screens.login.validator.SimplePasswordValidator
import com.matias.mysoothe.utils.FLOW_TIMEOUT
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val emailValidator: EmailValidator,
    private val passwordValidator: SimplePasswordValidator
) : ViewModel() {

    private data class InternalLoginState(
        val emailField: FieldState = FieldState(field = ""),
        val passwordField: FieldState = FieldState(""),
        val loading: Boolean = false,
        val loginError: Boolean = false,
    )

    val effects = Channel<LoginContract.Effect>(UNLIMITED)

    private val _state = MutableStateFlow(InternalLoginState())

    val state: StateFlow<LoginContract.State> = _state.map { internalState ->
        val emailError = internalState.emailField.shouldShowError { !emailValidator.validate(it) }
        val passwordError = internalState.passwordField.shouldShowError { !passwordValidator.validate(it) }
        LoginContract.State(
            email = internalState.emailField.field,
            emailError = emailError,
            password = internalState.passwordField.field,
            passwordError = passwordError,
            loginEnabled = !emailError && !passwordError && internalState.emailField.field.isNotBlank() &&
                internalState.passwordField.field.isNotBlank(),
            loading = internalState.loading,
            loginError = internalState.loginError,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(FLOW_TIMEOUT),
        initialValue = LoginContract.State(),
    )

    fun onEmailChanged(value: String) {
        _state.update {
            it.copy(
                emailField = it.emailField.copy(field = value, isDirty = true),
                loginError = false
            )
        }
    }

    fun onPasswordChanged(value: String) {
        _state.update {
            it.copy(
                passwordField = it.passwordField.copy(field = value, isDirty = true),
                loginError = false
            )
        }
    }

    fun login() {
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            val result = loginUseCase.invoke(
                email = _state.value.emailField.field,
                password = _state.value.passwordField.field
            )
            _state.update {
                it.copy(
                    loginError = !result.isSuccess,
                    loading = false,
                )
            }
            if (result.isSuccess) effects.send(LoginContract.Effect.LoginSuccessful)
        }
    }

    fun signUp() {
        viewModelScope.launch {
            effects.send(LoginContract.Effect.SignUp)
        }
    }
}
