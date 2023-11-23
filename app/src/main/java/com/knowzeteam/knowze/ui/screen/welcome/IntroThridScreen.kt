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

    Spacer(modifier = Modifier.height(20.dp))

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ClickableText(
                onClick = { /* Buat Skip */ },
                text = AnnotatedString(
                    stringResource(R.string.skip_welcome)
                ),
                style = TextStyle(
                    color = MainColor,
                    fontSize = 18.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.End
                ),
                modifier = Modifier
                    .padding(start = 40.dp, bottom = 60.dp)
            )

            Button(
                onClick = { /* Buat next intro screen */ },
                colors = ButtonDefaults.buttonColors(MainColor),
                modifier = Modifier
                    .padding(end = 40.dp, bottom = 60.dp)
                    .size(56.dp, 56.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_navigate),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun IntroThridScreenPreview() {
    IntroThridScreen()
}