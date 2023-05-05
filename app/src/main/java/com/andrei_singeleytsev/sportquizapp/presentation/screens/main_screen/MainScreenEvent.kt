package com.andrei_singeleytsev.sportquizapp.presentation.screens.main_screen

sealed class MainScreenEvent{
    object OnShowEditDialog: MainScreenEvent()
    data class Navigate(val route: String): MainScreenEvent()
    data class NavigateMain(val route: String): MainScreenEvent()
    object OnAddNewNote: MainScreenEvent()
}
