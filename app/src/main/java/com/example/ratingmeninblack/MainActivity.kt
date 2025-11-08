package com.example.ratingmeninblack

import android.graphics.Color.rgb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ratingmeninblack.ui.theme.RatingMenInBlackTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RatingMenInBlackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var secretNumber by remember { mutableStateOf((1..100).random()) }
    var userGuess by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("Попробуйте угадать число от 1 до 100") }
    var buttonText by remember { mutableStateOf("Проверить") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Игра: Угадай число",
        )

        Text(
            text = message,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        TextField(
            value = userGuess,
            onValueChange = { userGuess = it },
            label = { Text("Введите число") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (buttonText == "Проверить") {
                    val guessNumber = userGuess.toIntOrNull()
                    if (guessNumber == null || guessNumber !in 1..100) {
                        message = "Пожалуйста, введите число от 1 до 100"
                    } else {
                        if (guessNumber == secretNumber) {
                            message = "Поздравляем! Вы угадали число $secretNumber"
                            buttonText = "Сыграть заново"
                        } else if (guessNumber < secretNumber) {
                            message = "Моё число больше"
                        } else {
                            message = "Моё число меньше"
                        }
                    }
                } else {
                    secretNumber = (1..100).random()
                    userGuess = ""
                    message = "Попробуйте угадать число от 1 до 100"
                    buttonText = "Проверить"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(buttonText)
        }
    }
}


@Composable
fun EditNumberField(
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next),
        modifier = modifier
            .padding(top = 275.dp)
            .fillMaxWidth()
            .background(
                Color(color = rgb(255, 255, 255))
            )) }
private fun calculateTip(amount: Double, tipPercent: Double = 15.0, roundUp: Boolean): String {
    var tip = tipPercent / 100 * amount
    if (roundUp) { tip = kotlin.math.ceil(tip) }

    return NumberFormat.getCurrencyInstance().format(tip)

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RatingMenInBlackTheme {
        Greeting("Android")
    }
}