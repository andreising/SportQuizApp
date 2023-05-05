package com.andrei_singeleytsev.sportquizapp.presentation.screens.notes.new_note_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrei_singeleytsev.sportquizapp.data.entities.NoteItem
import com.andrei_singeleytsev.sportquizapp.data.repository.NoteItemRepository
import com.andrei_singeleytsev.sportquizapp.presentation.utils.UIEvent
import com.andrei_singeleytsev.sportquizapp.presentation.utils.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val repository: NoteItemRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var noteID = -1
    private var noteItem: NoteItem? = null

    var name by mutableStateOf("")
        private set

    var title by mutableStateOf("")
        private set

    init {
        noteID = savedStateHandle.get<String>("note_id")?.toInt() ?: -1
        if (noteID!=-1){
            viewModelScope.launch {
                repository.getNoteByID(noteID).let {note_item->
                    name = note_item.name
                    title = note_item.title
                    this@NewNoteViewModel.noteItem = note_item
                }
            }
        }
    }

    fun onEvent(event: NewNoteEvent){
        when(event) {
            is NewNoteEvent.OnSave -> {
                viewModelScope.launch {
                    if (name.isEmpty()||title.isEmpty()){
                        sendUIEvent(UIEvent.ShowSnackBar("Not all fields are filled"))
                        return@launch
                    }
                    repository.insertItem(
                        NoteItem(
                            noteItem?.id,
                            name,
                            title,
                            getCurrentTime()
                        )
                    )
                    sendUIEvent(UIEvent.PopBackStack)
                }

            }
            is NewNoteEvent.OnNameChange -> {
                name = event.name
            }
            is NewNoteEvent.OnTitleChange -> {
                title = event.title
            }
        }

    }

    private fun sendUIEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}