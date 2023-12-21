package com.knowzeteam.knowze.ui.component

import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.google.gson.Gson
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.data.local.AboutContentData
import com.knowzeteam.knowze.data.remote.response.courseResponse.SubtitlesItem
import com.knowzeteam.knowze.data.remote.response.videoresponse.VideoResponse
import com.knowzeteam.knowze.data.remote.response.videoresponse.VideosItem
import com.knowzeteam.knowze.ui.navigation.Screen
import com.knowzeteam.knowze.ui.theme.BorderColor
import com.knowzeteam.knowze.ui.theme.KnowzeTheme
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun CourseItem(
    subtitle : SubtitlesItem,
    navController : NavController
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = Color(0xFFF0F4FD),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("${Screen.DetailCourse.route}/${subtitle.content}")
            }
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
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                val encodedUrl = URLEncoder.encode(subtitle.link, StandardCharsets.UTF_8.toString())
                navController.navigate("${Screen.Youtube.route}/$encodedUrl")
            }
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