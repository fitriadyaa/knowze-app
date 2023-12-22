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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.navigation.Screen

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val loginState by viewModel.loginState.collectAsState()

    when (loginState) {
        is LoginViewModel.LoginState.Success -> {
            // Navigate to home screen or show success message
            navController.navigate(Screen.Home.route)
        }
        is LoginViewModel.LoginState.Error -> {
            LaunchedEffect(key1 = loginState) {
                Toast.makeText(context, (loginState as LoginViewModel.LoginState.Error).message, Toast.LENGTH_LONG).show()
            }
        }
        LoginViewModel.LoginState.Loading -> {
            LoadingIndicator(

            ) // Show loading indicator
        }
        LoginViewModel.LoginState.Idle -> {
            // Handle idle state if needed
        }
        else -> {}
    }

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
            viewModel.googleLogin(account)
        } catch (e: ApiException) {
            Log.e("LoginScreen", "Google Sign-In failed", e)
        }
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
                painter = painterResource(R.drawable.logo),
                contentDescription = stringResource(R.string.icon_login),
                modifier = modifier
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = modifier.weight(1f))

            GoogleLoginButton(onClick = {
                val signInIntent = googleSignInClient.signInIntent
                launcher.launch(signInIntent)
            })


            Spacer(modifier = Modifier.weight(0.1f))

            EmailLoginButton(
                onClick = {
                    navController.navigate(Screen.EmailLogin.route)
                }
            )

            Spacer(modifier = Modifier.weight(0.1f))

            ClickableText(
                onClick = {navController.navigate(Screen.Register.route)},
                text = AnnotatedString(stringResource(id = R.string.register_message)),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
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
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.padding(start = 8.dp),
            color = Color.White
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
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.padding(start = 8.dp),
            color = Color.White
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