package com.knowzeteam.knowze.ui.screen.auth.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.component.EmailTextField
import com.knowzeteam.knowze.ui.component.PasswordTextField
import com.knowzeteam.knowze.ui.navigation.Screen
import com.knowzeteam.knowze.ui.theme.KnowzeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginWithEmailScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    androidx.compose.material3.Text(
                        text = stringResource(id = R.string.login_here),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.Login.route)}) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = {
            Box (
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = modifier.fillMaxSize()
                ) {
                    Text(
                        text = stringResource(id = R.string.login_here),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Box {
                        Column {
                            Text(
                                text = stringResource(id = R.string.email),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Light,
                                    textAlign = TextAlign.Start
                                )
                            )

                            EmailTextField(label = stringResource(id = R.string.email_example))

                            Spacer(modifier = Modifier.height(25.dp))

                            Text(
                                text = stringResource(id = R.string.password),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Light,
                                    textAlign = TextAlign.Start
                                )
                            )

                            PasswordTextField(label = stringResource(id = R.string.password_example))
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    ClickableText(
                        onClick = { /*TODO*/ },
                        text = AnnotatedString(stringResource(id = R.string.forgot_password)),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    LoginButton(onClick = { /*TODO*/ })

                    Spacer(modifier = Modifier.height(10.dp))

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
                }
            }
        }
    )
}

@Composable
fun LoginButton(
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
        androidx.compose.material3.Text(
            text = stringResource(R.string.login),
            modifier = modifier.padding(start = 8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LoginWithEmailPreview() {
    KnowzeTheme {
        LoginWithEmailScreen(
            navController = rememberNavController()
        )
    }
}