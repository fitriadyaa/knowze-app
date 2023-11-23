package com.knowzeteam.knowze.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.component.BigMenuItem
import com.knowzeteam.knowze.ui.component.MenuItem
import com.knowzeteam.knowze.ui.component.SearchBar
import com.knowzeteam.knowze.ui.theme.KnowzeTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "image description",
                    contentScale = ContentScale.None,
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = stringResource(id = R.string.selamat) + " Fitria",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }
            SearchBar()
            Spacer(modifier = Modifier.height(16.dp))
            SuggestionBox(text = "Cara makan rumput")
            Spacer(modifier = Modifier.height(16.dp))
        }
        // Container
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                )
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.height(520.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    MenuItem(
                        text = stringResource(id = R.string.menu_rekomendasi),
                        imageResId = R.drawable.ic_cari_rekomendasi,
                        boxColor = Color(0xFF43936C),
                        onClick = {}
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    MenuItem(
                        text = stringResource(id = R.string.menu_keyword),
                        subText = stringResource(id = R.string.menu_keyword_detail),
                        imageResId = R.drawable.ic_trending_keyword,
                        boxColor = MaterialTheme.colorScheme.primary,
                        onClick = {}
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    MenuItem(
                        text = stringResource(id = R.string.menu_galeri),
                        subText = stringResource(id = R.string.menu_galeri_detail),
                        imageResId = R.drawable.ic_gallery,
                        boxColor = MaterialTheme.colorScheme.primary,
                        onClick = {}
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    BigMenuItem(
                        text = stringResource(id = R.string.menu_selamat),
                        subtext = stringResource(id = R.string.menu_selamat_detail),
                        boxColor = MaterialTheme.colorScheme.primary,
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Composable
fun SuggestionBox(
    text: String,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = stringResource(id = R.string.coba_ini),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = modifier
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
                .padding(16.dp)
                .height(20.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview(){
    KnowzeTheme {
       HomeScreen()
    }
}