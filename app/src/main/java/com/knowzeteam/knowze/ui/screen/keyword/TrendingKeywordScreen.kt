package com.knowzeteam.knowze.ui.screen.keyword

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendingKeywordScreen(
    onBack: () -> Unit,
    viewModelFactory: ViewModelFactory = ViewModelFactory(LocalContext.current)
) {
    val keywordViewModel: KeywordViewModel = viewModel(factory = viewModelFactory)
    // Observe the ViewModel states
    // Directly use the value property
    val isLoading = keywordViewModel.isLoading.value
    val keywords = keywordViewModel.keywords.value

    LaunchedEffect(key1 = true) {
        keywordViewModel.getKeywords()
    }

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
                        text = stringResource(id = R.string.trending_keyword),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Localized description",
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                KeywordList(keywords)
            }
        }
    }
}

@Composable
fun KeywordList(keywords: List<String>) {
    if (keywords.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No keywords available",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } else {
        Column {
            keywords.forEach { keyword ->
                KeywordItem(keyword)
                Divider()
            }
        }
    }
}


@Composable
fun KeywordItem(keyword: String) {
    Text(
        text = keyword,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        style = MaterialTheme.typography.bodyLarge
    )
}


//@Preview(showBackground = true)
//@Composable
//fun TrendingKeywordPreview() {
//    KnowzeTheme {
//        TrendingKeywordScreen(
//            keywordRepository = ,
//            idToken = ""
//        )
//    }
//}