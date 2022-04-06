package com.example.asrbot.utils

import com.example.asrbot.utils.Constants.OPEN_GOOGLE
import com.example.asrbot.utils.Constants.OPEN_SEARCH
import com.example.asrbot.utils.Constants.PLAY_MY
import com.example.asrbot.utils.Constants.PLAY_SONG
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.sql.Date

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message =_message.lowercase()

        return when {

            //Flips a coin
            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            //Math calculations
            message.contains("solve") -> {
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }
            }

            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "hey buddy"
                    2 -> "Yo!"
                    else -> "error" }
            }

            message.contains("hy") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "hey buddy"
                    2 -> "Yo!"
                    else -> "error" }
            }

            message.contains("hlo") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "hey buddy"
                    2 -> "Yo!"
                    else -> "error" }
            }

            message.contains("kaise ho") -> {
                when (random) {
                    0 -> "Mae thik hu, tm btao?"
                    1 -> "sb bdhiya,tm btao?"
                    2 -> "thik hee hai, tm btao?"
                    else -> "error" }
            }

            message.contains("sb bdhiya") -> {
                when (random) {
                    0 -> "kkrh"
                    1 -> "tb thik hai, kuch search kro naa"
                    2 -> "Yo!"
                    else -> "error" }
            }

            message.contains("thik hai") -> {
                when (random) {
                    0 -> "kkrh"
                    1 -> "tb thik hai, kuch search kro naa"
                    2 -> "Yo!"
                    else -> "error" }
            }

            message.contains("kya") -> {
                when (random) {
                    0 -> "type search on your keyboard and fir kuch v likho aa jyga"
                    1 -> "tb thik hai, kuch search kro naa"
                    2 -> "Yo!"
                    else -> "error" }
            }

            message.contains("kkrh") -> {
                when (random) {
                    0 -> "kuch nhi,tu bore hoo rha kya?"
                    1 -> "tera msg kaa intezzar"
                    2 -> "aapse baat"
                    else -> "error" }
            }

            message.contains("hnn") -> {
                when (random) {
                    0 -> "kuch search kro naa too!"
                    1 -> "calculate kro naa too"
                    2 -> "ek story sunao naa!!!"
                    else -> "error" }
            }

            message.contains("ohh") -> {
                when (random) {
                    0 -> "kuch search kro naa too!"
                    1 -> "calculate krna hai kuch?"
                    2 -> "ek story sunao naa!!!"
                    else -> "error" }
            }

            message.contains("kuch nhi") -> {
                when (random) {
                    0 -> "kuch search kro naa too!"
                    1 -> "calculate kro naa too"
                    2 -> "ek story sunao naa!!!"
                    else -> "error" }
            }


            //How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "I'm hungry..."
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }

            //What time is it?
            message.contains("time") && message.contains("?")-> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            //Open Google
            message.contains("open") && message.contains("google")-> {
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("search")-> {
                OPEN_SEARCH
            }
            message.contains("play")-> {
                PLAY_SONG
            }

            message.contains("mood")&& message.contains("kharab")&& message.contains("lg")&& message.contains("rha")-> {
                PLAY_MY
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }
}