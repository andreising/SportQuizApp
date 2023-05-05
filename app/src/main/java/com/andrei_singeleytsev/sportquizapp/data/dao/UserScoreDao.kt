package com.andrei_singeleytsev.sportquizapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.andrei_singeleytsev.sportquizapp.data.entities.UserScore
import kotlinx.coroutines.flow.Flow


@Dao
interface UserScoreDao {
    @Insert
    suspend fun addScore(item: UserScore)
    @Delete
    suspend fun deleteScore(item: UserScore)
    @Query ("SELECT * FROM user_score")
    fun getAllScores(): Flow<List<UserScore>>
}