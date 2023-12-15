package com.knowzeteam.knowze.ui.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.knowzeteam.knowze.ui.ViewModelFactory
import com.knowzeteam.knowze.ui.component.SearchBar
import com.knowzeteam.knowze.ui.navigation.Screen

@Composable
fun HomeSearch(
    modifier: Modifier = Modifier,
    navController: NavController,
    onBack: () -> Unit,
    initialSearchText: String,
    shouldFocus: Boolean = false,
){
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    val viewModel: GenerateViewModel = viewModel(
        factory = ViewModelFactory(context)
    )

    val response by viewModel.response.observeAsState()

    // Navigate to AboutCourseScreen when course_id is available
    LaunchedEffect(response) {
        response?.courseId?.let { courseId ->
            // Ensure the route format matches the navigation graph setup
            val route = Screen.AboutCourse.route.replace("{courseId}", courseId)
            navController.navigate(route)
        }
    }

    LaunchedEffect(response) {
        response?.let {
            Log.d("APIResponse", "Response: $it")
        }
    }

    LaunchedEffect(shouldFocus) {
        if (shouldFocus) {
            focusRequester.requestFocus()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back"
                    )
                }
                SearchBar(
                    initialText = initialSearchText,
                    onSearchAction = { query ->
                        viewModel.postGenerateQuery(query)
                    },
                    focusRequester = focusRequester,
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            RecommendationContent()
            Spacer(modifier = Modifier.height(20.dp))
            SettingDuration()
        }
    }
}


@Composable
fun RecommendationContent(
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = "Rekomendasi untuk anda",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        Spacer(modifier = modifier.height(10.dp))
        BoxWithText(text = "Cara menanam cabe di rumah")
        BoxWithText(text = "Cara membuat bom nuklir")
        BoxWithText(text = "Cara nyaleg jadi DPR")
        BoxWithText(text = "Cara bikin layangan Garut")
    }
}

@Composable
fun BoxWithText(
    text: String,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier.padding(bottom = 10.dp)) {
        Box(
            modifier = Modifier
                .background(color = Color.White, RoundedCornerShape(12.dp))
                .padding(14.dp)
                .widthIn(max = maxWidth)
                .height(20.dp)
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun SettingDuration(
    modifier: Modifier = Modifier
) {
    val rangeLabels = listOf("Default", "8", "12", "16", "20", "24")

    var sliderValue by remember {
        mutableFloatStateOf(0f)
    }

    Column {
        Text(
            text = "Atur Durasi Kursus",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Slider(
                    value = sliderValue,
                    onValueChange = { newValue ->
                        sliderValue = newValue
                    },
                    onValueChangeFinished = {},
                    valueRange = 0f..5f,
                    steps = 4,
                    modifier = modifier,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary,
                        activeTrackColor = MaterialTheme.colorScheme.primary,
                        inactiveTrackColor = MaterialTheme.colorScheme.secondary,
                    ),
                )
                Row(
                    modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    rangeLabels.forEachIndexed { _, label ->
                        Text(
                            text = label,
                            modifier = Modifier
                                .weight(1f)
                                .padding(top = 8.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun HomeSearchPreview() {
//    KnowzeTheme {
//        HomeSearch()
//    }
//}