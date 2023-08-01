package com.andrei_singeleytsev.sportquizapp.data.firebase.repository

import com.google.firebase.remoteconfig.FirebaseRemoteConfig

interface FirebaseRemoteRepository {
    suspend fun connectFB()
    fun getFBLink(): String?
}