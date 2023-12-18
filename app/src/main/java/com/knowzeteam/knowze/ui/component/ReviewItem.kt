package com.knowzeteam.knowze.ui.component

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.theme.KnowzeTheme

@Composable
fun ReviewItem(
    user_profile_picture: Int,
    user_name: String,
    review_time: String,
    feedback: String,
    ratingValue: Int,
    rating: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .size(500.dp, 102.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, bottom = 10.dp, start = 10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = user_profile_picture),
                    contentDescription = "Gambar User",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = user_name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.width(150.dp))

                Text(
                    text = review_time + " month ago",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Gray
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = feedback,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
            )
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(top = 60.dp, start = 10.dp)
        ) {
            RatingCourse(rating = ratingValue)

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = rating,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
            )
        }
    }
}

@Composable
fun RatingCourse(
    modifier: Modifier = Modifier,
    rating: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..5) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "star",
                modifier = modifier
                    .size(25.dp),
                tint = if (i <= rating) Color(0xFFFFD700) else Color(0xFFA2ADB1)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewItemPreview() {
    KnowzeTheme {
        ReviewItem(
            user_profile_picture = R.drawable.user_picture_1,
            user_name = stringResource(id = R.string.user_name_1),
            review_time = stringResource(id = R.string.review_time_1),
            feedback = stringResource(id = R.string.feedback_1),
            ratingValue = 4,
            rating = stringResource(id = R.string.rating_1)
        )
    }
}