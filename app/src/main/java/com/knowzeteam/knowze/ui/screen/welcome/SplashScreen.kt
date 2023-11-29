package com.knowzeteam.knowze.ui.screen.welcome

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun SplashScreen(navController: NavController) {
    val textAlpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        textAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 3000)
        )
        // Navigate after the animation
        navController.navigate("onboarding_one") {
            // Remove splash screen from the back stack
            popUpTo("splash_screen") { inclusive = true }
        }
    }

    Text(
        text = "knowZe",
        fontSize = 30.sp,
        color = Color.Black,
        modifier = Modifier.alpha(textAlpha.value)
    )
}

