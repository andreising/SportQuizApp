package com.andrei_singeleytsev.sportquizapp.presentation.dialog

import androidx.compose.runtime.MutableState

interface DialogController {
    val dialogTitle: MutableState<String>
    val openDialog: MutableState<Boolean>
    fun onDialogEvent(event: DialogEvent)
}