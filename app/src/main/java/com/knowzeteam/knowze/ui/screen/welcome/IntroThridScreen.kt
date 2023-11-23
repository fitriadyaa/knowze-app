package com.knowzeteam.knowze.ui.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.theme.MainColor
import com.knowzeteam.knowze.ui.theme.PoppinsFontFamily

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
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = stringResource(id = R.string.desc_intro3),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
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
            colors = ButtonDefaults.buttonColors(MainColor),
            modifier = Modifier
                .padding(bottom = 25.dp)
                .size(156.dp, 56.dp)
        ) {
            Text(
                text = stringResource(id = R.string.button_get_started),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun IntroThridScreenPreview() {
    IntroThridScreen()
}