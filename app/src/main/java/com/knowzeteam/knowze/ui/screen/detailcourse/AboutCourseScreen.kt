package com.knowzeteam.knowze.ui.screen.detailcourse

import android.util.Log
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.knowzeteam.knowze.data.local.AboutContentData
import com.knowzeteam.knowze.data.remote.response.courseResponse.CourseResponse
import com.knowzeteam.knowze.ui.ViewModelFactory
import com.knowzeteam.knowze.ui.component.CategoryButton
import com.knowzeteam.knowze.ui.navigation.Screen


@Composable
fun AboutCourseScreen(
    courseId: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    val viewModel: CourseViewModel = viewModel(
        factory = ViewModelFactory(context)
    )

    val courseDetails by viewModel.courseDetails.observeAsState()

    LaunchedEffect(courseId) {
        viewModel.fetchCourseDetails(courseId)
    }

    if (courseDetails != null) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            BannerCourse(navController)
            courseDetails?.let { course ->
                Box(
                    modifier = modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp)
                        )
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    CourseContent(course, navController, modifier, courseId)
                }
            } ?: run {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier.fillMaxSize()
                ) {
                }
            }
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = modifier.height(30.dp))
            GenerateCourseItem()
            Spacer(modifier = Modifier.height(25.dp))
            BannerGenerateCourse()
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
                    modifier = modifier
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Top,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, end = 10.dp)
                    ) {
                        ShimmerCategoryButton(categoryText = "", onClick = { /*TODO*/ })
                        Spacer(modifier = modifier.width(10.dp))
                        ShimmerCategoryButton(categoryText = "", onClick = { /*TODO*/ })
                    }
                    Spacer(modifier = modifier.height(25.dp))
                    ShimmerAnimation { brush ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .background(brush)
                        )
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    ShimmerAnimation { brush ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .background(brush)
                        )
                    }
                    Divider()
                    ShimmerAnimation { brush ->
                        // For the title
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .background(brush))

                        Spacer(Modifier.height(8.dp))

                        // For the description
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                            .background(brush))

                        Spacer(Modifier.height(8.dp))

                        // For additional content
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                            .background(brush))
                    }
                    Spacer(modifier = modifier.height(60.dp))
                    Button(
                        onClick = {  },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color.LightGray),
                        modifier = modifier
                            .fillMaxWidth()
                            .size(327.dp, 60.dp)
                    ) {
                        Text(
                            text = "Mulai Sekarang",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray
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

@Composable
fun CourseContent(
    courseData: CourseResponse,
    navController: NavController,
    modifier: Modifier,
    courseId: String,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 10.dp)
        ) {
            // Course Category: Photography
            CategoryButton(categoryText = courseData.themeActivity ?: "Tema", onClick = { /*TODO*/ }, colors = MaterialTheme.colorScheme.primary)
            Spacer(modifier = modifier.width(10.dp))
            CategoryButton(categoryText = courseData.typeActivity ?: "Tipe", onClick = { /*TODO*/ }, colors = Color(0xFFFF9900))
        }
        Spacer(modifier = modifier.height(25.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Judul Course
            Text(
                text = courseData.title ?: "Default Title",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
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
                    text = courseData.lessons.toString() + " Topic",
                    color = Color.Black
                )

                Spacer(modifier = modifier.width(10.dp))

                // Course Time //
                Image(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = "Course Time",
                    modifier = modifier
                )

                Spacer(modifier = modifier.width(4.dp))

                Text(
                    text = courseData.duration ?: "00.00",
                    color = Color.Black
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
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = modifier.height(10.dp))

                Text(
                    text = courseData.desc ?: "Description",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = modifier.height(60.dp))
            }

            // Button Mulai
            Button(
                onClick = {

                    courseData.id = courseId

                    val dataAbout = AboutContentData(courseData)

                    val gson = Gson()
                    val course = gson.toJson(dataAbout)

                    Log.d("jsonString", course)
                    println("jsonString_print $course")

                    navController.navigate("${Screen.AboutContent.route}/${course}")
                },
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
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun BannerCourse(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier) {
        Image(
            painter = painterResource(id = R.drawable.bg_course),
            contentDescription = "Gambar Course",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 60.dp, start = 10.dp, end = 30.dp)
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
                    tint = Color.White,
                    modifier = Modifier
                        .clickable {
                            Log.d("BannerCourse", "Back icon clicked")
                            navController.navigateUp()
                            Log.d("BannerCourse", "popBackStack called")
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AboutCourseScreenPreview() {
//    KnowzeTheme {
//        AboutCourseScreen(
//            courseResponse = CourseResponse(),
//            onButtonClick = {},
//            onBackClick = {}
//        )
//    }
//}