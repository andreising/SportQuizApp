package com.andrei_singeleytsev.sportquizapp.data.room.repository

import com.andrei_singeleytsev.sportquizapp.data.room.entities.UserScore
import kotlinx.coroutines.flow.Flow

interface UserScoreRepository {
    suspend fun addScore(item: UserScore)
    suspend fun deleteScore(item: UserScore)
    fun getAllScores(): Flow<List<UserScore>>
}