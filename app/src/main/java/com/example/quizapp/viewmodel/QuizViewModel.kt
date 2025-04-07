package com.example.quizapp.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.model.Question
import com.example.quizapp.model.QuestionType
import com.example.quizapp.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private var quizRepository: QuizRepository) : ViewModel() {

    private val _questions = MutableStateFlow<List<Question>> (emptyList())
    val questions: StateFlow<List<Question>> get() = _questions


    private var _currentIndex = mutableIntStateOf(0)
    val currentIndex get() = _currentIndex


    init{
        loadQuestions()
    }

    fun nextQuestion() {
        if (_currentIndex.intValue < _questions.value.size - 1) {
            _currentIndex.intValue += 1
        }
    }


    private fun loadQuestions() {
        viewModelScope.launch {
            quizRepository.getQuestions().collect{
                _questions.value = it
            }
        }
    }

    private fun compareListsCaseInsensitive(list1: List<String>, list2: List<String>): Boolean {
        if (list1.size != list2.size) {
            return false
        }
        val lowerCaseList1 = list1.map { it.lowercase() }
        val lowerCaseList2 = list2.map { it.lowercase() }
        return lowerCaseList1 == lowerCaseList2
    }

    fun updateAnswer(answer: String) {
        val current = _questions.value[_currentIndex.intValue]
        current.userAnswers.clear()
        current.userAnswers.add(answer)
    }

    fun updateMultiAnswer(answers: List<String>) {
        val current = _questions.value[_currentIndex.intValue]
        current.userAnswers.clear()
        current.userAnswers.addAll(answers)
    }

    fun getScore():Int {
        var score = 0
        questions.value.forEach { question ->
            when(question.questionType) {
                QuestionType.TEXT_INPUT, QuestionType.SINGLE_CHOICE, QuestionType.TRUE_FALSE ->
                    if (question.userAnswers.firstOrNull()?.equals(question.correctAnswers.firstOrNull(), ignoreCase = true) == true)
                        score++

                QuestionType.MULTI_ANSWER_CHOICE ->
                    if(question.userAnswers.isNotEmpty() && compareListsCaseInsensitive(question.correctAnswers.sorted(), question.userAnswers.toList().sorted()))
                        score++
            }
        }
        return score
    }


    fun getTotalQuestions():Int {
        return questions.value.size
    }

    fun getCurrentQuestion(): Question {
        return questions.value[_currentIndex.intValue]
    }

    fun resetQuiz() {
        _currentIndex.intValue = 0
        questions.value.forEach { it.userAnswers.clear() }
    }
}
