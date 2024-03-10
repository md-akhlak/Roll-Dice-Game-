package com.example.rolldice

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rolldice.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var score1 = 0
    private var score2 = 0
    private var isFirstPlayer = true

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.playerOneScore.text = "Player 1 Score : \n $score1"
        binding.playerTwoScore.text = "Player 2 Score : \n $score2"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.rollDice.setOnClickListener {

            val randomNumber = Random.nextInt(1, 7)
            val imageRes = when (randomNumber) {
                1 -> {
                    R.drawable.dice_one
                }

                2 -> {
                    R.drawable.dice_two
                }

                3 -> {
                    R.drawable.dice_three
                }

                4 -> {
                    R.drawable.dice_four
                }

                5 -> {
                    R.drawable.dice_five
                }

                else -> {
                    R.drawable.dice_six
                }
            }

            if (isFirstPlayer) {
                score1 += randomNumber
                binding.playerOneScore.text = "Player 1 Score : \n $score1"
            } else {
                score2 += randomNumber
                binding.playerTwoScore.text = "Player 2 Score : \n $score2"
            }

            if (score1 >= 20 || score2 >= 20) {
                binding.result.visibility = View.VISIBLE
                binding.winner.text = "Winner is ${
                    if (score1 > score2)
                        "Player 1 "
                    else
                        "Player 2"
                }" + " And score is ${
                    if (score1 > score2)
                        score1
                    else
                        score2

                }"
            }

            binding.diceImage.setImageResource(imageRes)

            binding.rollDice.text = if (isFirstPlayer) {
                "Player 2"

            } else {
                "Player 1"
            }

            isFirstPlayer = !isFirstPlayer
        }
    }
}