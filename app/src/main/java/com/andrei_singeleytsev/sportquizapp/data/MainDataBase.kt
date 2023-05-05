package com.andrei_singeleytsev.sportquizapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andrei_singeleytsev.sportquizapp.data.dao.NoteItemDao
import com.andrei_singeleytsev.sportquizapp.data.dao.UserScoreDao

import com.andrei_singeleytsev.sportquizapp.data.entities.NoteItem
import com.andrei_singeleytsev.sportquizapp.data.entities.UserScore

@Database(
    entities = [NoteItem::class, UserScore::class],
    version = 1
)
abstract class MainDataBase: RoomDatabase() {
    abstract val noteItemDao: NoteItemDao
    abstract val userScoreDao: UserScoreDao
}