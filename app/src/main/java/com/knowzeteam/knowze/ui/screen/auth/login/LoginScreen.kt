package com.knowzeteam.knowze.ui.screen.auth.login

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.data.remote.retrofit.ApiConfig
import com.knowzeteam.knowze.ui.ViewModelFactory
import com.knowzeteam.knowze.ui.navigation.Screen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.knowzeteam.knowze.repository.DashboardRepository
import com.knowzeteam.knowze.repository.DashboardRepositoryImpl
import com.knowzeteam.knowze.utils.UserPreferencesRepository


@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Configure Google Sign In
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()
    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            // Notify ViewModel for Google auth
            viewModel.firebaseAuthWithGoogle()
        } catch (e: ApiException) {
            Log.e("LoginScreen", "Google Sign-In failed", e)
        }
    }

    val dashboardData by viewModel.dashboardData.observeAsState()
    LaunchedEffect(dashboardData) {
        dashboardData?.let {
            navController.navigate(Screen.Home.route)
        }
    }

    // Show a toast on error
    error?.let {
        Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_LONG).show()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Spacer(modifier = modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.ic_login),
                contentDescription = stringResource(R.string.icon_login),
                modifier = modifier
                    .size(290.dp, 286.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = modifier.weight(1f))

            GoogleLoginButton(onClick = {
                val signInIntent = googleSignInClient.signInIntent
                launcher.launch(signInIntent)
            })

            // Observe ViewModel states
            val isLoading by viewModel.isLoading.collectAsState()
            val idToken by viewModel.idToken.observeAsState()
            val error by viewModel.error.observeAsState()

            // UI response to ViewModel states...
            if (isLoading) {
                CircularProgressIndicator()
            }

            idToken?.let {
                // Navigate to next screen
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }

            error?.let {
                Toast.makeText(context, "Login failed, please try again or check your internet connection", Toast.LENGTH_LONG).show()
            }
        }
    }
}
@Composable
fun GoogleLoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = stringResource(R.string.login_with_google),
            modifier = modifier
                .size(24.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Text(
            text = stringResource(R.string.login_with_google),
            modifier = modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun EmailLoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_email),
            contentDescription = stringResource(R.string.login_with_email),
            modifier = modifier
                .size(24.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Text(
            text = stringResource(R.string.login_with_email),
            modifier = modifier.padding(start = 8.dp)
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    KnowzeTheme {
//        LoginScreen()
//    }
//}