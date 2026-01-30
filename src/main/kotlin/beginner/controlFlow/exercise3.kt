package org.delcom.beginner.controlFlow

// Exercise 3 - Filter Words Starting with 'l'
fun main() {
    val words = listOf("dinosaur", "limousine", "magazine", "language")

    println("=== Filter Words Starting with 'l' ===\n")
    println("Original list: $words\n")

    // Solution 1: Basic for loop with if
    println("Solution 1: Basic for loop")
    println("-".repeat(30))

    for (w in words) {
        if (w.startsWith("l")) {
            println(w)
        }
    }

    println("\n" + "=".repeat(50) + "\n")

    // Solution 2: Using forEach with lambda
    println("Solution 2: Using forEach")
    println("-".repeat(30))

    words.forEach { word ->
        if (word.startsWith("l")) {
            println(word)
        }
    }

    println("\n" + "=".repeat(50) + "\n")

    // Solution 3: Using filter to create new list
    println("Solution 3: Using filter() to create new list")
    println("-".repeat(30))

    val wordsStartingWithL = words.filter { it.startsWith("l") }
    println("Filtered list: $wordsStartingWithL")

    // Print each word
    wordsStartingWithL.forEach { println(it) }
}