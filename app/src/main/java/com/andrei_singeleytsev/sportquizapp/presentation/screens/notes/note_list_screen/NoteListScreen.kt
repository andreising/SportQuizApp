package com.andrei_singeleytsev.sportquizapp.presentation.screens.notes.note_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andrei_singeleytsev.sportquizapp.data.entities.NoteItem
import com.andrei_singeleytsev.sportquizapp.presentation.dialog.DialogController
import com.andrei_singeleytsev.sportquizapp.presentation.dialog.DialogEvent
import com.andrei_singeleytsev.sportquizapp.presentation.theme.GreenLight
import com.andrei_singeleytsev.sportquizapp.presentation.theme.GreenMain
import com.andrei_singeleytsev.sportquizapp.presentation.utils.UIEvent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteListScreen(
    paddingValues: PaddingValues,
    viewModel: NoteListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
){
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val notesList = viewModel.noteList.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect(){
            when(it) {
                is UIEvent.NavigateMain -> {
                    onNavigate(it.route)
                }
                is UIEvent.ShowSnackBar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = it.message,
                        actionLabel = "Undone",
                        duration = SnackbarDuration.Short
                    )
                    if (result == SnackbarResult.ActionPerformed){
                        viewModel.onEvent(NoteListEvent.UndoneDeleteItem)
                    }
                }
                else -> {

                }
            }
        }
    }
    Scaffold(
        modifier = Modifier.padding(paddingValues),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(
                    snackbarData = it, containerColor = GreenMain,
                    modifier = Modifier.padding(bottom = 100.dp)
                )
            }
        }
    ) {
        Content(paddingValues, notesList){
            viewModel.onEvent(it)
        }
    }



    NoteDialog(viewModel)
}

@Composable
fun NoteDialog(
    dialogController: DialogController
) {
    if (dialogController.openDialog.value) {
        AlertDialog(onDismissRequest = {
            dialogController.onDialogEvent(DialogEvent.OnCancel)
        },
        title = null,
        text = {
            Text(text = dialogController.dialogTitle.value,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            )
        },
        confirmButton = {
            TextButton(onClick = {
                dialogController.onDialogEvent(DialogEvent.OnConfirm)
            }) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                dialogController.onDialogEvent(DialogEvent.OnCancel)
            }) {
                Text(text = "Cancel")
            }
        })
    }
}

@Composable
fun Content(paddingValues: PaddingValues, list: State<List<NoteItem>>, deleteNote:(NoteListEvent)->Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenLight)
            .padding(paddingValues),
    ) {
        items(list.value){item ->
            NoteItem(item){noteListEvent ->
                deleteNote(noteListEvent)
            }
        }
    }
}
