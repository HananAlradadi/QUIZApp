package com.example.quizapp

import androidx.lifecycle.ViewModel

class qViweModel: ViewModel() {

    val allquixe : MutableList<quixe> = mutableListOf (
        quixe(1
            ,"What is the capital of the Kingdom of Saudi Arabia",
    listOf<String> ("Ryaidh" , "Jeddah", "Makkah") ,
    "Ryaidh"
    ) ,
        quixe(2,"What is color Black",
            listOf<String> ("Orange" , "Blue", "Black") ,
            "Ryaidh"
        )   ,
        quixe(3,"What is your favourite color",
            listOf<String> ("Purple" , "Red", "Green") ,
            "Green"
        )
    )
    val score = mutableSetOf("")
    fun checkAnswer(Quixe: quixe , answer : String){
       val list2 = allquixe.map {
           if (it == Quixe && answer == it.correcrtChoice){
               it.copy(isAnswerCorrect = true)
           }
           else if (it == Quixe && answer != it.correcrtChoice){
               it.copy(isAnswerCorrect = false)
           }
           else{
               it
           }

       }.toMutableList()

    }
    fun onSubmit(){

        score.value  = "you get ${allquixe.filter { it }} out of ${allquixe.size}"
    }
}