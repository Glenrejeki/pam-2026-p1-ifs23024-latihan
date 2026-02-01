package org.delcom.intermidate.ExtensionFunctions

// Exercise 1 - Extension Function: isPositive
fun main() {
    println("=== Extension Function: isPositive ===\n")

    // Solution 1: One-liner extension function
    fun Int.isPositive(): Boolean = this > 0

    println("Solution 1: One-liner")
    println("-".repeat(30))
    println("1.isPositive() = ${1.isPositive()}")
    println("0.isPositive() = ${0.isPositive()}")
    println("(-5).isPositive() = ${(-5).isPositive()}")

    println("\n" + "=".repeat(50) + "\n")

    // Solution 2: With explicit return
    fun Int.isPositive2(): Boolean {
        return this > 0
    }

    println("Solution 2: With explicit return")
    println("-".repeat(30))
    println("1.isPositive2() = ${1.isPositive2()}")
    println("0.isPositive2() = ${0.isPositive2()}")
    println("(-10).isPositive2() = ${(-10).isPositive2()}")

    println("\n" + "=".repeat(50) + "\n")

    // Solution 3: As an expression body
    fun Int.isPositive3(): Boolean = this > 0

    println("Solution 3: Expression body")
    println("-".repeat(30))
    println("100.isPositive3() = ${100.isPositive3()}")
    println("(-1).isPositive3() = ${(-1).isPositive3()}")

    println("\n" + "=".repeat(50) + "\n")

    // Testing with various numbers
    println("Testing with Various Numbers:")
    println("-".repeat(30))

    val testCases = listOf(-100, -50, -1, 0, 1, 50, 100)

    println("Number\t| isPositive()")
    println("-".repeat(20))
    for (num in testCases) {
        println("$num\t| ${num.isPositive()}")
    }
}