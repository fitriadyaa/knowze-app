package com.knowzeteam.knowze.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.theme.KnowzeTheme

@Composable
fun DialogFeedback(
    onRatingSelected: (Int) -> Unit,
    onSubmitFeedback: (String) -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var ratingState by remember { mutableIntStateOf(4) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(342.dp, 401.dp)
            .fillMaxSize()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            RatingBar(
                modifier = Modifier
                    .height(50.dp)
                    .width(290.dp),
                rating = ratingState,
                onRatingSelected = { rating ->
                    ratingState = rating
                    onRatingSelected(rating)
                }
            )

            Spacer(modifier = Modifier.height(25.dp))

            Column {
                Text(
                    text = stringResource(id = R.string.feedback_form),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light
                    )
                )

                CustomOutlinedTextField(
                    value = text,
                    labelText = "Masukan feedback anda disini...",
                    onValueChange = {
                        text = it
                    }
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = { onSubmitFeedback(text.text) },
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.feedback_button),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: TextFieldValue,
    labelText: String,
    onValueChange: (TextFieldValue) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = labelText) },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int,
    onRatingSelected: (Int) -> Unit
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
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clickable {
                        onRatingSelected(i)
                    },
                tint = if (i <= rating) Color(0xFFFFD700) else Color(0xFFA2ADB1)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FeedbackPreview() {
    KnowzeTheme {
        DialogFeedback(
            onRatingSelected = { /* handle rating selection */ },
            onSubmitFeedback = { /* handle feedback submission */ }
        )
    }
}
