package com.knowzeteam.knowze.ui.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.theme.KnowzeTheme

@Composable
fun IntroThridScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_intro3),
            contentDescription = stringResource(R.string.icon_welcome),
            modifier = Modifier
                .size(324.dp, 316.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = stringResource(id = R.string.title_intro3),
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = stringResource(id = R.string.desc_intro3),
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp)
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = { /* Login */ },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .padding(bottom = 25.dp)
                .size(156.dp, 56.dp)
        ) {
            Text(
                text = stringResource(id = R.string.button_get_started),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun IntroThridScreenPreview() {
    KnowzeTheme {
        IntroThridScreen()
    }
}