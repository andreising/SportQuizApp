package com.andrei_singeleytsev.sportquizapp.domain.business


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.andrei_singeleytsev.sportquizapp.domain.data.ListOfQuestions
import com.andrei_singeleytsev.sportquizapp.domain.models.QuestionItem
import com.andrei_singeleytsev.sportquizapp.domain.models.QuestionModel
import com.andrei_singeleytsev.sportquizapp.domain.repository.GameHelperProvider
import com.andrei_singeleytsev.sportquizapp.domain.utils.Categories

class GameHelperImpl :GameHelperProvider {

    private val markedQuestions = mutableMapOf<String, List<Int>>()
    private var currentQuestion = QuestionModel(0f,"","", emptyList(), "")
    private var progress = mutableStateOf(0)

    override suspend fun setQuestionModel() {
        currentQuestion = generateQuestionModel()
    }
    override suspend fun getQuestionModel():QuestionModel {
        return currentQuestion
    }

    override fun getProgress(): MutableState<Int> {
        return progress
    }

    override fun refreshProgress() {
        progress.value = 0
    }

    override fun progressPlus() {
        progress.value++
    }

    private fun generateAngleAsInt(): Int {
        return (1080..1440).random()
    }

    private fun getCategory(int: Int): String {
        val number = int - 1080
        var categorySport = ""
        when(number) {
            in 0..30 -> {
                categorySport = Categories.FOOTBALL

            }
            in 31..60 -> {
                categorySport = Categories.BASKETBALL

            }
            in 61..90 -> {
                categorySport =Categories.HOCKEY

            }
            in 91..120 -> {
                categorySport = Categories.VOLLEYBALL

            }
            in 121..150 -> {
                categorySport = Categories.TENNIS

            }
            in 151..180 -> {
                categorySport = Categories.MARTIAL_ARTS

            }
            in 181..210 -> {
                categorySport = Categories.SKIING

            }
            in 211..240 -> {
                categorySport = Categories.BASEBALL

            }
            in 241..270 -> {
                categorySport = Categories.GOLF

            }
            in 271..300 -> {
                categorySport = Categories.RUGBY

            }
            in 301..330 -> {
                categorySport = Categories.HANDBALL

            }
            in 331..360 -> {
                categorySport = Categories.CYCLING

            }
            else -> {categorySport = ""}
        }
        return categorySport
    }

    private fun checkQuestion(category: String, itemCount: Int): Boolean {
        var list = emptyList<Int>()
        try {
            list = markedQuestions.getValue(category)
        } catch (_: NoSuchElementException) {

        }
        return list.contains(itemCount)
    }

    private fun getQuestionItem(category: String): QuestionItem{
        val array = getArray(category)
        var itemCount: Int
        do {
            itemCount = (array.indices).random()
        } while (checkQuestion(category, itemCount))
        markQuestion(category, itemCount)
        return array[itemCount]
    }

    private fun getArray(category: String):Array<QuestionItem> {
        return when(category){
            ListOfQuestions.Football.category -> ListOfQuestions.Football.array
            ListOfQuestions.Basketball.category -> ListOfQuestions.Basketball.array
            ListOfQuestions.Hockey.category -> ListOfQuestions.Hockey.array
            ListOfQuestions.Volleyball.category -> ListOfQuestions.Volleyball.array
            ListOfQuestions.Martial_Arts.category -> ListOfQuestions.Martial_Arts.array
            ListOfQuestions.Tennis.category -> ListOfQuestions.Tennis.array
            ListOfQuestions.Skiing.category -> ListOfQuestions.Skiing.array
            ListOfQuestions.Baseball.category -> ListOfQuestions.Baseball.array
            ListOfQuestions.Golf.category -> ListOfQuestions.Golf.array
            ListOfQuestions.Rugby.category -> ListOfQuestions.Rugby.array
            ListOfQuestions.Handball.category -> ListOfQuestions.Handball.array
            ListOfQuestions.Cycling.category -> ListOfQuestions.Cycling.array
            else -> emptyArray()
        }
    }

    private fun generateQuestionModel(): QuestionModel{
        val angleAsInt = generateAngleAsInt()
        val category = getCategory(angleAsInt)
        val questionItem = getQuestionItem(category)
        val angle = angleAsInt.toFloat()
        val question = questionItem.question
        val answers = questionItem.answers
        val rightAnswer = answers[0]
        return QuestionModel(
            angle = angle,
            category = category,
            question = question,
            answers = answers,
            currentAnswer = rightAnswer
        )
    }

    private fun markQuestion(category: String, itemCount: Int){
        var list = emptyList<Int>().toMutableList()
        try {
            list = markedQuestions.getValue(category).toMutableList()
        } catch (_: NoSuchElementException) {

        }

        list+=itemCount
        markedQuestions[category] = list
    }




}