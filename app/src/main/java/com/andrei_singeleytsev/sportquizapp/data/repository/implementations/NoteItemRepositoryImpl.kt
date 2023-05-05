package com.andrei_singeleytsev.sportquizapp.data.repository.implementations

import com.andrei_singeleytsev.sportquizapp.data.dao.NoteItemDao
import com.andrei_singeleytsev.sportquizapp.data.entities.NoteItem
import com.andrei_singeleytsev.sportquizapp.data.repository.NoteItemRepository
import kotlinx.coroutines.flow.Flow

class NoteItemRepositoryImpl(
    private val dao: NoteItemDao
) : NoteItemRepository {
    override suspend fun insertItem(item: NoteItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: NoteItem) {
        dao.deleteItem(item)
    }

    override fun getItems(): Flow<List<NoteItem>> {
        return dao.getItems()
    }

    override suspend fun getNoteByID(id: Int): NoteItem {
        return dao.getNoteByID(id)
    }
}