package com.knowzeteam.knowze.ui.screen.detailcourse

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.component.CategoryButton
import com.knowzeteam.knowze.ui.theme.BorderColor
import com.knowzeteam.knowze.ui.theme.KnowzeTheme
import com.valentinilk.shimmer.shimmer

@Composable
fun GeneratingCourseScreen(
    modifier: Modifier = Modifier
) {
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
                            ),
                            modifier = modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
}

@Composable
fun GenerateCourseItem() {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(Alignment.CenterVertically),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(30.dp),
                color = Color.White
            )
            Spacer(modifier = Modifier.width(35.dp))
            Text(
                text = "Generating...",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}

@Composable
fun BannerGenerateCourse() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.generate_message),
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_generate_course),
            contentDescription = "Generate Icon",
            modifier = Modifier
                .size(145.dp, 145.dp)
                .weight(1f)
        )
    }
}

@Composable
fun ShimmerCategoryButton(categoryText: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Color.LightGray),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.height(28.dp)
    ) {
        Text(
            text = categoryText,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 10.sp, // Adjust the font size as needed
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}

@Composable
fun ShimmerAnimation(
    content: @Composable (brush: Brush) -> Unit
) {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.9f)
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = LinearEasing),
            RepeatMode.Restart
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    content(brush)
}


@Preview(showBackground = true)
@Composable
fun GeneratingCoursePreview() {
    KnowzeTheme {
        GeneratingCourseScreen()
    }
}