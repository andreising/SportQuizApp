package com.andrei_singeleytsev.sportquizapp.presentation.screens.main_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrei_singeleytsev.sportquizapp.data.room.repository.UserScoreRepository
import com.andrei_singeleytsev.sportquizapp.presentation.dialog.DialogController
import com.andrei_singeleytsev.sportquizapp.presentation.dialog.DialogEvent
import com.andrei_singeleytsev.sportquizapp.presentation.utils.Routes
import com.andrei_singeleytsev.sportquizapp.presentation.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: UserScoreRepository) : ViewModel(), DialogController {
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    override var dialogTitle = mutableStateOf("List Name")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    var showFloatingActionButton = mutableStateOf(false)
        private set

    fun onEvent(event: MainScreenEvent){
        when(event){
            is MainScreenEvent.OnShowEditDialog -> {

            }
            is MainScreenEvent.Navigate -> {
                showFloatingActionButton.value = event.route != Routes.QUIZ_SCREEN

                sendUIEvent(UIEvent.Navigate(event.route))
                }
            is MainScreenEvent.NavigateMain -> {
                sendUIEvent(UIEvent.NavigateMain(event.route))
                }
            is MainScreenEvent.OnAddNewNote -> {
                sendUIEvent(UIEvent.NavigateMain(Routes.NEW_NOTE_SCREEN + "/-1"))
            }
        }
    }

    override fun onDialogEvent(event: DialogEvent) {
        when(event){
            is DialogEvent.OnCancel -> {

            }
            is DialogEvent.OnConfirm -> {

            }
        }
    }
    private fun sendUIEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}