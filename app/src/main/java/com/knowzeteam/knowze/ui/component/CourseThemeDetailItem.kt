package com.knowzeteam.knowze.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
fun CourseThemeDetailItem(
    titleCourse: String,
    imgCourseTheme: Int,
    ratingCourse: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(360.dp, 140.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .fillMaxSize()
            .fillMaxWidth()
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp, top = 10.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "Course Time",
                    modifier = modifier
                )

                Spacer(modifier = modifier.width(4.dp))

                Text(
                    text = ratingCourse,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, top = 35.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                CategoryButton(categoryText = "Olahraga", onClick = { /*TODO*/ }, colors = MaterialTheme.colorScheme.primary)
                Spacer(modifier = modifier.width(10.dp))
                CategoryButton(categoryText = "Outdoor", onClick = { /*TODO*/ }, colors = Color(0xFFFF9900))
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = titleCourse,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseThemeDetailPreview() {
    KnowzeTheme {
        CourseThemeDetailItem(
            titleCourse = stringResource(id = R.string.course_theme_detail_1),
            imgCourseTheme = R.drawable.ex_pict_course,
            ratingCourse = stringResource(id = R.string.course_rating),
            onClick = { /*TODO*/ })
    }
}