package com.knowzeteam.knowze.ui.screen.detailcourse

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.theme.KnowzeTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.ui.component.CategoryButton
import com.knowzeteam.knowze.ui.component.CourseItem

@Composable
fun AboutContentScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        BannerContent()

        Box(
            modifier = modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp)
                )
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.course_content),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = modifier
                            .padding(top = 20.dp)
                    )

                    Text(
                        text = stringResource(id = R.string.topic),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        ),
                        modifier = modifier
                            .padding(top = 20.dp, end = 10.dp)
                    )
                }

                Divider(modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 16.dp, bottom = 10.dp)
                ) {
                    LazyColumn {
                        item {
                            CourseItem(
                                courseTitle = "1. Intro to Photography",
                                courseDuration = "1min",
                                imageResId = R.drawable.ex_pict_course
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun BannerContent(
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .size(375.dp, 338.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ex_pict_course),
                contentDescription = "Gambar Course",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxSize()
            )

            // Overlay
            BoxContentOverlay(modifier = modifier)
        }

        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, start = 10.dp, end = 30.dp)
            ) {

                Surface(
                    modifier = modifier
                        .padding(top = 10.dp)
                        .size(56.dp)
                        .clip(CircleShape),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Next",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = modifier
                            .padding(16.dp)
                    )
                }
            }

            Spacer(modifier = modifier.height(25.dp))

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp)
            ) {
                CategoryButton(categoryText = "Photography", onClick = { /*TODO*/ })
                Spacer(modifier = modifier.width(10.dp))
                CategoryButton(categoryText = "Indoor", onClick = { /*TODO*/ })
            }

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = modifier
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp)
            ) {
                // Judul Course
                Text(
                    text = stringResource(id = R.string.course_title),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                Spacer(modifier = modifier.height(25.dp))

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, end = 10.dp)
                ) {
                    // Rating Course //
                    Image(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "Course Time",
                        modifier = modifier
                    )

                    Spacer(modifier = modifier.width(4.dp))

                    Text(
                        text = stringResource(id = R.string.course_rating),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White
                        ),
                    )

                    Spacer(modifier = modifier.width(10.dp))

                    // Course Time //
                    Image(
                        painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = "Course Time",
                        modifier = modifier
                            .size(24.dp)
                    )

                    Spacer(modifier = modifier.width(4.dp))

                    Text(
                        text = stringResource(id = R.string.course_time),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White
                        ),
                    )
                }
            }
        }
    }
}

@Composable
fun BoxContentOverlay(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.4f))
    )
}

@Preview
@Composable
fun AboutContentScreenPreview() {
    KnowzeTheme {
        AboutContentScreen()
    }
}