package com.knowzeteam.knowze.ui.screen.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.ViewModelFactory
import com.knowzeteam.knowze.ui.component.CategoryButton
import com.knowzeteam.knowze.ui.screen.detailcourse.BoxContentOverlay
import com.knowzeteam.knowze.ui.screen.detailcourse.CourseViewModel
import com.knowzeteam.knowze.ui.theme.KnowzeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseGallery(
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val viewModel: CourseGalleryViewModel = viewModel(
        factory = ViewModelFactory(context)
    )
    // Using collectAsLazyPagingItems for PagingData
    val coursesFlow = viewModel.allCourses.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.course_gallery),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {onBack() }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Localized description",
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyColumn {
                items(coursesFlow) { course ->
                    course?.let {
                        CardItem(
                            titleCourse = it.title ?: "Unknown Title",
                            onClick = { /* Handle card click */ }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                coursesFlow.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item { LoadingItem() }
                        }
                        loadState.append is LoadState.Loading -> {
                            item { LoadingItem() }
                        }
                    }
                }
            }
            if (coursesFlow.itemCount == 0 && coursesFlow.loadState.refresh is LoadState.NotLoading) {
                Text("No courses available", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
@Composable
fun CardItem(
    titleCourse: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Image(
            painter = painterResource(R.drawable.bg_knowze),
            contentDescription = stringResource(R.string.theme_course_pict),
            contentScale = ContentScale.Crop,
        )
//        BoxContentOverlay(modifier = Modifier.fillMaxSize())
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = titleCourse,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
fun LoadingItem() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        CircularProgressIndicator()
    }
}


@Preview
@Composable
fun CourseGalleryPreview() {
    KnowzeTheme {
        CourseGallery(
            onBack = {}
        )
    }
}