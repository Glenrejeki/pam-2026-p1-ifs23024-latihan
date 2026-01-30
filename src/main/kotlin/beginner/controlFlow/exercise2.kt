package org.delcom.beginner.controlFlow

// Exercise 2 - FizzBuzz Game
fun main() {
    println("=== FizzBuzz Game (1 to 100) ===\n")

    // Solution 1: Standard solution
    println("Standard Solution:")
    println("-".repeat(20))

    for (number in 1..100) {
        println(
            when {
                number % 15 == 0 -> "fizzbuzz"
                number % 3 == 0 -> "fizz"
                number % 5 == 0 -> "buzz"
                else -> "$number"
            }
        )
    }

    println("\n" + "=".repeat(50) + "\n")

    // Solution 2: With step-by-step explanation
    println("Solution with Explanation:")
    println("-".repeat(20))

    for (number in 1..30) { // Show only first 30 for clarity
        print("$number: ")

        when {
            number % 15 == 0 -> {
                println("fizzbuzz (divisible by 3 and 5)")
            }
            number % 3 == 0 -> {
                println("fizz (divisible by 3)")
            }
            number % 5 == 0 -> {
                println("buzz (divisible by 5)")
            }
            else -> {
                println("number")
            }
        }
    }
}