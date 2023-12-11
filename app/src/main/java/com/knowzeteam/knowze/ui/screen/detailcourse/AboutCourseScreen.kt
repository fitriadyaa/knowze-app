package com.knowzeteam.knowze.ui.screen.detailcourse

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.theme.KnowzeTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.knowzeteam.knowze.ui.component.CategoryButton
import com.knowzeteam.knowze.ui.component.ReviewItem


@Composable
fun AboutCourseScreen(
    courseTitle: String,
    courseDetail: String,
    courseDuration: String,
    onBackClick: () -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        BannerCourse(
            onBackClick = onBackClick
        )

        Box(
            modifier = modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp)
                )
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
                    .padding(/*bottom = 16.dp*/)
            ) {

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, end = 10.dp)
                ) {
                    // Course Category: Photography
                    CategoryButton(categoryText = "Photography", onClick = { /*TODO*/ })
                    Spacer(modifier = modifier.width(10.dp))
                    CategoryButton(categoryText = "Indoor", onClick = { /*TODO*/ })
                }
                Spacer(modifier = modifier.height(25.dp))

                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    // Judul Course
                    Text(
                        text = courseTitle,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 24.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = modifier
                            .fillMaxWidth()
                    )

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
                            text = stringResource(id = R.string.course_rating)
                        )
                        // Rating Course //

                        Spacer(modifier = modifier.width(10.dp))

                        // Course Time //
                        Image(
                            painter = painterResource(id = R.drawable.ic_time),
                            contentDescription = "Course Time",
                            modifier = modifier
                        )

                        Spacer(modifier = modifier.width(4.dp))

                        Text(
                            text = courseDuration,
                        )
                    }

                    Divider(modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp))

                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = modifier
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
                            modifier = modifier
                                .fillMaxWidth()
                        )

                        Spacer(modifier = modifier.height(10.dp))

                        Text(
                            text = courseDetail,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 14.sp,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Light
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Divider(modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp))

                        Text(
                            text = stringResource(R.string.reviews_section),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 16.sp,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Spacer(modifier = modifier.height(25.dp))

                        LazyColumn {
                            item {
                                ReviewItem(
                                    user_profile_picture = R.drawable.user_picture_1,
                                    user_name = stringResource(id = R.string.user_name_1),
                                    review_time = stringResource(id = R.string.review_time_1),
                                    feedback = stringResource(id = R.string.feedback_1),
                                    ratingValue = 4,
                                    rating = stringResource(id = R.string.rating_1)
                                )
                            }

                            item {
                                Divider(modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp))
                                ReviewItem(
                                    user_profile_picture = R.drawable.user_picture_2,
                                    user_name = stringResource(id = R.string.user_name_2),
                                    review_time = stringResource(id = R.string.review_time_2),
                                    feedback = stringResource(id = R.string.feedback_2),
                                    ratingValue = 4,
                                    rating = stringResource(id = R.string.rating_2)
                                )
                            }

                            item {
                                Divider(modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp))
                                ReviewItem(
                                    user_profile_picture = R.drawable.user_picture_3,
                                    user_name = stringResource(id = R.string.user_name_3),
                                    review_time = stringResource(id = R.string.review_time_3),
                                    feedback = stringResource(id = R.string.feedback_3),
                                    ratingValue = 4,
                                    rating = stringResource(id = R.string.rating_3)
                                )
                            }
                        }

                        Spacer(modifier = modifier.height(60.dp))

                        // Button Mulai
                        Button(
                            onClick = onButtonClick,
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                            modifier = modifier
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
                                modifier = modifier
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
    onBackClick:  () -> Unit,
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
                .padding(bottom = 60.dp, start = 10.dp, end = 30.dp)
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
                        .clickable(onClick = onBackClick)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutCourseScreenPreview() {
    KnowzeTheme {
        AboutCourseScreen(
            courseTitle = stringResource(id = R.string.course_title),
            courseDetail = stringResource(id = R.string.desc),
            courseDuration = stringResource(id = R.string.course_time),
            onButtonClick = {},
            onBackClick = {}
        )
    }
}