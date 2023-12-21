package com.knowzeteam.knowze.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.R

@Composable
fun SearchBar(
    initialText: String = "",
    focusRequester: FocusRequester = FocusRequester(),
    onSearchAction: (String) -> Unit,
    isLoading: Boolean?
) {
    var searchText by remember { mutableStateOf(initialText) }

    SearchBarContent(
        searchText = searchText,
        onValueChange = {
            searchText = it
        },
        onSearchAction = onSearchAction,
        focusRequester = focusRequester,
        isLoading = isLoading
    )
}

@Composable
private fun SearchBarContent(
    searchText: String,
    onValueChange: (String) -> Unit,
    onSearchAction: (String) -> Unit,
    isLoading: Boolean?,
    focusRequester: FocusRequester = FocusRequester(),
) {
    // Placeholder text
    val placeholderText = stringResource(R.string.search_value)

    // State for animated typing effect
    val animatedPlaceholder = remember { Animatable(0f) }
    LaunchedEffect(key1 = placeholderText) {
        animatedPlaceholder.animateTo(
            targetValue = if (searchText.isEmpty()) placeholderText.length.toFloat() else 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }
    val displayText = placeholderText.take(animatedPlaceholder.value.toInt())

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
            .clickable { focusRequester.requestFocus() }
            .focusRequester(focusRequester),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = searchText,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, start = 16.dp)
                .focusRequester(focusRequester)
        )
        if (searchText.isEmpty()) {
            Text(
                text = displayText,
                color = Color.Gray,
                modifier = Modifier.padding(start = 16.dp),
            )
        }

        if (isLoading == true) {
            // Display a loading indicator, e.g., a small CircularProgressIndicator
            CircularProgressIndicator(modifier = Modifier.size(32.dp).align(Alignment.CenterEnd).padding(end = 10.dp))
        } else {
            // Existing Image for search icon
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                contentScale = ContentScale.None,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterEnd)
                    .padding(end = 10.dp)
                    .clickable {
                        onSearchAction(searchText)
                    }
            )
        }
    }
}


//@Preview(showBackground = true, device = "id:pixel_4")
//@Composable
//fun SearchBarPreview() {
//    KnowzeTheme {
//        SearchBar()
//    }
//}