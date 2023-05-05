package com.andrei_singeleytsev.sportquizapp.presentation.dialog

sealed class DialogEvent{
    data class OnTextChange(val text:String): DialogEvent()
    object OnCancel:DialogEvent()
    object OnConfirm: DialogEvent()
}
