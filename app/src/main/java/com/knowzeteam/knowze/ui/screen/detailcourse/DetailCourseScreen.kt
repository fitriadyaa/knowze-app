package com.knowzeteam.knowze.ui.screen.detailcourse

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.knowzeteam.knowze.data.remote.response.courseResponse.Content
import com.knowzeteam.knowze.ui.ViewModelFactory
import com.knowzeteam.knowze.ui.theme.KnowzeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCourseScreen(
    courseId : String,
    navController: NavController
) {

    val context = LocalContext.current

    val viewModel: CourseViewModel = viewModel(
        factory = ViewModelFactory(context)
    )

    val courseDetails by viewModel.courseDetails.observeAsState()
    
    LaunchedEffect(courseId) {
        viewModel.fetchCourseDetails(courseId)
    }

    Log.d("idnya", courseId)

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
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = courseDetails?.subtitles?.get(0)?.content?.opening.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            courseDetails?.subtitles?.get(0)?.content?.steps?.forEach {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text =  it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text =  courseDetails?.subtitles?.get(0)?.content?.closing.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }
    }
}
//@Preview
//@Composable
//fun DetailCoursePreview() {
//    KnowzeTheme {
//        DetailCourseScreen(
//            content = Content(
//                opening = "Sample Opening",
//                steps = listOf("Step 1", "Step 2"),
//                closing = "Sample Closing"
//            ),
//            navController = rememberNavController()
//        )
//    }
//}
