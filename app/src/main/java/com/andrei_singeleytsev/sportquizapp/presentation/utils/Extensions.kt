package com.andrei_singeleytsev.sportquizapp.presentation.utils

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getCurrentTime(): String{
    val formatter = SimpleDateFormat("dd.MM.yy HH:mm", Locale.getDefault())
    return formatter.format(Calendar.getInstance().time)
}