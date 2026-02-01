package org.delcom.intermidate.ExtensionFunctions

// Exercise 2 - toLowercaseString Extension Function
fun main() {
    println("=== String.toLowercaseString() Extension ===\n")

    // Solution 1: Simple implementation
    println("Solution 1: Simple implementation")
    println("-".repeat(30))

    fun String.toLowercaseString(): String = this.lowercase()

    val testString = "Hello World!"
    println("Input: \"$testString\"")
    println("Output: \"${testString.toLowercaseString()}\"")

    // More test cases
    println("\nTest Cases:")
    println("-".repeat(30))

    val testCases = mapOf(
        "HELLO" to "hello",
        "KotLin" to "kotlin",
        "123 ABC" to "123 abc",
        "UPPER and lower" to "upper and lower",
        "" to ""
    )

    for ((input, expected) in testCases) {
        val result = input.toLowercaseString()
        val status = if (result == expected) "✓" else "✗"
        println("$status \"$input\" → \"$result\" (expected: \"$expected\")")
    }

    println("\n" + "=".repeat(50) + "\n")

    // Solution 2: Comparison with built-in function
    println("Solution 2: Comparison with built-in .lowercase()")
    println("-".repeat(30))

    val string1 = "Programming"
    val string2 = "IN KOTLIN"

    println("Using extension function:")
    println("  \"$string1\".toLowercaseString() = \"${string1.toLowercaseString()}\"")
    println("  \"$string2\".toLowercaseString() = \"${string2.toLowercaseString()}\"")

    println("\nUsing built-in function:")
    println("  \"$string1\".lowercase() = \"${string1.lowercase()}\"")
    println("  \"$string2\".lowercase() = \"${string2.lowercase()}\"")

    println("\nAre they the same?")
    println("  \"$string1\": ${string1.toLowercaseString() == string1.lowercase()}")
    println("  \"$string2\": ${string2.toLowercaseString() == string2.lowercase()}")

    println("\n" + "=".repeat(50) + "\n")

    // Solution 3: Extension with locale consideration
    println("Solution 3: With Locale Consideration")
    println("-".repeat(30))

    fun String.toLowercaseLocaleAware(locale: java.util.Locale = java.util.Locale.getDefault()): String {
        return this.lowercase(locale)
    }

    val localeString = "İSTANBUL" // Turkish dotted I
    println("Locale-aware lowercase:")
    println("  Default locale: \"${localeString.toLowercaseLocaleAware()}\"")
    println("  Turkish locale: \"${localeString.toLowercaseLocaleAware(java.util.Locale("tr", "TR"))}\"")
}