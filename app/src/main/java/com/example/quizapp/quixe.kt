package com.example.quizapp

data class quixe(
    val id:Int,
    val question:String,
    val answerList:List<String>,
    val correcrtChoice:String,
    var isAnswerCorrect:Boolean = false) {



}