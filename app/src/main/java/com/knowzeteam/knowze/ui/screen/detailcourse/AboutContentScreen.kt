package com.knowzeteam.knowze.ui.screen.detailcourse

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import com.knowzeteam.knowze.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.knowzeteam.knowze.data.local.AboutContentData
import com.knowzeteam.knowze.ui.ViewModelFactory
import com.knowzeteam.knowze.ui.component.CategoryButton
import com.knowzeteam.knowze.ui.component.CourseItem
import com.knowzeteam.knowze.ui.component.VideoItem

@Composable
fun AboutContentScreen(
    course: AboutContentData,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val viewModel: CourseViewModel = viewModel(
        factory = ViewModelFactory(context)
    )

    LaunchedEffect(course) {
        course.id?.let { viewModel.fetchCourseDetails(it) }
        course.title?.let { viewModel.fetchVideos(course.title, course.id ?: "") }
    }

    val videoData by viewModel.videos.observeAsState()
    val courseDetails by viewModel.courseDetails.observeAsState()
    val error by viewModel.error.observeAsState()

    Log.d("courseDetails_id", course.id.orEmpty())


    Log.d("courseDetails", courseDetails?.subtitles.toString())

    Column(modifier = modifier.fillMaxSize()) {
        BannerContent(course = course, modifier = modifier, navController = navController )
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier.padding(14.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    item {
                        Text(text = "List Kursus", color = Color.Black, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                    }

                    courseDetails?.subtitles?.let { subtitles ->
                        items(subtitles) { subtitle ->
                            subtitle?.let {
                                Spacer(modifier = Modifier.height(10.dp))
                                CourseItem(subtitle = it, course.id.toString() , navController)
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp)) // Space between the two lists
                        Text(text = "Video", color = Color.Black, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                    }

                    // Display the error message for HTTP 500 error
                    if (error == "Maaf video belum tersedia") {
                       item {
                           Text(
                               text = error ?: "",
                               modifier = Modifier.padding(16.dp),
                               textAlign = TextAlign.Center,
                               color = Color.Red
                           )
                       }
                    } else {
                        videoData?.videos?.let { videos ->
                            items(videos) { video ->
                                video?.let {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    VideoItem(
                                        subtitle = it,
                                        navController = navController
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BannerContent(
    course: AboutContentData,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier) {
        Image(
            painter = painterResource(id = R.drawable.bg_surface),
            contentDescription = "Gambar Course",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        )
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 10.dp, start = 10.dp, end = 30.dp)
        ) {
            Surface(
                modifier = modifier
                    .padding(top = 10.dp, start = 10.dp)
                    .size(56.dp)
                    .clip(CircleShape),
                color = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Next",
                    tint = Color.White,
                    modifier = modifier
                        .clickable(onClick = { navController.popBackStack() })
                        .padding(16.dp)
                )
            }
            Spacer(modifier = modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp)
            ) {
                CategoryButton(categoryText = course.themeActivity ?: "", onClick = { /*TODO*/ }, colors = MaterialTheme.colorScheme.primary)
                Spacer(modifier = modifier.width(10.dp))
                CategoryButton(categoryText = course.typeActivity ?: "", onClick = { /*TODO*/ }, colors = Color(0xFFFF9900))
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = modifier
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp)
            ) {
                Text(
                    text = course.title ?: "Default Title",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "Course Time",
                        modifier = modifier
                    )
                    Spacer(modifier = modifier.width(4.dp))
                    Text(
                        text = stringResource(id = R.string.course_rating),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black
                        ),
                    )
                    Spacer(modifier = modifier.width(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = "Course Time",
                        modifier = modifier
                            .size(24.dp)
                    )
                    Spacer(modifier = modifier.width(4.dp))
                    Text(
                        text = course.duration ?: "days",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black
                        ),
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun AboutContentScreenPreview() {
//    KnowzeTheme {
//        AboutContentScreen(
//            course = CourseResponse(
//                id ="1",
//                duration = "2 Jam",
//                subtitles = emptyList(),
//                typeActivity = "Belajar",
//                title = "belajar",
//                themeActivity = "Siang",
//                desc = "belajar dulu",
//                lessons = 1
//            ),
//            navController = NavController(context = LocalContext.current)
//        )
//    }
//}