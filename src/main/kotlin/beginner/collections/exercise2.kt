package org.delcom.beginner.collections

// Exercise 2 - Check Protocol Support
fun main() {
    val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
    val requested = "smtp"
    val isSupported = requested.uppercase() in SUPPORTED

    println("Support for $requested: $isSupported")

    // Bonus: Test dengan berbagai input
    println("\n=== Testing with various protocols ===")

    val testProtocols = listOf("http", "HTTPS", "ftp", "smtp", "ssh", "HTTP", "FTP")

    for (protocol in testProtocols) {
        val supported = protocol.uppercase() in SUPPORTED
        println("Protocol '$protocol' -> Supported: $supported")
    }

    // Alternative solutions
    println("\n=== Alternative Solutions ===")

    // Solution 1: Using contains()
    val isSupported1 = SUPPORTED.contains(requested.uppercase())
    println("Using contains(): $isSupported1")

    // Solution 2: Explicit check
    val isSupported2 = when (requested.uppercase()) {
        in SUPPORTED -> true
        else -> false
    }
    println("Using when expression: $isSupported2")

    // Solution 3: One-liner
    val isSupported3 = requested.uppercase() in setOf("HTTP", "HTTPS", "FTP")
    println("One-liner: $isSupported3")
}