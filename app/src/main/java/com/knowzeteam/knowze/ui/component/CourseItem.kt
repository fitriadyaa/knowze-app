package com.knowzeteam.knowze.ui.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.data.local.AboutContentData
import com.knowzeteam.knowze.data.remote.response.courseResponse.SubtitlesItem
import com.knowzeteam.knowze.data.remote.response.videoresponse.VideosItem
import com.knowzeteam.knowze.ui.navigation.Screen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun CourseItem(
    subtitle : SubtitlesItem,
    courseId : String,
    navController : NavController
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = Color(0xFFF0F4FD),
                shape = RoundedCornerShape(15.dp)
            )
            .fillMaxWidth()
            .clickable {
                subtitle.content?.let {
                    navController.navigate("${Screen.DetailCourse.route}/${courseId}")
                }
            }
            .padding(16.dp)

    ) {
        Column {
            Text(
                text = subtitle.topic ?: "topic",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "1 minute",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
            )
        }
    }
}

@Composable
fun VideoItem(
    subtitle : VideosItem,
    navController: NavController
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = Color(0xFFF0F4FD),
                shape = RoundedCornerShape(15.dp)
            )
            .fillMaxWidth()
            .clickable {
                val encodedUrl = URLEncoder.encode(subtitle.id, StandardCharsets.UTF_8.toString())
                navController.navigate("${Screen.Youtube.route}/$encodedUrl")
            }
            .padding(16.dp)
    ) {
        Box {
            Image(
                painter = painterResource(R.drawable.ic_knowze),
                contentDescription = "Gambar Course",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(70.dp, 70.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Icon Play Course",
                modifier = Modifier
                    .size(24.dp, 24.dp)
                    .align(Alignment.Center),
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Column {
            Text(
                text = subtitle.title ?: "topic",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = subtitle.channel.toString(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
            )
        }
    }
}


//@Preview
//@Composable
//fun CourseItemPreview() {
//    KnowzeTheme {
//        CourseItem(
//            courseTitle = "1. Intro to Photography",
//            courseDuration = "1min",
//            imageResId = R.drawable.ex_pict_course
//        )
//    }
//}