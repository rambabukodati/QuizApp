package com.example.quizapp.repository

import com.example.quizapp.model.Question
import com.example.quizapp.model.QuestionType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class QuizRepository @Inject constructor() {
    private val questions = listOf(
        Question(
            questionText = "A Fragment can exist independently without being attached to an Activity",
            questionType = QuestionType.TRUE_FALSE,
            correctAnswers = listOf("False")
        ),
        Question(
            questionText = "Which of the following is NOT a component of an Android application?",
            questionType = QuestionType.SINGLE_CHOICE,
            options = listOf("Activity", "Service", "Fragment", "Object"),
            correctAnswers = listOf("Object")
        ),

        Question(
            questionText = "Which of the following are valid components in an Android application?",
            questionType = QuestionType.MULTI_ANSWER_CHOICE,
            options = listOf("Activity", "Fragment", "Database", "Service"),
            correctAnswers = listOf("Activity", "Service")
        ),
        Question(
            questionText = "To store a small amount of key-value pairs persistently in Android, you can use ",
            questionType = QuestionType.TEXT_INPUT,
            correctAnswers = listOf("SharedPreferences")
        ),
       /* Question(
            questionText = "A Service in Android runs in the background to perform long-running operations",
            questionType = QuestionType.TRUE_FALSE,
            options = listOf("True", "False"),
            correctAnswers = listOf("True")
        ),
        Question(
            questionText = "Which of the following classes are used to start a new Activity in Android?",
            questionType = QuestionType.MULTI_ANSWER_CHOICE,
            options = listOf("Intent", "ActivityManager", "Context", "Fragment"),
            correctAnswers = listOf("Intent", "Context")
        ),

        Question(
            questionText = "In Android, which method is called when an Activity is first created?",
            questionType = QuestionType.SINGLE_CHOICE,
            options = listOf("onPause()", "onResume()", "onStart()", "onCreate()"),
            correctAnswers = listOf("onCreate()")
        )*/

    )

    fun getQuestions(): Flow<List<Question>> = flow {
        emit(questions)
    }

}