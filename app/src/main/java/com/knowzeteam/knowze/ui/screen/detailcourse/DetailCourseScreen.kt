package com.knowzeteam.knowze.ui.screen.detailcourse

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.theme.KnowzeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCourseScreen() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Detail Course",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(375.dp, 200.dp)
                        .background(Color.Gray)
                ) {
                    Text(text = "Ini Buat Video Player")
                }

                Spacer(modifier = Modifier.height(25.dp))

                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.title_detail),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = stringResource(id = R.string.course_detail_1),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = stringResource(id = R.string.course_detail_2),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        )
                    )
                }
            }
        }
    }
}

@SuppressLint("OpaqueUnitKey")
@Composable
fun VideoPlayer(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var playWhenReady by remember { mutableStateOf(true) }
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.EMPTY)
            /* setMediaItem(MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")) */
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            playWhenReady = playWhenReady
            prepare()
            play()
        }
    }
    
    DisposableEffect(
        AndroidView(
            modifier = Modifier
                .fillMaxSize(),
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = true
                    FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            }
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}

@Preview
@Composable
fun DetailCoursePreview() {
    KnowzeTheme {
        DetailCourseScreen()
    }
}