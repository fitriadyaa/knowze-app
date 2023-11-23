package com.knowzeteam.knowze.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.theme.KnowzeTheme
@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }

    SearchBarContent(searchText, onValueChange = { searchText = it })
}

@Composable
private fun SearchBarContent(searchText: String, onValueChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp)
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
                .padding(end = 24.dp)
        )
        if (searchText.isEmpty()) {
            Text(
                text = "Cara membuka tutup kaleng...",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search Icon",
            contentScale = ContentScale.None,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun SearchBarPreview() {
    KnowzeTheme {
        SearchBar()
    }
}