package com.example.quizapp.ui.view.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quizapp.model.Question

@Composable
fun TextInputQuestion(question: Question, onAnswer: (String) -> Unit) {
    var text by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = question.questionText, style = MaterialTheme.typography.titleMedium)
        TextField(
            value = text,
            onValueChange = { text = it
                onAnswer(it)},
            label = { Text("Your Answer") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)

        )

    }
}