package com.andrei_singeleytsev.sportquizapp.presentation.screens.quiz.question_screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrei_singeleytsev.sportquizapp.data.room.entities.NoteItem
import com.andrei_singeleytsev.sportquizapp.data.room.repository.NoteItemRepository
import com.andrei_singeleytsev.sportquizapp.domain.models.QuestionModel
import com.andrei_singeleytsev.sportquizapp.domain.repository.GameHelperProvider
import com.andrei_singeleytsev.sportquizapp.presentation.utils.UIEvent
import com.andrei_singeleytsev.sportquizapp.presentation.utils.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionScreenViewModel @Inject constructor(
    private val provider: GameHelperProvider,
    private val repository: NoteItemRepository
) : ViewModel() {
    val isAddButtonEnabled = mutableStateOf(false)
    val isButtonEnabled = mutableStateOf(false)
    val question = mutableStateOf("")
    val answer0 = mutableStateOf("")
    val answer1 = mutableStateOf("")
    val answer2 = mutableStateOf("")
    val answer3 = mutableStateOf("")
    val colors = listOf(
        mutableStateOf(Color.Blue),
        mutableStateOf(Color.Blue),
        mutableStateOf(Color.Blue),
        mutableStateOf(Color.Blue)
    )
    private var shuffledList = mutableListOf<String>()
    private var currentAnswer = ""
    private var currentButton = 0
    private var questionModel = QuestionModel(
        0f,
        "",
        "",
        emptyList(),
        ""
    )

    init {
        viewModelScope.launch {
            questionModel = provider.getQuestionModel()
        }
        question.value = questionModel.question
        shuffledList = questionModel.answers.toMutableList()
        shuffledList.shuffle()
        answer0.value = shuffledList[0]
        answer1.value = shuffledList[1]
        answer2.value = shuffledList[2]
        answer3.value = shuffledList[3]
        currentAnswer = questionModel.currentAnswer
        currentButton = shuffledList.indexOf(currentAnswer)
    }

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private fun sendUIEvent(event: UIEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun onEvent(event: QuestionScreenEvent) {
        when (event) {
            is QuestionScreenEvent.OnSaveQuestion -> {
                viewModelScope.launch {
                    repository.insertItem(
                        NoteItem(null, question.value, currentAnswer, getCurrentTime())
                    )
                }
                isAddButtonEnabled.value = false
            }

            is QuestionScreenEvent.OnChooseAnswer -> {
                if (currentAnswer == shuffledList[event.id]) {
                    colors[event.id].value = Color.Green
                    provider.progressPlus()
                } else {
                    colors[event.id].value = Color.Red
                    colors[currentButton].value = Color.Green
                }
                isAddButtonEnabled.value = true
                isButtonEnabled.value = true
            }

            is QuestionScreenEvent.OnContinueGame -> {
                viewModelScope.launch {
                    provider.setQuestionModel()
                }
                sendUIEvent(UIEvent.PopBackStack)
            }
        }
    }
}