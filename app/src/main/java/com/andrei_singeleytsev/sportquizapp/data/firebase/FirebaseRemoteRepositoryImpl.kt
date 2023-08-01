package com.andrei_singeleytsev.sportquizapp.data.firebase

import android.os.Build
import android.util.Log
import com.andrei_singeleytsev.sportquizapp.R
import com.andrei_singeleytsev.sportquizapp.data.firebase.repository.FirebaseRemoteRepository
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import java.util.Locale
const val URL ="url"
const val START_QUIZ ="start_quiz"
class FirebaseRemoteRepositoryImpl() : FirebaseRemoteRepository {
    private var link: String? = null
    override suspend fun connectFB() {
        val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_default)
        val url = remoteConfig.getString(URL)
        Log.d("tag2", "start")
        if (url.isNotEmpty()) {
            Log.d("tag0", url)
            link = url
            return
        }
        if (isEmulator()) {
            link = START_QUIZ
            return
        }

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val receivedLink = remoteConfig.getString(URL)
                    Log.d("tag2", receivedLink)
                    link = receivedLink.ifEmpty {
                        START_QUIZ
                    }
                }
            }
    }

    override fun getFBLink(): String? {
        return link
    }

    private fun isEmulator(): Boolean {
        if (BuildConfig.DEBUG) return false
        val phoneModel = Build.MODEL
        val buildProduct = Build.PRODUCT
        val buildHardware = Build.HARDWARE
        val brand = Build.BRAND;
        var result = (Build.FINGERPRINT.startsWith("generic")
                || phoneModel.contains("google_sdk")
                || phoneModel.lowercase(Locale.getDefault()).contains("droid4x")
                || phoneModel.contains("Emulator")
                || phoneModel.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || buildHardware == "goldfish"
                || Build.BRAND.contains("google")
                || buildHardware == "vbox86"
                || buildProduct == "sdk"
                || buildProduct == "google_sdk"
                || buildProduct == "sdk_x86"
                || buildProduct == "vbox86p"
                || Build.BOARD.lowercase(Locale.getDefault()).contains("nox")
                || Build.BOOTLOADER.lowercase(Locale.getDefault()).contains("nox")
                || buildHardware.lowercase(Locale.getDefault()).contains("nox")
                || buildProduct.lowercase(Locale.getDefault()).contains("nox"))
        if (result) return true
        result = result or (Build.BRAND.startsWith("generic") &&
                Build.DEVICE.startsWith("generic"))
        if (result) return true
        result = result or ("google_sdk" == buildProduct)
        return result
    }
}