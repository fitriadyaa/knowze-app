package com.knowzeteam.knowze.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.component.MenuItem
import com.knowzeteam.knowze.ui.component.MiniMenuItem
import com.knowzeteam.knowze.ui.component.SearchBar
import com.knowzeteam.knowze.ui.screen.auth.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.knowzeteam.knowze.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val showLogoutDialog = remember { mutableStateOf(false) }
    val isLoggedOut by viewModel.isLoggedOut.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (showLogoutDialog.value) {
        LogoutDialog(showLogoutDialog, onConfirmLogout = { viewModel.logout() })
    }

    LaunchedEffect(isLoggedOut) {
        if (isLoggedOut) {
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Home.route) { inclusive = true }
            }
        }
    }

    Box {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerContent(showLogoutDialog)
            }
        ) {
            HomeContent(drawerState = drawerState)
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun DrawerContent(showLogoutDialog: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight()
            .width(260.dp)
    ) {
        Column {
            DrawerHeader()
            DrawerItem("Profile", Icons.Default.Person)
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = { showLogoutDialog.value = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Log Out")
            }
        }
    }
}

@Composable
fun DrawerHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Profile",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Fitria Widyani", style = MaterialTheme.typography.titleMedium)
        Text("email@example.com", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun DrawerItem(text: String, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(icon, contentDescription = null)
        Spacer(Modifier.width(16.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    drawerState: DrawerState,
    modifier: Modifier = Modifier
){

    // State to track if the drawer should open
    var openDrawer by remember { mutableStateOf(false) }

    // When openDrawer changes to true, open the drawer
    LaunchedEffect(openDrawer) {
        if (openDrawer && drawerState.isClosed) {
            drawerState.open()
            openDrawer = false // Reset the state
        }
    }


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
                IconButton(
                    onClick = {
                        openDrawer = true
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_menu),
                        contentDescription = "image description",
                        contentScale = ContentScale.None,
                    )
                }
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
                    Divider(
                        modifier = Modifier
                            .height(4.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
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
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(
                        modifier = Modifier
                            .height(4.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(
                        text = stringResource(id = R.string.title_menu_mini),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF3334CC)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    MiniMenuItem(
                        text = stringResource(id = R.string.menu_1),
                        boxColor = Color(0xFF3334CC),
                        onClick = { /*TODO*/ }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    MiniMenuItem(
                        text = stringResource(id = R.string.menu_2),
                        boxColor = Color(0xFF3334CC),
                        onClick = { /*TODO*/ }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    MiniMenuItem(
                        text = stringResource(id = R.string.menu_3),
                        boxColor = Color(0xFF3334CC),
                        onClick = { /*TODO*/ }
                    )
                }
            }
        }
    }
}

@Composable
fun LogoutDialog(showLogoutDialog: MutableState<Boolean>, onConfirmLogout: () -> Unit) {
    AlertDialog(
        onDismissRequest = { showLogoutDialog.value = false },
        title = { Text("Konfirmasi Keluar") },
        text = { Text("Apakah kamu yakin ingin keluar?") },
        confirmButton = {
            Button(onClick = {
                showLogoutDialog.value = false
                onConfirmLogout()
            }) {
                Text("Keluar")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = { showLogoutDialog.value = false }) {
                Text("Tidak")
            }
        }
    )
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

//
//@Preview(showBackground = true, device = Devices.PIXEL_4)
//@Composable
//fun HomeScreenPreview(){
//    KnowzeTheme {
//        HomeScreen(
//
//        )
//    }
//}