package com.andrei_singeleytsev.sportquizapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "note_item")
data class NoteItem(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val title: String,
    val time: String
)
