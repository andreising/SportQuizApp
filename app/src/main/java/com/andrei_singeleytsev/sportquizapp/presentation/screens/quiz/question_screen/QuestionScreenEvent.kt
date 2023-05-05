package com.andrei_singeleytsev.sportquizapp.presentation.screens.quiz.question_screen


sealed class QuestionScreenEvent{
    data class OnChooseAnswer(val id: Int): QuestionScreenEvent()
    object OnContinueGame: QuestionScreenEvent()
    object OnSaveQuestion: QuestionScreenEvent()
}
