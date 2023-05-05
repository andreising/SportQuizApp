package com.andrei_singeleytsev.sportquizapp.presentation.screens.quiz.question_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andrei_singeleytsev.sportquizapp.presentation.utils.UIEvent


@Composable
fun QuestionScreen(
    viewModel: QuestionScreenViewModel = hiltViewModel(),
    onNavigate: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is UIEvent.PopBackStack -> {
                    onNavigate()
                }

                else -> {}
            }
        }
    }
    if (viewModel.isButtonEnabled.value) {
        Button(
            onClick = {
                viewModel.onEvent(QuestionScreenEvent.OnSaveQuestion)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
        ) {
            Text(text = "Add to the notes")
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Question:",
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(top = 30.dp),
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            ),
            textAlign = TextAlign.Center
        )
        Text(
            text = viewModel.question.value,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(top = 30.dp),
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            textAlign = TextAlign.Center
        )
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(end = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    TextButton(
                        modifier = Modifier
                            .padding(bottom = 15.dp)
                            .fillMaxWidth(),
                        onClick = {
                            if (!viewModel.isButtonEnabled.value) viewModel.onEvent(
                                QuestionScreenEvent.OnChooseAnswer(0)
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = viewModel.colors[0].value
                        )
                    ) {
                        Text(
                            text = viewModel.answer0.value,
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )
                    }
                    TextButton(
                        modifier = Modifier.fillMaxWidth(), onClick = {
                            if (!viewModel.isButtonEnabled.value) viewModel.onEvent(
                                QuestionScreenEvent.OnChooseAnswer(1)
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = viewModel.colors[1].value
                        )
                    ) {
                        Text(
                            text = viewModel.answer1.value,
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )
                    }
                }
                Column(
                    modifier = Modifier.weight(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextButton(
                        modifier = Modifier
                            .padding(bottom = 15.dp)
                            .fillMaxWidth(),
                        onClick = {
                            if (!viewModel.isButtonEnabled.value) viewModel.onEvent(
                                QuestionScreenEvent.OnChooseAnswer(2)
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = viewModel.colors[2].value
                        )
                    ) {
                        Text(
                            text = viewModel.answer2.value,
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )
                    }
                    TextButton(
                        modifier = Modifier.fillMaxWidth(), onClick = {
                            if (!viewModel.isButtonEnabled.value) viewModel.onEvent(
                                QuestionScreenEvent.OnChooseAnswer(3)
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = viewModel.colors[3].value
                        )
                    ) {
                        Text(
                            text = viewModel.answer3.value,
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                viewModel.onEvent(QuestionScreenEvent.OnContinueGame)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            enabled = viewModel.isButtonEnabled.value
        ) {
            Text(text = "Continue")
        }
    }
}