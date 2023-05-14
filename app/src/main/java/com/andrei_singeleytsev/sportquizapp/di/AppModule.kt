package com.andrei_singeleytsev.sportquizapp.di

import android.app.Application

import androidx.room.Room
import com.andrei_singeleytsev.sportquizapp.data.firebase.FirebaseRemoteRepositoryImpl
import com.andrei_singeleytsev.sportquizapp.data.firebase.repository.FirebaseRemoteRepository
import com.andrei_singeleytsev.sportquizapp.data.room.MainDataBase
import com.andrei_singeleytsev.sportquizapp.data.room.repository.NoteItemRepository
import com.andrei_singeleytsev.sportquizapp.data.room.repository.UserScoreRepository
import com.andrei_singeleytsev.sportquizapp.data.room.repository.implementations.NoteItemRepositoryImpl
import com.andrei_singeleytsev.sportquizapp.data.room.repository.implementations.UserScoreRepositoryImpl
import com.andrei_singeleytsev.sportquizapp.domain.business.GameHelperImpl
import com.andrei_singeleytsev.sportquizapp.domain.repository.GameHelperProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMainDatabase(app: Application): MainDataBase {
        return Room.databaseBuilder(
            app,
            MainDataBase::class.java,
            "sport_quiz_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteItemRepository(db: MainDataBase): NoteItemRepository {
        return NoteItemRepositoryImpl(db.noteItemDao)
    }

    @Provides
    @Singleton
    fun provideUserScoreRepository(db: MainDataBase): UserScoreRepository {
        return UserScoreRepositoryImpl(db.userScoreDao)
    }

    @Provides
    @Singleton
    fun provideFBRemoteConfig():FirebaseRemoteRepository{
        return FirebaseRemoteRepositoryImpl()
    }
    @Provides
    @Singleton
    fun provideGameHelper(): GameHelperProvider {
        return GameHelperImpl()
    }
}