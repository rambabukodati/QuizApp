package com.example.quizapp.ui.view.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizapp.model.QuestionType
import com.example.quizapp.viewmodel.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(viewModel:QuizViewModel = hiltViewModel()) {
    val question = viewModel.getCurrentQuestion()
    val score = viewModel.getScore()
    val totalQuestions = viewModel.getTotalQuestions()
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        ScoreDialog(
            score = score,
            totalQuestions = totalQuestions,
            onDismiss = {
                showDialog.value = false
                viewModel.resetQuiz()
            }
        )
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Quiz App") })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            when (question.questionType) {
                QuestionType.TRUE_FALSE -> TrueFalseQuestion(
                    question = question,
                    onAnswer = { answer ->
                        viewModel.updateAnswer(answer)
                    }
                )
                QuestionType.SINGLE_CHOICE -> SingleChoiceQuestion(
                    question = question,
                    onAnswer = { answer ->
                        viewModel.updateAnswer(answer)
                    }
                )
                QuestionType.MULTI_ANSWER_CHOICE -> MultiAnswerQuestion (
                    question = question,
                    onAnswer = { answer ->
                        viewModel.updateMultiAnswer(answer)
                    }
                )
                QuestionType.TEXT_INPUT -> TextInputQuestion(
                    question = question,
                    onAnswer = { answer ->
                        viewModel.updateAnswer(answer)
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 20.dp),
                horizontalArrangement = Arrangement.End
            ) {

                if (viewModel.getCurrentQuestion() == question) {
                    Button(
                        onClick = {
                            if (viewModel.currentIndex.intValue == totalQuestions - 1) {
                                showDialog.value = true
                            } else {
                                viewModel.nextQuestion()
                            }
                        }
                    ) {
                        if (viewModel.currentIndex.intValue != totalQuestions - 1)
                            Text("Next")
                        else
                            Text("Submit")
                    }
                }
            }
        }
    }
}

@Composable
fun ScoreDialog(score: Int, totalQuestions: Int, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Quiz Completed") },
        text = {
            Text("Your score: $score / $totalQuestions")
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")

            }
        }
    )
}