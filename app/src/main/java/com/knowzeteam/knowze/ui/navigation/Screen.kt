package com.knowzeteam.knowze.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Onboard1 : Screen("onboarding_one")
    object Onboard2: Screen("onboarding_second")
    object Onboard3: Screen("onboarding_thrid")
    object Login: Screen("login_screen")
    object Home : Screen("home_screen")
}