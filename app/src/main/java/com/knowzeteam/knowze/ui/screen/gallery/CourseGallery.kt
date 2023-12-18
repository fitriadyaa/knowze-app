package com.knowzeteam.knowze.ui.screen.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.component.CategoryButton
import com.knowzeteam.knowze.ui.screen.detailcourse.BoxContentOverlay
import com.knowzeteam.knowze.ui.theme.KnowzeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseGallery() {
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
                    IconButton(onClick = { }) {
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
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            CardItem(
                titleCourse = stringResource(id = R.string.course_theme_detail_1),
                imgCourseTheme = R.drawable.img_hike,
                ratingCourse = stringResource(id = R.string.course_rating),
                onClick = { /*TODO*/ }
            )
        }
    }
}
@Composable
fun CardItem(
    titleCourse: String,
    imgCourseTheme: Int,
    ratingCourse: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(140.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .fillMaxSize()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .shadow(4.dp, RoundedCornerShape(10.dp))
    ) {
        Box {
            Image(
                painter = painterResource(id = imgCourseTheme),
                contentDescription = stringResource(R.string.theme_course_pict),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            BoxContentOverlay(modifier = Modifier.fillMaxSize())
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CategoryRow()
                Spacer(modifier = Modifier.width(60.dp))
                RatingRow(ratingCourse)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = titleCourse,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Composable
fun CategoryRow() {
    Row {
        CategoryButton(categoryText = "Olahraga", onClick = { /*TODO*/ })
        Spacer(modifier = Modifier.width(10.dp))
        CategoryButton(categoryText = "Outdoor", onClick = { /*TODO*/ })
    }
}

@Composable
fun RatingRow(ratingCourse: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = "Course Time",
            modifier = Modifier.size(24.dp) // Adjusted size for better proportion
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = ratingCourse,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // Changed to white for better readability
            ),
        )
    }
}

@Preview
@Composable
fun CourseGalleryPreview() {
    KnowzeTheme {
        CourseGallery()
    }
}