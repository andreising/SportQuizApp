package com.andrei_singeleytsev.sportquizapp.presentation.screens.web_view_screen

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.andrei_singeleytsev.sportquizapp.presentation.screens.notes.note_list_screen.NoteListEvent
import com.andrei_singeleytsev.sportquizapp.presentation.utils.UIEvent


@Composable
fun WebViewScreen(
    chromeClient: WebChromeClient,
    viewModel: WebViewScreenViewModel = hiltViewModel()
) {
    val act = (LocalContext.current as? Activity)
    viewModel.webChrome.value = chromeClient
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            viewModel.getWebView(it)
        }
    )

    BackHandler(onBack = {
        viewModel.onBackManager(act)
    })

}