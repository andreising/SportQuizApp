package com.andrei_singeleytsev.sportquizapp.presentation.screens.notes.new_note_screen

sealed class NewNoteEvent{
    data class OnNameChange(val name:String): NewNoteEvent()
    data class OnTitleChange(val title:String): NewNoteEvent()
    object OnSave: NewNoteEvent()
}
