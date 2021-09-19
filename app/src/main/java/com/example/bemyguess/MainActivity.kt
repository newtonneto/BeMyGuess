package com.example.bemyguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var answer = 0
    var isGameOver = false
    var numOfAttempts = 0

    //A função onCreate é chamada sempre que a tela é montada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startOver()
    }

    fun btnSubmitTapped(view: View) {
        //O ?: garante que o valor de guess não vão ser null, caso o retorno do metodo seja null
        val guess = getUsersGuess() ?: -999
        val textView = findViewById<TextView>(R.id.textView)

        if (guess !in 1..25) {
            textView.text = "Guess must be 1 to 25"
            return
        }

        var message = ""
        numOfAttempts++

        if (guess == answer) {
            message = "Correct! Guess(es): $numOfAttempts"
            isGameOver = true

            val answerTextView = findViewById<TextView>(R.id.answer)
            answerTextView.text = answer.toString()

            val submitButton = findViewById<Button>(R.id.buttonSubmit)
            submitButton.isEnabled = false
        }
        else {
            message = if (guess < answer) "Too low!" else "Too high!"
        }

        textView.text = message
    }

    fun btnStartOverTapped(view: View) {
        startOver()
    }

    //Gera um número aleatorio
    fun generateAnswer() {
        answer = Random.nextInt(1, 25)
    }

    //Recebe o palpite do usuário
    fun getUsersGuess() : Int? {
        val editTextGuess = findViewById<EditText>(R.id.editTextGuess)
        val usersGuess = editTextGuess.text.toString()

        var guessAsInt = 0

        try {
            guessAsInt = usersGuess.toInt()
        }
        catch (e: Exception) {
            return null
        }

        return guessAsInt
    }

    //Reinicia o jogo
    fun startOver() {
        isGameOver = false
        generateAnswer()
        numOfAttempts = 0

        val answerTextView = findViewById<TextView>(R.id.answer)
        answerTextView.text = "??"

        val submitButton = findViewById<Button>(R.id.buttonSubmit)
        submitButton.isEnabled = true

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Guess 1 to 25"

        val editTextGuess = findViewById<EditText>(R.id.editTextGuess)
        editTextGuess.text.clear()
    }
}