package com.andrei_singeleytsev.sportquizapp.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andrei_singeleytsev.sportquizapp.presentation.activity.MainActivity
import com.andrei_singeleytsev.sportquizapp.presentation.screens.main_screen.MainScreen
import com.andrei_singeleytsev.sportquizapp.presentation.screens.notes.new_note_screen.NewNoteScreen
import com.andrei_singeleytsev.sportquizapp.presentation.screens.quiz.question_screen.QuestionScreen
import com.andrei_singeleytsev.sportquizapp.presentation.screens.loading_screen.LoadingScreen
import com.andrei_singeleytsev.sportquizapp.presentation.screens.splash_screen.SplashScreen
import com.andrei_singeleytsev.sportquizapp.presentation.screens.web_view_screen.WebViewScreen
import com.andrei_singeleytsev.sportquizapp.presentation.utils.Routes


@Composable
fun MainNavigationGraph(
    startScreen: MutableState<String?>, chromeClient: MainActivity.ChromeClient
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SPLASH_SCREEN) {

        composable(Routes.SPLASH_SCREEN){
            SplashScreen(navController = navController)
        }
        composable(Routes.LOADING_SCREEN){
            LoadingScreen(startScreen, navController)
        }
        composable(Routes.WEBVIEW_SCREEN) {
            WebViewScreen(chromeClient)
        }
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
