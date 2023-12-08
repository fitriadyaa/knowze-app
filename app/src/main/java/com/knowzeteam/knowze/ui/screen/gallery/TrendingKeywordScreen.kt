package com.knowzeteam.knowze.ui.screen.gallery

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.component.TrendingKeywordItem
import com.knowzeteam.knowze.ui.theme.KnowzeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendingKeywordScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    androidx.compose.material3.Text(
                        text = stringResource(id = R.string.menu_galeri),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = { /* do something */ }) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 100.dp)
        ) {
            Text(
                text = stringResource(id = R.string.trending_keyword),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                LazyColumn {
                    item {
                        TrendingKeywordItem(
                            trendKeyword = stringResource(id = R.string.trending_keyword_1),
                            onClick = { /*TODO*/ }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                        TrendingKeywordItem(
                            trendKeyword = stringResource(id = R.string.trending_keyword_2),
                            onClick = { /*TODO*/ }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                        TrendingKeywordItem(
                            trendKeyword = stringResource(id = R.string.trending_keyword_3),
                            onClick = { /*TODO*/ }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                        TrendingKeywordItem(
                            trendKeyword = stringResource(id = R.string.trending_keyword_4),
                            onClick = { /*TODO*/ }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                        TrendingKeywordItem(
                            trendKeyword = stringResource(id = R.string.trending_keyword_5),
                            onClick = { /*TODO*/ }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                        TrendingKeywordItem(
                            trendKeyword = stringResource(id = R.string.trending_keyword_6),
                            onClick = { /*TODO*/ }
                        )
                    }

                }

                Spacer(modifier = Modifier.width(35.dp))

                LazyColumn {
                    item {
                        TrendingKeywordItem(
                            trendKeyword = stringResource(id = R.string.trending_keyword_7),
                            onClick = { /*TODO*/ }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                        TrendingKeywordItem(
                            trendKeyword = stringResource(id = R.string.trending_keyword_8),
                            onClick = { /*TODO*/ }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                        TrendingKeywordItem(
                            trendKeyword = stringResource(id = R.string.trending_keyword_9),
                            onClick = { /*TODO*/ }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                        TrendingKeywordItem(
                            trendKeyword = stringResource(id = R.string.trending_keyword_10),
                            onClick = { /*TODO*/ }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                        TrendingKeywordItem(
                            trendKeyword = stringResource(id = R.string.trending_keyword_11),
                            onClick = { /*TODO*/ }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                        TrendingKeywordItem(
                            trendKeyword = stringResource(id = R.string.trending_keyword_12),
                            onClick = { /*TODO*/ }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrendingKeywordPreview() {
    KnowzeTheme {
        TrendingKeywordScreen()
    }
}