package com.knowzeteam.knowze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.FirebaseApp
import com.knowzeteam.knowze.data.remote.retrofit.ApiConfig
import com.knowzeteam.knowze.data.remote.retrofit.ApiService
import com.knowzeteam.knowze.ui.ViewModelFactory
import com.knowzeteam.knowze.ui.theme.KnowzeTheme

class MainActivity : ComponentActivity() {
    private lateinit var apiService: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        apiService = ApiConfig.getApiService(this)
        FirebaseApp.initializeApp(this)
        installSplashScreen()

        val viewModelFactory = ViewModelFactory(this)

        setContent {
            KnowzeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KnowzeApp(viewModelFactory)
                }
            }
        }
    }
}
