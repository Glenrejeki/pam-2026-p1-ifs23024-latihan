package org.delcom.beginner.basicTypes

// Exercise 1 - Basic Types
fun main() {
    println("=== Basic Types in Kotlin ===\n")

    // Integer Types
    val byteVal: Byte = 100
    val shortVal: Short = 1000
    val intVal: Int = 100000
    val longVal: Long = 10000000000L

    println("Integer Types:")
    println("Byte: $byteVal")
    println("Short: $shortVal")
    println("Int: $intVal")
    println("Long: $longVal")

    // Floating Point Types
    val floatVal: Float = 3.14F
    val doubleVal: Double = 3.1415926535

    println("\nFloating Point Types:")
    println("Float: $floatVal")
    println("Double: $doubleVal")

    // Other Types
    val charVal: Char = 'K'
    val booleanVal: Boolean = true
    val stringVal: String = "Hello, Kotlin!"

    println("\nOther Types:")
    println("Char: $charVal")
    println("Boolean: $booleanVal")
    println("String: $stringVal")

    // Type Inference
    println("\nType Inference:")
    val inferred1 = 42  // Int
    val inferred2 = 3.14  // Double
    val inferred3 = "Text"  // String

    println("inferred1: $inferred1 (${inferred1::class.simpleName})")
    println("inferred2: $inferred2 (${inferred2::class.simpleName})")
    println("inferred3: $inferred3 (${inferred3::class.simpleName})")
}