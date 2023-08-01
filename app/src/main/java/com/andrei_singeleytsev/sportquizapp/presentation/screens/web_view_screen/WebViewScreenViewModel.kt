package com.andrei_singeleytsev.sportquizapp.presentation.screens.web_view_screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore
import android.util.Log
import android.webkit.CookieManager
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrei_singeleytsev.sportquizapp.data.firebase.repository.FirebaseRemoteRepository
import com.andrei_singeleytsev.sportquizapp.presentation.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class WebViewScreenViewModel @Inject constructor(
    private val repository: FirebaseRemoteRepository
):ViewModel() {
    val cookieManager: CookieManager = CookieManager.getInstance()
    @SuppressLint("StaticFieldLeak")
    var webView: WebView? = null
    private var currentUrl = repository.getFBLink()!!
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val webChrome = mutableStateOf<WebChromeClient?>(null)


    @SuppressLint("SetJavaScriptEnabled")
    fun getWebView(context: Context): WebView{
        return webView ?: WebView(context).apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                databaseEnabled = true
                setSupportZoom(true)
                allowFileAccess = true
                allowContentAccess = true
            }

            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    cookieManager.flush()
                }
            }
            webChromeClient = webChrome.value
            loadUrl(currentUrl)
            webView = this
        }
    }

    fun onBackManager(activity: Activity?){
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            activity?.finish()
        }
    }





}