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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.ui.component.BigMenuItem
import com.knowzeteam.knowze.ui.component.MenuItem


@Composable
fun AboutCourseScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        BannerCourse()

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
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 16.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, end = 10.dp)
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

                Spacer(modifier = Modifier.height(25.dp))

                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxSize()
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
                    )

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
                        // Rating Course //
                    }

                    Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp))

                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        // Deskripsi Course
                        Text(
                            text = stringResource(id = R.string.desc_title),
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontSize = 16.sp,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = stringResource(id = R.string.desc),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 14.sp,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Light
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(60.dp))

                        // Button Mulai
                        Button(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(327.dp, 60.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.start_now),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BannerCourse(
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

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp, start = 30.dp, end = 30.dp)
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
    }
}

@Preview(showBackground = true)
@Composable
fun AboutCourseScreenPreview() {
    KnowzeTheme {
        AboutCourseScreen()
    }
}