package com.knowzeteam.knowze.ui.screen.detailcourse

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun AboutContentScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BannerContent()

        Box(
            modifier = Modifier
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
                        modifier = Modifier
                            .padding(top = 20.dp)
                    )

                    Text(
                        text = stringResource(id = R.string.topic),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        ),
                        modifier = Modifier
                            .padding(top = 20.dp, end = 10.dp)
                    )
                }

                Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp))

                Box {
                    LazyColumn(
                        contentPadding = PaddingValues(
                            top = 0.dp,
                            end = 16.dp,
                            bottom = 10.dp
                        ),
                    ) {
                        // Buat Lazy Column, Template Card/Item ada di component/CourseItem.kt
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
        Image(
            painter = painterResource(id = R.drawable.ex_pict_course),
            contentDescription = "Gambar Course",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .size(375.dp, 338.dp)
        )

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
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(56.dp)
                        .clip(CircleShape),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Next",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp)
            ) {
                // Course Category: Photography
                Button(
                    onClick = { /*TODO*/ },
                    colors =ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier
                        .size(109.dp, 25.dp)
                ) {
                    Text(
                        text = "Photography",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                // Course Category: Indoor
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier
                        .size(109.dp, 25.dp)
                ) {
                    Text(
                        text = "indoor",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp)
            ) {
                // Judul Course
                Text(
                    text = stringResource(id = R.string.course_title),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, end = 10.dp)
                ) {
                    // Rating Course //
                    Image(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "Course Time",
                        modifier = Modifier
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = stringResource(id = R.string.course_rating)
                    )
                    // Rating Course //

                    Spacer(modifier = Modifier.width(10.dp))

                    // Course Time //
                    Image(
                        painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = "Course Time",
                        modifier = Modifier
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = stringResource(id = R.string.course_time)
                    )
                    // Course Time //
                }
            }
        }
    }
}

@Preview
@Composable
fun AboutContentScreenPreview() {
    KnowzeTheme {
        AboutContentScreen()
    }
}