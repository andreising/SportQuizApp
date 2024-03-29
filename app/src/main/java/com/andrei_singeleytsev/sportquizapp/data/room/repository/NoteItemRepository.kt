package com.andrei_singeleytsev.sportquizapp.data.room.repository

import com.andrei_singeleytsev.sportquizapp.data.room.entities.NoteItem
import kotlinx.coroutines.flow.Flow

interface NoteItemRepository {
    suspend fun insertItem(item: NoteItem)
    suspend fun deleteItem(item: NoteItem)
    fun getItems(): Flow<List<NoteItem>>
    suspend fun getNoteByID(id: Int): NoteItem
}