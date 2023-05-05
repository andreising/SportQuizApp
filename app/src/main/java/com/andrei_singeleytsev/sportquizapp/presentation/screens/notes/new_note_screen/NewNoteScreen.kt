package com.andrei_singeleytsev.sportquizapp.presentation.screens.notes.new_note_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andrei_singeleytsev.sportquizapp.presentation.theme.GreenMain
import com.andrei_singeleytsev.sportquizapp.presentation.utils.UIEvent


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreen(
    viewModel: NewNoteViewModel = hiltViewModel(),
    onPopBackStack: ()-> Unit
){
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{
            when(it) {
                is UIEvent.PopBackStack -> {
                    onPopBackStack()
                }
                is UIEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        it.message
                    )
                }
                else -> {}
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        NewNoteScreenContent()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreenContent(
    viewModel: NewNoteViewModel = hiltViewModel()
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.LightGray)
    ) {
        Card(modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                )  {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = viewModel.name,
                        onValueChange = {name ->
                            viewModel.onEvent(NewNoteEvent.OnNameChange(name))
                        },
                        label = {
                            Text(
                                text = "Title..",
                                fontSize = 14.sp)
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = GreenMain,
                            containerColor = Color.White
                        ),
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                    IconButton(
                        modifier = Modifier.padding(top = 10.dp, end = 3.dp),
                        onClick = {
                            viewModel.onEvent(NewNoteEvent.OnSave)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "img_add",
                            tint = GreenMain
                        )
                    }
                }
                TextField(modifier = Modifier.fillMaxSize(), value = viewModel.title, onValueChange = {title->
                    viewModel.onEvent(NewNoteEvent.OnTitleChange(title))
                },
                    label = {
                        Text(
                            text = "Description..",
                            fontSize = 14.sp
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Black
                    )

                )
            }

        }
    }
}