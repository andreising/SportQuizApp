package com.andrei_singeleytsev.sportquizapp.presentation.utils

sealed class UIEvent{
    object PopBackStack: UIEvent()
    data class Navigate(val route: String): UIEvent()
    data class NavigateMain(val route: String): UIEvent()
    data class ShowSnackBar(val message: String): UIEvent()
}
