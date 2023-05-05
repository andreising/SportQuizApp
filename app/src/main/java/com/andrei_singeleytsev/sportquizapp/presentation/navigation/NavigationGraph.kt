package com.andrei_singeleytsev.sportquizapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andrei_singeleytsev.sportquizapp.presentation.screens.notes.note_list_screen.NoteListScreen
import com.andrei_singeleytsev.sportquizapp.presentation.screens.quiz.wheel_screen.QuizScreen
import com.andrei_singeleytsev.sportquizapp.presentation.utils.Routes

@Composable
fun NavigationGraph(navController: NavHostController, padding: PaddingValues, onNavigate: (String)->Unit){

    NavHost(navController = navController, startDestination = Routes.QUIZ_SCREEN){
        composable(Routes.QUIZ_SCREEN){
            QuizScreen(padding){
                onNavigate(it)
            }
        }
        composable(Routes.NOTE_LIST_SCREEN){
            NoteListScreen(padding){
                onNavigate(it)
            }
        }
    }
}