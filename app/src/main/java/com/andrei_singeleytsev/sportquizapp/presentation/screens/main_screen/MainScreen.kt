package com.andrei_singeleytsev.sportquizapp.presentation.screens.main_screen

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andrei_singeleytsev.sportquizapp.R
import com.andrei_singeleytsev.sportquizapp.presentation.menu.BottomNavigationMenu
import com.andrei_singeleytsev.sportquizapp.presentation.navigation.NavigationGraph
import com.andrei_singeleytsev.sportquizapp.presentation.utils.UIEvent
import com.andrei_singeleytsev.sportquizapp.presentation.theme.GreenMain


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mainNavController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when(uiEvent) {
                is UIEvent.NavigateMain ->{
                    mainNavController.navigate(uiEvent.route)
                }
                is UIEvent.Navigate -> {
                    navController.navigate(uiEvent.route)
                }
                else ->{}
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationMenu(currentRoute){route ->
                viewModel.onEvent(MainScreenEvent.Navigate(route))

            }
        }, floatingActionButton = {
            if (viewModel.showFloatingActionButton.value){
                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(MainScreenEvent.OnAddNewNote)
                    },
                    shape = CircleShape,
                    containerColor = GreenMain
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_icon),
                        contentDescription = "add",
                        tint = Color.White
                    )
                }
            }

        }
    ) {
        NavigationGraph(navController = navController, it){route->
            viewModel.onEvent(MainScreenEvent.NavigateMain(route))
        }
    }
}