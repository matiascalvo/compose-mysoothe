package com.matias.mysoothe.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matias.mysoothe.R
import com.matias.mysoothe.ui.screens.home.HomeScreen
import com.matias.mysoothe.ui.screens.login.LoginScreen
import com.matias.mysoothe.ui.screens.welcome.WelcomeScreen
import com.matias.mysoothe.ui.theme.MySootheTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MySootheTheme {
                MyApp()
            }
        }
    }
}

internal enum class Screen(val route: String) {
    Welcome("welcome"),
    SignIn("signin"),
    Home("home"),
}

@Composable
fun MyApp() {
    val context = LocalContext.current
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Welcome.route) {
        addWelcome(navController, context)
        addSignIn(navController, context)
        addHome()
    }
}

private fun NavGraphBuilder.addWelcome(
    navController: NavController,
    context: Context,
) {
    composable(Screen.Welcome.route) {
        WelcomeScreen(
            onLogin = { navController.navigate(Screen.SignIn.route) },
            onSignUp = { Toast.makeText(context, R.string.not_implemented, Toast.LENGTH_SHORT).show() }
        )
    }
}

private fun NavGraphBuilder.addSignIn(
    navController: NavController,
    context: Context,
) {
    composable(Screen.SignIn.route) {
        LoginScreen(
            onLogin = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Welcome.route) {
                        inclusive = true
                    }
                }
            },
            onSignUp = { Toast.makeText(context, R.string.not_implemented, Toast.LENGTH_SHORT).show() }
        )
    }
}

private fun NavGraphBuilder.addHome() {
    composable(Screen.Home.route) {
        HomeScreen()
    }
}
