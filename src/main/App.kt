package main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.Random
import kotlin.math.abs
import kotlin.time.Duration.Companion.milliseconds

suspend fun greet() = withContext(Dispatchers.Default) {

    this.async {
        delay(4.milliseconds)
    }
}

suspend fun greetUser() {

    greet().await()
}

interface Maths {
    fun add(a: Int, b: Int): Int
    fun sub(a: Int, b: Int): Int
    fun mul(a: Int, b: Int): Int
    fun div(a: Int, b: Int): Int
}

class Math : Maths {
    override fun add(a: Int, b: Int): Int {
        return a + b
    }

    override fun sub(a: Int, b: Int): Int {

        return abs(a - b)
    }


    override fun mul(a: Int, b: Int): Int {

        return abs(a * b)
    }


    override fun div(a: Int, b: Int): Int {

        return abs(a / b)
    }
}

interface Commands {

    fun loggingList(): List<String>
}
class Logger : Commands {

    override fun loggingList(): List<String> {
        val listCommands = listOf("/Home", "/Timer", "/Math")
        return listCommands
    }
}

suspend fun timeSleep() = withContext(Dispatchers.Default) {

    this.async {
        delay(1000.milliseconds)
    }

}

suspend fun main() {

    val log = Logger()
    val listCommands = log.loggingList()

    greetUser()

    println("Enter One Command: ")
    val input: String = readln()

    val isMathInput = input.contains("/Math")
    val isColorPicked = input.startsWith("/Colors")

    if (input.contains("/")) {

        listCommands.forEach { e ->
            println(e)
        }
    }

    else if (input.contains("/Timer")) {

        var n = 1
        while (n > 0) {

            n += 1

            timeSleep().await()

            val isMoreGreater = n == 25 * 60

            if (isMoreGreater) {
                println("Done")
            } else {
                println("Seconds: $n")
            }

        }

    }

    else if (isMathInput){
        val options: List<String> = listOf("+", "-", "*", "/")
        val rand = Random()
        val index = rand.nextInt(0, options.size)
        val operator = options[index]

        val addMath = Math()

        val isPlus = operator == "+"
        val isSub = operator == "-"
        val isMul = operator == "*"
        val isDiv = operator == "/"

        val num1 = rand.nextInt(1, 10)
        val num2 = rand.nextInt(1, 10)

        if (isPlus) {
            println("What is: $num1 + $num2")
            val answer = readln().toInt()
            if (answer == addMath.add(num1, num2)) {

                println("Correct Answer")
            }
            else if (answer != addMath.add(num1, num2)) {

                println("Incorrect Answer The Answer was: ${abs(addMath.add(num1, num2))}")
            }

        }

        else if (isSub) {

            println("What is: $num1 - $num2")
            val answer = readln().toInt()
            if (answer == addMath.sub(num1, num2)) {

                println("Correct Answer")
            }
            else if (answer != addMath.sub(num1, num2)) {

                println("Incorrect Answer The Answer was: ${abs(addMath.sub(num1, num2))}")
            }

        }

        else if (isMul) {

            println("What is: $num1 * $num2")
            val answer = readln().toInt()
            if (answer == addMath.sub(num1, num2)) {

                println("Correct Answer")
            }
            else if (answer != addMath.sub(num1, num2)) {

                println("Incorrect Answer The Answer was: ${abs(addMath.sub(num1, num2))}")
            }

        }

        else if (isDiv) {

            println("What is: $num1 / $num2")
            val answer = readln().toInt()
            if (answer == addMath.div(num1, num2)) {

                println("Correct Answer")
            }
            else if (answer != addMath.div(num1, num2)) {

                println("Incorrect Answer The Answer was: ${abs(addMath.div(num1, num2))}")
            }

        }
    }

    if (isColorPicked) {

        val colors = Random()
        val length = colors.nextInt(0, 6)

        val isRed = length == 1
        val isBlue = length == 2
        val isGreen = length == 3
        val isOrange = length == 4
        val isPurple = length == 5

        if (isRed) {

            println("RED")
        }

        else if (isBlue) {

            println("BLUE")
        }

        else if (isGreen) {
            println("GREEN")
        }

        else if(isOrange) {
            println("ORANGE")
        }

        else if (isPurple) {
            println("PURPLE")
        }
    }
}