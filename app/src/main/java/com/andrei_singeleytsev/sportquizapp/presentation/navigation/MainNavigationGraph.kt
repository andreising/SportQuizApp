package com.andrei_singeleytsev.sportquizapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andrei_singeleytsev.sportquizapp.presentation.screens.main_screen.MainScreen
import com.andrei_singeleytsev.sportquizapp.presentation.screens.notes.new_note_screen.NewNoteScreen
import com.andrei_singeleytsev.sportquizapp.presentation.screens.quiz.question_screen.QuestionScreen
import com.andrei_singeleytsev.sportquizapp.presentation.utils.Routes

@Composable
fun MainNavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN) {
        composable(Routes.MAIN_SCREEN) {
            MainScreen(navController)
        }
        composable(Routes.NEW_NOTE_SCREEN + "/{note_id}"){
            NewNoteScreen{
                navController.popBackStack()
            }
        }
        composable(Routes.QUESTION_SCREEN){
            QuestionScreen{
                navController.popBackStack()
            }
        }
    }
}