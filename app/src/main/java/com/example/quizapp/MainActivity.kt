package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizapp.ui.theme.QUIZAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QUIZAppTheme {
                // A surface container using the 'background' color from the theme
                val state1 = remember {
                    mutableIntStateOf(0)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {

                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(Modifier.padding(16.dp)) {
                        quiz()
                    }
                }
            }
        }
    }

    @Composable
    fun quiz(viewModel: qViweModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
        LazyColumn{(Modifier.padding(16.dp))
            items(viewModel.quizeList){quizItem ->
                Text(text = quizItem.question)
                val selectedOption = rememberSaveable { mutableStateOf("") }
                quizItem.answerList.forEach{answer->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedOption.value = answer
                            viewModel.checkAnswer(quizItem, answer)
                        }, verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = answer == selectedOption.value,
                            onClick = {selectedOption.value =answer
                                viewModel.checkAnswer(quizItem,answer)
                            })
                        Text(text = answer)
                    }
                }
            }
            item {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        viewModel.onSubmit()
                    }) {
                        Text(text = "Sumbit")
                    }
                }
            }
            item {
                Text(text = viewModel.score.value)
            }
        }    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        QUIZAppTheme {
            Greeting("Android")
        }
    }
}