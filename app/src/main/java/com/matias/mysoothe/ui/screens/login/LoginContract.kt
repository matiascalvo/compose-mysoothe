package com.matias.mysoothe.ui.screens.login

class LoginContract {

    data class State(
        val email: String = "",
        val emailError: Boolean = false,
        val password: String = "",
        val passwordError: Boolean = false,
        val showPassword: Boolean = false,
        val loginEnabled: Boolean = false,
        val loading: Boolean = false,
        val loginError: Boolean = true
    ) {
        companion object {
            val Empty = State()
        }
    }

    sealed class Effect {
        object None : Effect()
        object LoginSuccessful : Effect()
        object SignUp : Effect()
    }
}
