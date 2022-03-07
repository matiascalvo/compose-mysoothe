package com.matias.mysoothe.ui.screens.login.validator

import android.util.Patterns
import javax.inject.Inject

class EmailValidator @Inject constructor() : Validator {
    override fun validate(value: String) = value.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(value).matches()
}
