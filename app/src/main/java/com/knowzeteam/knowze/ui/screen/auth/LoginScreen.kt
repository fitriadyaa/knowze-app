package com.knowzeteam.knowze.ui.screen.auth

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.navigation.Screen

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Configure Google Sign In
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()
    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Handle the result of the sign-in intent
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            // Notify ViewModel for Google auth
            viewModel.firebaseAuthWithGoogle()
        } catch (e: ApiException) {
            // Handle exception
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

            Spacer(modifier = Modifier.weight(0.1f))

            EmailLoginButton(
                onClick = { /*TODO*/ }
            )

            Spacer(modifier = Modifier.weight(0.1f))

            ClickableText(
                onClick = { /*TODO*/ },
                text = AnnotatedString(stringResource(id = R.string.register_message)),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.fillMaxWidth()
            )

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

/*
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    KnowzeTheme {
        LoginScreen()
    }
}*/
