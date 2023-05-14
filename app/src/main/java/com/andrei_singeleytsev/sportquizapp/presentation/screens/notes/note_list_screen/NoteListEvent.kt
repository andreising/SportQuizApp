package com.andrei_singeleytsev.sportquizapp.presentation.screens.notes.note_list_screen

import com.andrei_singeleytsev.sportquizapp.data.room.entities.NoteItem

sealed class NoteListEvent{
    data class OnShowDeleteDialog(val item: NoteItem): NoteListEvent()
    data class OnItemClick(val route: String): NoteListEvent()
    object UndoneDeleteItem: NoteListEvent()
}
