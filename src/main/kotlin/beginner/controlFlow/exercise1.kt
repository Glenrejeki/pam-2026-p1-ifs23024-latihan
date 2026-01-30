package org.delcom.beginner.controlFlow

// Exercise 1 - Pizza Slices Refactoring
fun main() {
    println("=== Original Code (repeated increment) ===")
    var pizzaSlices = 0
    pizzaSlices++
    println("There's only $pizzaSlices slice/s of pizza :(")
    pizzaSlices++
    println("There's only $pizzaSlices slice/s of pizza :(")
    pizzaSlices++
    println("There's only $pizzaSlices slice/s of pizza :(")
    pizzaSlices++
    println("There's only $pizzaSlices slice/s of pizza :(")
    pizzaSlices++
    println("There's only $pizzaSlices slice/s of pizza :(")
    pizzaSlices++
    println("There's only $pizzaSlices slice/s of pizza :(")
    pizzaSlices++
    println("There's only $pizzaSlices slice/s of pizza :(")
    pizzaSlices++
    println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")

    println("\n" + "=".repeat(50) + "\n")

    // Solution 1: Using while loop
    println("=== Solution 1: Using WHILE Loop ===")
    var pizzaSlicesWhile = 0

    while (pizzaSlicesWhile < 8) {
        pizzaSlicesWhile++
        println("There's only $pizzaSlicesWhile slice/s of pizza :(")
    }
    println("There are $pizzaSlicesWhile slices of pizza. Hooray! We have a whole pizza! :D")

    println("\n" + "=".repeat(50) + "\n")

    // Solution 2: Using do-while loop
    println("=== Solution 2: Using DO-WHILE Loop ===")
    var pizzaSlicesDoWhile = 0

    do {
        pizzaSlicesDoWhile++
        println("There's only $pizzaSlicesDoWhile slice/s of pizza :(")
    } while (pizzaSlicesDoWhile < 8)
    println("There are $pizzaSlicesDoWhile slices of pizza. Hooray! We have a whole pizza! :D")
}