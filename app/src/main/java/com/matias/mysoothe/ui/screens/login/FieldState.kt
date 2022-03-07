package com.matias.mysoothe.ui.screens.login

data class FieldState(
    val field: String = "",
    val isDirty: Boolean = false
) {
    fun shouldShowError(checker: (String) -> Boolean): Boolean {
        return isDirty && checker.invoke(field)
    }
}
