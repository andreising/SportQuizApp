package com.andrei_singeleytsev.sportquizapp.presentation.dialog

sealed class DialogEvent{
    object OnCancel:DialogEvent()
    object OnConfirm: DialogEvent()
}
