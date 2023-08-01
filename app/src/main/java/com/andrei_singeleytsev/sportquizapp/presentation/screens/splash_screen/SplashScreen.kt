package com.andrei_singeleytsev.sportquizapp.presentation.screens.splash_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.andrei_singeleytsev.sportquizapp.R
import com.andrei_singeleytsev.sportquizapp.presentation.utils.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    Log.d("tag", "checkThis")
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "img_splash")
    }

    LaunchedEffect(true) {
        delay(3000)
        navController.navigate(Routes.LOADING_SCREEN){
            popUpTo(Routes.SPLASH_SCREEN){
                inclusive = true
            }
        }
    }
}