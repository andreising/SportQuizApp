package com.andrei_singeleytsev.sportquizapp.presentation.screens.quiz.wheel_screen

import android.os.CountDownTimer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrei_singeleytsev.sportquizapp.domain.models.QuestionModel
import com.andrei_singeleytsev.sportquizapp.domain.repository.GameHelperProvider
import com.andrei_singeleytsev.sportquizapp.presentation.utils.Routes
import com.andrei_singeleytsev.sportquizapp.presentation.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizScreenViewModel @Inject constructor(
    private val provider: GameHelperProvider
): ViewModel() {
    var isGameOn = mutableStateOf(false)
        private set
    var title = mutableStateOf("Let's start!")
        private set
    var buttonTitle = mutableStateOf("Start the game")
    var buttonClickable = mutableStateOf(true)
    var progress = mutableStateOf(0)
        private set
    var currentScore = mutableStateOf(0)
        private set


    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private fun sendUIEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    var angle = mutableStateOf(0f)

    private var currentQuestionModel = QuestionModel(
        0f,
        "",
        "",
        emptyList(),
        ""
    )
    fun onEvent(event: QuizScreenEvent){
        when(event) {
            is QuizScreenEvent.OnButtonPressed -> {
                if (isGameOn.value) {
                    nextStep()
                } else {
                    gameStart()
                }
            }
        }
    }

    private fun nextStep() {
        viewModelScope.launch {
            currentQuestionModel = provider.getQuestionModel()
        }
        buttonClickable.value = false
        angle.value+=currentQuestionModel.angle
        object : CountDownTimer(5000, 2500){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                sendUIEvent(UIEvent.NavigateMain(Routes.QUESTION_SCREEN))
                progress.value++
                buttonClickable.value = true
                angle.value = 0f
                if (progress.value==10) endGame()
                }
        }.start()



    }

    private fun gameStart(){
        isGameOn.value = true
        buttonTitle.value = "Roll!"
        progress.value = 0
        currentScore = provider.getProgress()
        provider.refreshProgress()
        viewModelScope.launch{
            provider.setQuestionModel()
            currentQuestionModel = provider.getQuestionModel()
        }
    }

    private fun endGame() {
        isGameOn.value = false
        buttonTitle.value = "Play Again"
        title.value = "Congratilations! Your score is ${progress.value}"
    }

}