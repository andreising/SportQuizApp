package com.andrei_singeleytsev.sportquizapp.domain.repository

import android.content.Context
import androidx.compose.runtime.MutableState
import com.andrei_singeleytsev.sportquizapp.domain.models.QuestionModel

interface GameHelperProvider {
    suspend fun setQuestionModel()
    suspend fun getQuestionModel(): QuestionModel
    fun getProgress(): MutableState<Int>
    fun refreshProgress()
    fun progressPlus()
}