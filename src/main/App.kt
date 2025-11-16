package main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

suspend fun greet() = withContext(Dispatchers.Default) {

    this.async {
        delay(4.milliseconds)
    }
}

suspend fun greetUser() {

    greet().await()
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

    if (input.contains("/")) {

        listCommands.forEach { e ->
            println(e)
        }
    }

    else if (input.contains("/Timer")) {

        var n = 1
        while(n > 0) {

            n += 1

            timeSleep().await()

            if (n == 25 * 60) {
                println("Done")
                break
            }

            else {
                println("Seconds: $n")
            }
        }
    }

}