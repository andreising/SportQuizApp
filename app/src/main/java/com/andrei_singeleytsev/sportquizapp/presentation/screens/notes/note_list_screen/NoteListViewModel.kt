package com.andrei_singeleytsev.sportquizapp.presentation.screens.notes.note_list_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrei_singeleytsev.sportquizapp.data.entities.NoteItem
import com.andrei_singeleytsev.sportquizapp.data.repository.NoteItemRepository
import com.andrei_singeleytsev.sportquizapp.presentation.dialog.DialogController
import com.andrei_singeleytsev.sportquizapp.presentation.dialog.DialogEvent
import com.andrei_singeleytsev.sportquizapp.presentation.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val repository: NoteItemRepository
): ViewModel(), DialogController {

    val noteList = repository.getItems()
    private var noteItem: NoteItem? = null

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    override var dialogTitle = mutableStateOf("Delete this note?")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override fun onDialogEvent(event: DialogEvent) {
        when(event){
            DialogEvent.OnCancel ->{
                openDialog.value = false
            }
            DialogEvent.OnConfirm->{
                viewModelScope.launch {
                    repository.deleteItem(noteItem!!)
                }
                openDialog.value = false
                sendUIEvent(UIEvent.ShowSnackBar("Undone delete item?"))
            }
            else -> {

            }
        }
    }
    private fun sendUIEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun onEvent(event: NoteListEvent){
        when(event) {
            is NoteListEvent.OnShowDeleteDialog ->{
                openDialog.value = true
                noteItem = event.item
            }
            is NoteListEvent.OnItemClick -> {
                sendUIEvent(UIEvent.NavigateMain(event.route))
            }
            is NoteListEvent.UndoneDeleteItem -> {
                viewModelScope.launch {
                    repository.insertItem(noteItem!!)
                }
            }
        }

    }

}