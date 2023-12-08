package com.knowzeteam.knowze.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.theme.KnowzeTheme

@Composable
fun CourseThemeItem(
    titleCourse: String,
    imgCourseTheme: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .size(151.dp, 76.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = imgCourseTheme),
            contentDescription = stringResource(R.string.theme_course_pict),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = 1.2f,
                    scaleY = 1.2f,
                    alpha = 0.8f
                )
        )

        Text(
            text = titleCourse,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
fun CourseThemePreview() {
    KnowzeTheme {
        CourseThemeItem(
            titleCourse = stringResource(id = R.string.course_theme_1),
            imgCourseTheme = R.drawable.ex_pict_course,
            onClick = { /*TODO*/ })
    }
}