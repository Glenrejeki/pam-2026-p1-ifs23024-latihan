package org.delcom.beginner.collections

// Exercise 3 - Number to Word Map
fun main() {
    // Solution 1: Using mapOf() with to operator
    val number2word = mapOf(1 to "one", 2 to "two", 3 to "three")
    val n = 2
    println("$n is spelt as '${number2word[n]}'")

    // Alternative syntax
    println("\n=== Alternative Solutions ===")

    // Solution 2: Using Pair() explicitly
    val number2word2 = mapOf(
        Pair(1, "one"),
        Pair(2, "two"),
        Pair(3, "three")
    )
    println("Using Pair(): ${number2word2[3]}")

    // Solution 3: Building map dynamically
    val number2word3 = mutableMapOf<Int, String>()
    number2word3[1] = "one"
    number2word3[2] = "two"
    number2word3[3] = "three"
    println("Using mutableMap: ${number2word3[1]}")

    // Solution 4: Using mapOf with varargs
    val number2word4 = mapOf(
        1 to "uno",
        2 to "dos",
        3 to "tres"
    )
    println("Spanish: 2 is '${number2word4[2]}'")

    // Testing edge cases
    println("\n=== Testing Edge Cases ===")

    val testMap = mapOf(1 to "one", 2 to "two", 3 to "three")

    println("Map: $testMap")
    println("Contains key 1: ${testMap.containsKey(1)}")
    println("Contains key 4: ${testMap.containsKey(4)}")
    println("Contains value 'two': ${testMap.containsValue("two")}")
    println("Contains value 'four': ${testMap.containsValue("four")}")

    // Safe access with elvis operator
    val safeValue = testMap[4] ?: "not found"
    println("Safe access for key 4: '$safeValue'")
}