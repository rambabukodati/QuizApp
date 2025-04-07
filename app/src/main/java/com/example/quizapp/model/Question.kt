package com.example.quizapp.model

data class Question(
    val questionText:String,
    val questionType:QuestionType,
    val options:List<String> = emptyList(),
    val correctAnswers:List<String> = emptyList(),
    val userAnswers:MutableList<String> = mutableListOf()
)