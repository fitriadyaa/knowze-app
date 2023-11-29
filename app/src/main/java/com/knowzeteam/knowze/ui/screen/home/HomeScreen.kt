package com.knowzeteam.knowze.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.component.SearchField
import com.knowzeteam.knowze.ui.theme.KnowzeTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )
            Text(
                text = "Welcome , Fitria",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
            )
        }
        SearchField()
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview(){
    KnowzeTheme {
        MaterialTheme {
            HomeScreen()
        }
    }
}