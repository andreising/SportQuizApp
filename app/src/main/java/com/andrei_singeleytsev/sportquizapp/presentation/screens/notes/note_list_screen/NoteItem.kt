package com.andrei_singeleytsev.sportquizapp.presentation.screens.notes.note_list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andrei_singeleytsev.sportquizapp.data.entities.NoteItem
import com.andrei_singeleytsev.sportquizapp.presentation.theme.GreenMain
import com.andrei_singeleytsev.sportquizapp.presentation.utils.Routes

@Composable
fun NoteItem(
    noteItem: NoteItem,
    onEvent: (NoteListEvent)-> Unit
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = 3.dp,
            top = 3.dp,
            end = 3.dp
        )
        .clickable {
            onEvent(NoteListEvent.OnItemClick(
                Routes.NEW_NOTE_SCREEN + "/${noteItem.id}"
            ))
        }
    )  {
        Column(Modifier.fillMaxWidth()) {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            start = 10.dp
                        )
                        .weight(1f), text = noteItem.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(
                        top = 10.dp,
                        start = 10.dp,
                        end = 10.dp
                    ), text = noteItem.time,
                    color = Color.Black
                )
            }
            Row(Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 10.dp, start = 10.dp, bottom = 10.dp
                        )
                        .weight(1f), text = noteItem.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray
                )
                IconButton(onClick = {
                    onEvent(NoteListEvent.OnShowDeleteDialog(noteItem))
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "img_dlt",
                        tint = GreenMain
                    )
                }
            }
        }

    }
}