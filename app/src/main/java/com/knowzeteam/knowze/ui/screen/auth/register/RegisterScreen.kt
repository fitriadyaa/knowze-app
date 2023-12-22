package com.knowzeteam.knowze.ui.screen.auth.register

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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.component.EmailTextField
import com.knowzeteam.knowze.ui.component.NameTextField
import com.knowzeteam.knowze.ui.component.PasswordTextField
import com.knowzeteam.knowze.ui.navigation.Screen
import com.knowzeteam.knowze.ui.theme.KnowzeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                ),
                title = {
                    androidx.compose.material.Text(
                        text = stringResource(id = R.string.register),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Localized description",
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxSize()
            ) {
                Box {
                    Column {
                        Text(
                            text = stringResource(id = R.string.write_your_name_here),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                                textAlign = TextAlign.Start,
                                color = Color.Black
                            )
                        )

                        NameTextField(label = stringResource(id = R.string.your_name))

                        Spacer(modifier = Modifier.height(30.dp))

                        Text(
                            text = stringResource(id = R.string.email),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                                textAlign = TextAlign.Start,
                                color = Color.Black
                            )
                        )

                        EmailTextField(label = stringResource(id = R.string.email_example))

                        Spacer(modifier = Modifier.height(25.dp))

                        Text(
                            text = stringResource(id = R.string.password),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                                textAlign = TextAlign.Start,
                                color = Color.Black
                            )
                        )

                        PasswordTextField(label = stringResource(id = R.string.password_example))

                        Spacer(modifier = Modifier.height(25.dp))

                        Text(
                            text = stringResource(id = R.string.repeat_password),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                                textAlign = TextAlign.Start,
                                color = Color.Black
                            )
                        )

                        PasswordTextField(label = stringResource(id = R.string.password_example))
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))

                RegisterButton(onClick = { /*TODO*/ })
            }
        }
    }
}

@Composable
fun RegisterButton(
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
        Text(
            text = stringResource(R.string.register),
            modifier = modifier.padding(start = 8.dp),
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    KnowzeTheme {
        RegisterScreen(
            navController = rememberNavController()
        )
    }
}