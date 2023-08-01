package com.andrei_singeleytsev.sportquizapp.presentation.screens.loading_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andrei_singeleytsev.sportquizapp.presentation.utils.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoadingScreen(screen: MutableState<String?>, navController: NavController) {

    if (screen.value!=null) {
        navController.navigate(screen.value!!){
            screen.value = null
            popUpTo(Routes.LOADING_SCREEN){
                inclusive = true
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp),
        )

    }
}





