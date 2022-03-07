package com.matias.mysoothe.ui.screens.welcome

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matias.mysoothe.R
import com.matias.mysoothe.ui.component.PrimaryButton
import com.matias.mysoothe.ui.component.SecondaryButton
import com.matias.mysoothe.ui.theme.MySootheTheme

@Composable
fun WelcomeScreen(onLogin: () -> Unit = {}, onSignUp: () -> Unit = {}) {
    Surface(color = MaterialTheme.colors.background) {
        WelcomeBackground()
        WelcomeContent(onLogin = onLogin, onSignUp = onSignUp)
    }
}

@Composable
private fun WelcomeContent(onLogin: () -> Unit, onSignUp: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WelcomeLogo()
        Spacer(Modifier.height(32.dp))
        PrimaryButton(titleRes = R.string.sign_up, onClick = onSignUp)
        Spacer(Modifier.height(8.dp))
        SecondaryButton(titleRes = R.string.log_in, onClick = onLogin)
    }
}

@Composable
private fun WelcomeLogo() {
    val isLight = MaterialTheme.colors.isLight

    val background = if (isLight) {
        R.drawable.ic_light_logo
    } else {
        R.drawable.ic_dark_logo
    }

    Image(
        painter = painterResource(id = background),
        contentDescription = null,
    )
}

@Composable
private fun WelcomeBackground() {
    val isLight = MaterialTheme.colors.isLight

    val background = if (isLight) {
        R.drawable.ic_light_welcome
    } else {
        R.drawable.ic_dark_welcome
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
        WelcomeScreen()
    }
}
