package com.andrei_singeleytsev.sportquizapp.presentation.menu

import com.andrei_singeleytsev.sportquizapp.R
import com.andrei_singeleytsev.sportquizapp.presentation.utils.Routes

sealed class BottomNavigationItem(val title: String, val icon_id: Int, val route: String) {
    object QuizItem: BottomNavigationItem("Quiz", R.drawable.game_icon, Routes.QUIZ_SCREEN)
    object NoteListItem: BottomNavigationItem ("Notes", R.drawable.note_list_icon, Routes.NOTE_LIST_SCREEN)
}
