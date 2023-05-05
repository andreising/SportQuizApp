package com.andrei_singeleytsev.sportquizapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "user_score")
data class UserScore(
    @PrimaryKey
    val id: Int? = null,
    val score: Int,
    val time: String
)
