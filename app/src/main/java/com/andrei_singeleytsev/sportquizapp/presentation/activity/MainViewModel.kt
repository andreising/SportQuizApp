package com.andrei_singeleytsev.sportquizapp.presentation.activity

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.CountDownTimer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrei_singeleytsev.sportquizapp.data.firebase.START_QUIZ
import com.andrei_singeleytsev.sportquizapp.data.firebase.repository.FirebaseRemoteRepository
import com.andrei_singeleytsev.sportquizapp.presentation.utils.Routes
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fbRemoteRepository: FirebaseRemoteRepository
):ViewModel() {
    private var link: String? = null
    val startScreen = mutableStateOf<String?>(null)
    fun start(application: Application){
        viewModelScope.launch{
            fbRemoteRepository.connectFB()
        }
        object : CountDownTimer(2000, 2000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                getLink(application)
            }

        }.start()
    }



    private fun openQuizScreen() {
        startScreen.value = Routes.MAIN_SCREEN
    }

    private fun openWebView() {
        startScreen.value = Routes.WEBVIEW_SCREEN
    }

    private fun isInternetConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false

            return networkInfo.isConnected
        }
    }

    private fun getLink(application: Application){
        var timer: CountDownTimer?=null

        timer = object : CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) {

                link = getLinkFromFB()
                if (link!=null) {
                    timer?.onFinish()
                    timer?.cancel()
                }
            }

            override fun onFinish() {
                checkCondition(application)
            }
        }.start()


    }

    private fun checkCondition(application: Application) {
        if (link != null) {
            if (isInternetConnected(application)){
                if (link == START_QUIZ) {
                    openQuizScreen()
                } else {
                    openWebView()
                }
            } else {
                openQuizScreen()
            }
        } else {
            openQuizScreen()
        }
    }

    private fun getLinkFromFB():String? {

        return fbRemoteRepository.getFBLink()
    }



}