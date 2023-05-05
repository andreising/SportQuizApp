package com.andrei_singeleytsev.sportquizapp.data.repository

import com.andrei_singeleytsev.sportquizapp.data.entities.UserScore
import kotlinx.coroutines.flow.Flow

interface UserScoreRepository {
    suspend fun addScore(item: UserScore)
    suspend fun deleteScore(item: UserScore)
    fun getAllScores(): Flow<List<UserScore>>
}