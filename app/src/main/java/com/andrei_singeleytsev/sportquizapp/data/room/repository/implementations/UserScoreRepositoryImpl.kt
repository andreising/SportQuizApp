package com.andrei_singeleytsev.sportquizapp.data.room.repository.implementations

import com.andrei_singeleytsev.sportquizapp.data.room.dao.UserScoreDao
import com.andrei_singeleytsev.sportquizapp.data.room.entities.UserScore
import com.andrei_singeleytsev.sportquizapp.data.room.repository.UserScoreRepository
import kotlinx.coroutines.flow.Flow

class UserScoreRepositoryImpl(
    private val dao: UserScoreDao
) : UserScoreRepository {
    override suspend fun addScore(item: UserScore) {
        dao.addScore(item)
    }

    override suspend fun deleteScore(item: UserScore) {
        dao.deleteScore(item)
    }

    override fun getAllScores(): Flow<List<UserScore>> {
        return dao.getAllScores()
    }

}