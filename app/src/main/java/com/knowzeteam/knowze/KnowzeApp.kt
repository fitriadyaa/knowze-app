package com.knowzeteam.knowze

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.knowzeteam.knowze.ui.navigation.Screen
import com.knowzeteam.knowze.ui.screen.auth.login.LoginScreen
import com.knowzeteam.knowze.ui.screen.auth.login.LoginViewModel
import com.knowzeteam.knowze.ui.screen.auth.login.LoginWithEmailScreen
import com.knowzeteam.knowze.ui.screen.auth.register.RegisterScreen
import com.knowzeteam.knowze.ui.screen.gallery.CourseThemeGallery
import com.knowzeteam.knowze.ui.screen.home.HomeScreen
import com.knowzeteam.knowze.ui.screen.home.HomeSearch
import com.knowzeteam.knowze.ui.screen.welcome.IntroOneScreen
import com.knowzeteam.knowze.ui.screen.welcome.IntroSecondScreen
import com.knowzeteam.knowze.ui.screen.welcome.IntroThridScreen
import com.knowzeteam.knowze.ui.screen.welcome.SplashScreen

@Composable
fun KnowzeApp(viewModelFactory: ViewModelProvider.Factory, isLoggedIn: Boolean) {

    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel(factory = viewModelFactory)

    val startDestination = if (isLoggedIn) {
        Screen.Home.route
    } else {
        Screen.Splash.route
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Onboard1.route) {
            IntroOneScreen(
                onNextClick = { navController.navigate(Screen.Onboard2.route) },
                onSkipClick = { navController.navigate(Screen.Login.route) },
            )
        }
        composable(Screen.Onboard2.route) {
            IntroSecondScreen(
                onNextClick = { navController.navigate(Screen.Onboard3.route) },
                onSkipClick = { navController.navigate(Screen.Login.route) },
            )
        }
        composable(Screen.Onboard3.route){
            IntroThridScreen(
                onNextClick = { navController.navigate(Screen.Login.route) },
                onSkipClick = { navController.navigate(Screen.Login.route) },
            )
        }

        composable(Screen.Login.route) {

            LoginScreen(navController = navController, viewModel = loginViewModel)
        }

        composable(Screen.EmailLogin.route) {
            LoginWithEmailScreen(navController = navController)
        }

        composable(Screen.Register.route) {
            // Create the RegisterScreen composable and pass the navController
            RegisterScreen(navController = navController)
        }

        composable(Screen.Home.route){
            HomeScreen(
                navController = navController,
                viewModel = loginViewModel
            )
        }

        composable(Screen.GalleryCourse.route){
            CourseThemeGallery()
        }

        composable(
            "${Screen.HomeS.route}/{focus}",
            arguments = listOf(navArgument("focus") { defaultValue = "nofocus"; type = NavType.StringType })
        ) { backStackEntry ->
            val focus = backStackEntry.arguments?.getString("focus") ?: "nofocus"
            HomeSearch(
                onBack = { navController.popBackStack() },
                initialSearchText = "",
                shouldFocus = focus == "focus",
            )
        }
    }
}
