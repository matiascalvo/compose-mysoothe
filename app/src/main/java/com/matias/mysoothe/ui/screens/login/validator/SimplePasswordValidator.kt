package com.matias.mysoothe.ui.screens.login.validator

import javax.inject.Inject

private const val MIN_PASSWORD_LENGTH = 3

class SimplePasswordValidator @Inject constructor() : Validator {
    override fun validate(value: String) = value.isNotBlank() && value.length > MIN_PASSWORD_LENGTH
}
