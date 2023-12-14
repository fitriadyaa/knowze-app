package com.knowzeteam.knowze.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
    onSearch: () -> Unit,
    focusRequester: FocusRequester = FocusRequester(),
) {
    var searchText by remember { mutableStateOf(initialText) }

    SearchBarContent(
        searchText = searchText,
        onValueChange = {
            searchText = it
        },
        onSearchBarClick = { onSearch() },
        focusRequester = focusRequester,
    )
}

@Composable
private fun SearchBarContent(
    searchText: String,
    onValueChange: (String) -> Unit,
    onSearchBarClick: () -> Unit,
    focusRequester: FocusRequester = FocusRequester(),
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { onSearchBarClick() }
            .background(Color.White, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = searchText,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.primary,
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
                text = stringResource(R.string.search_value),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 16.dp),
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search Icon",
            contentScale = ContentScale.None,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp)

        )
    }
}

//@Preview(showBackground = true, device = "id:pixel_4")
//@Composable
//fun SearchBarPreview() {
//    KnowzeTheme {
//        SearchBar()
//    }
//}