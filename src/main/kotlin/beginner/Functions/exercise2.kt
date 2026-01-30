package org.delcom.beginner.Functions

class Exercise2 {
    // Fungsi repeatN yang memanggil action sebanyak n kali
    fun repeatN(n: Int, action: () -> Unit) {
        println("repeatN called with n = $n")
        for (i in 1..n) {
            print("[$i] ")
            action()  // Memanggil fungsi action
        }
        println() // New line setelah selesai
    }

    fun demonstrateRepeatFunction() {
        println("=== Exercise 2: Higher-Order Function - repeatN ===\n")

        // Demonstration 1: Basic usage
        println("1. Basic Usage - Print Hello 5 times:")
        println("-".repeat(40))
        repeatN(5) {
            println("Hello")
        }

        println("\n" + "=".repeat(50) + "\n")

        // Demonstration 2: Different actions
        println("2. Different Actions:")
        println("-".repeat(40))

        println("Counting from 1 to 3:")
        repeatN(3) {
            println("Counting...")
        }

        println("\nPrinting stars:")
        repeatN(4) {
            print("★ ")
        }
        println() // New line

        println("\n" + "=".repeat(50) + "\n")

        // Demonstration 3: Storing lambda in variable
        println("3. Storing Lambda in Variable:")
        println("-".repeat(40))

        val sayHi = { println("Hi there!") }
        val countDown = { println("3... 2... 1... Go!") }

        println("Calling sayHi 2 times:")
        repeatN(2, sayHi)

        println("\nCalling countDown 1 time:")
        repeatN(1, countDown)

        println("\n" + "=".repeat(50) + "\n")

        // Demonstration 4: With parameters and return values
        println("4. More Advanced Examples:")
        println("-".repeat(40))

        // Fungsi yang menerima parameter
        fun repeatNWithCounter(n: Int, action: (Int) -> Unit) {
            for (i in 1..n) {
                action(i)  // Pass the current iteration number
            }
        }

        println("With iteration counter:")
        repeatNWithCounter(3) { iteration ->
            println("Iteration #$iteration")
        }

        println("\nSquare numbers:")
        repeatNWithCounter(5) { num ->
            println("$num squared is ${num * num}")
        }
    }
}

// Fungsi main untuk menjalankan Exercise2
fun main() {
    val exercise = Exercise2()
    exercise.demonstrateRepeatFunction()
}