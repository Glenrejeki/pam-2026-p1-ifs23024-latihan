package org.delcom.beginner.Functions

// Exercise 1 - Creating URLs with Lambda Expressions
fun main() {
    val actions = listOf("title", "year", "author")
    val prefix = "https://example.com/book-info"
    val id = 5

    println("=== URL Generator ===\n")

    println("Input:")
    println("Actions: $actions")
    println("Prefix: $prefix")
    println("ID: $id\n")

    // Solution 1: Using map with lambda
    println("Solution 1: Using map() with lambda")
    println("-".repeat(30))
    val urls = actions.map { action -> "$prefix/$id/$action" }
    println("Generated URLs: $urls")

    // Print each URL separately
    println("\nIndividual URLs:")
    urls.forEach { println("  • $it") }

    println("\n" + "=".repeat(50) + "\n")

    // Solution 2: Different lambda syntax
    println("Solution 2: Different lambda variations")
    println("-".repeat(30))

    // Variation 1: Using 'it'
    val urls1 = actions.map { "$prefix/$id/$it" }
    println("Using 'it': $urls1")

    // Variation 2: Multi-line lambda
    val urls2 = actions.map { action ->
        val url = "$prefix/$id/$action"
        url.uppercase() // Transform the URL
    }
    println("With transformation: $urls2")

    // Variation 3: Lambda stored in variable
    val urlBuilder: (String) -> String = { action -> "$prefix/$id/$action" }
    val urls3 = actions.map(urlBuilder)
    println("Using stored lambda: $urls3")
}