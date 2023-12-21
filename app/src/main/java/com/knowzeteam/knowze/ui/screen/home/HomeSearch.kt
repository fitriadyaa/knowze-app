package com.knowzeteam.knowze.ui.screen.home

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
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
    val generateLoading by viewModel.isLoadingGenerate.observeAsState()

    LaunchedEffect(response) {
        if (response?.courseId != null) {
            navController.navigate("${Screen.AboutCourse.route}/${response?.courseId.toString()}") {
                // Clears the back stack up to the start destination of the navigation graph
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(response) {
        response?.let {
            Log.d("APIResponse", "Response: $it")
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchRecommendations()
    }


//    LaunchedEffect(response) {
//        isLoading = true
//        if (response?.courseId != null) {
//            isLoading = false
//        }
//    }


    val recommendations by viewModel.recommendations.observeAsState()


    LaunchedEffect(shouldFocus) {
        if (shouldFocus) {
            focusRequester.requestFocus()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                SearchBar(
                    initialText = initialSearchText,
                    onSearchAction = { query ->
                        viewModel.onSearch(query)

                    },
                    focusRequester = focusRequester,
                    isLoading = generateLoading
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            RecommendationContent(recommendations = recommendations)
            Spacer(modifier = Modifier.height(20.dp))
            SettingDuration()
        }
    }
}


@Composable
fun RecommendationContent(
    recommendations: List<String?>?,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = "Rekomendasi untuk anda",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        Spacer(modifier = modifier.height(10.dp))

        // Check if the recommendations list is null or empty
        if (recommendations.isNullOrEmpty()) {
            // Display dummy recommendations
            val dummyRecommendations = listOf("Cara membuat hidroponik", "Belajar membuat aplikasi android", "Cara membuka tutup kaleng sarden")
            dummyRecommendations.forEach { dummyRecommendation ->
                BoxWithText(text = dummyRecommendation)
            }
        } else {
            // Display actual recommendations
            recommendations.forEach { recommendation ->
                recommendation?.let {
                    BoxWithText(text = it)
                }
            }
        }
    }
}

@Composable
fun BoxWithText(
    text: String,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier = modifier.padding(bottom = 10.dp)) {
        Box(
            modifier = modifier
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(10.dp)
                .height(16.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
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
            style = MaterialTheme.typography.titleMedium.copy(
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
                        inactiveTrackColor = Color.LightGray
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
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun shimmerAnimationEffect(
    shimmerColor: Color = Color.LightGray.copy(alpha = 0.7f)
): Brush {
    val shimmerWidth = 0.2f
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val xShimmer = animation.value

    return Brush.linearGradient(
        colors = listOf(shimmerColor, shimmerColor.copy(alpha = 0.2f), shimmerColor),
        start = Offset.Zero,
        end = Offset(xShimmer + shimmerWidth, 0f)
    )
}

//@Preview
//@Composable
//fun HomeSearchPreview() {
//    KnowzeTheme {
//        HomeSearch()
//    }
//}