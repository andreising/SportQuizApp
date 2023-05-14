package com.andrei_singeleytsev.sportquizapp.presentation.screens.quiz.wheel_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andrei_singeleytsev.sportquizapp.R
import com.andrei_singeleytsev.sportquizapp.presentation.theme.GreenMain
import com.andrei_singeleytsev.sportquizapp.presentation.utils.Routes
import com.andrei_singeleytsev.sportquizapp.presentation.utils.UIEvent

@Composable
fun QuizScreen(
    paddingTop: PaddingValues,
    viewModel: QuizScreenViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is UIEvent.NavigateMain -> {
                    onNavigate(Routes.QUESTION_SCREEN)
                }

                else -> {}
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingTop)
            .background(color = Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.isGameOn.value) {
            ProgressTexts(
                viewModel.progress,
                viewModel.currentScore
            )
        } else {
            InfoText(viewModel.title)
        }
        GamePlace(viewModel.angle)
        MainButton()
    }
}

@Composable
fun ProgressTexts(
    progress: MutableState<Int>,
    score: MutableState<Int>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = progress.value.toString() + "/10",
            style = TextStyle(
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Your score:" + score.value.toString(),
            style = TextStyle(
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun InfoText(title: MutableState<String>) {
    Text(
        text = title.value,
        modifier = Modifier.padding(top = 50.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 35.sp,
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}

@Composable
fun GamePlace(rotationValue: MutableState<Float>) {
    val angle: Float by animateFloatAsState(
        targetValue = rotationValue.value,
        animationSpec = tween(
            durationMillis = 4000
        )
    )
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.wheel),
            contentDescription = "wheel",
            modifier = Modifier.rotate(angle)

        )
        Image(
            alignment = Alignment.TopCenter,
            painter = painterResource(id = R.drawable.triangle),
            contentDescription = "arrow",
            modifier = Modifier
                .size(40.dp)
                .rotate(180f),

            )
    }
}

@Composable
fun MainButton(
    viewModel: QuizScreenViewModel = hiltViewModel()
) {
    Button(
        onClick = {
            if (viewModel.buttonClickable.value) viewModel.onEvent(QuizScreenEvent.OnButtonPressed)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = GreenMain
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)

    ) {
        Text(
            text = viewModel.buttonTitle.value,
            color = Color.Red
        )
    }
}