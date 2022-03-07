package com.matias.mysoothe.ui.screens.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.matias.mysoothe.R
import com.matias.mysoothe.ui.component.MyTextField
import com.matias.mysoothe.ui.component.PrimaryButton
import com.matias.mysoothe.ui.compose.rememberStateWithLifecycle
import com.matias.mysoothe.ui.theme.MySootheTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun LoginScreen(onLogin: () -> Unit, onSignUp: () -> Unit) {
    LoginScreen(
        viewModel = hiltViewModel(),
        onLogin = onLogin,
        onSignUp = onSignUp
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun LoginScreen(viewModel: LoginViewModel, onLogin: () -> Unit, onSignUp: () -> Unit) {
    val viewState by rememberStateWithLifecycle(viewModel.state)
    val effect: Flow<LoginContract.Effect> = viewModel.effects.receiveAsFlow()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(effect) {
        effect.collect { effect ->
            when (effect) {
                is LoginContract.Effect.SignUp -> onSignUp()
                is LoginContract.Effect.LoginSuccessful -> onLogin()
                is LoginContract.Effect.None -> {}
            }
        }
    }

    LoginScreenContent(
        state = viewState,
        LoginScreenContentCallbacks(
            onEmailChanged = { viewModel.onEmailChanged(it) },
            onPasswordChanged = { viewModel.onPasswordChanged(it) },
            onLogin = {
                viewModel.login()
                keyboardController?.hide()
            },
            onSignUp = {
                viewModel.signUp()
                keyboardController?.hide()
            }
        )
    )
}

class LoginScreenContentCallbacks(
    val onEmailChanged: (String) -> Unit = {},
    val onPasswordChanged: (String) -> Unit = {},
    val onLogin: () -> Unit = {},
    val onSignUp: () -> Unit = {}
)

@Composable
private fun LoginScreenContent(
    state: LoginContract.State = LoginContract.State.Empty,
    callbacks: LoginScreenContentCallbacks = LoginScreenContentCallbacks()
) {
    Surface(
        color = MaterialTheme.colors.background,
    ) {
        LoginBackground()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.log_in).uppercase(),
                modifier = Modifier.paddingFromBaseline(200.dp),
                style = MaterialTheme.typography.h1
            )
            Spacer(Modifier.height(32.dp))
            MyTextField(
                labelResource = R.string.email_address,
                value = state.email,
                isError = state.emailError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                ),
                onValueChange = { callbacks.onEmailChanged(it) }
            )
            Spacer(Modifier.height(8.dp))
            MyTextField(
                labelResource = R.string.password,
                value = state.password,
                isError = state.passwordError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions { callbacks.onLogin.invoke() },
                onValueChange = callbacks.onPasswordChanged
            )
            Spacer(Modifier.height(8.dp))
            PrimaryButton(
                titleRes = R.string.log_in,
                enabled = state.loginEnabled,
                onClick = callbacks.onLogin
            )
            SignUpLabel(onClick = callbacks.onSignUp)

            if (state.loginError) {
                LoginError()
            }
        }
    }
}

@Composable
private fun LoginError() {
    Text(
        text = stringResource(id = R.string.login_error),
        modifier = Modifier
            .fillMaxWidth()
            .paddingFromBaseline(32.dp)
            .padding(horizontal = 16.dp),
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline,
    )
}

@Composable
private fun SignUpLabel(onClick: () -> Unit = {}) {
    val labelText = buildAnnotatedString {
        append(stringResource(id = R.string.dont_have_account))
        append(" ")
        withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
            append(stringResource(id = R.string.sign_up))
        }
    }

    Text(
        text = labelText,
        modifier = Modifier
            .paddingFromBaseline(32.dp)
            .clickable(onClick = onClick),
        style = MaterialTheme.typography.body1,
    )
}

@Composable
private fun LoginBackground() {
    val isLight = MaterialTheme.colors.isLight

    val background = if (isLight) {
        R.drawable.ic_light_login
    } else {
        R.drawable.ic_dark_login
    }

    Image(
        painter = painterResource(id = background),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun Preview() {
    MySootheTheme {
        LoginScreenContent()
    }
}
