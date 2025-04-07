package com.example.quizapp.ui.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.ui.theme.QuizAPPTheme
import com.example.quizapp.ui.view.compose.QuizScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizAPPTheme {
                QuizScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuizPreview() {
    QuizScreen()
}