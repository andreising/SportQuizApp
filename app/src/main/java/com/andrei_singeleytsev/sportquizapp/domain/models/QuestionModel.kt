package com.andrei_singeleytsev.sportquizapp.domain.models

data class QuestionModel(
    val angle: Float,
    val category: String,
    val question: String,
    val answers: List<String>,
    val currentAnswer: String
)
