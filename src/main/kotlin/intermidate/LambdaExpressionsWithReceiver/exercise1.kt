package org.delcom.intermidate.LambdaExpressionsWithReceiver

// Exercise 1 - Lambda Expressions with Receiver
fun main() {
    println("=== Lambda Expressions with Receiver ===\n")

    // Given function
    fun fetchData(callback: StringBuilder.() -> Unit) {
        val builder = StringBuilder("Data received")
        builder.callback()
    }

    // Solution from the exercise
    println("Solution:")
    println("-".repeat(30))

    fetchData {
        append(" - Processed")
        println(this.toString())
    }

    println("\n" + "=".repeat(50) + "\n")

    // What's happening behind the scenes
    println("=== Understanding the Code ===")
    println("-".repeat(30))

    println("1. fetchData() receives a lambda with StringBuilder as receiver")
    println("2. Inside fetchData():")
    println("   • Creates StringBuilder with \"Data received\"")
    println("   • Calls the lambda on that StringBuilder")
    println("3. Inside the lambda:")
    println("   • 'this' refers to the StringBuilder")
    println("   • append(\" - Processed\") adds text to StringBuilder")
    println("   • toString() converts StringBuilder to String")
    println("   • println() prints the result")

    println("\nEquivalent code without lambda with receiver:")
    println("-".repeat(30))

    // Traditional approach
    fun fetchDataTraditional(callback: (StringBuilder) -> Unit) {
        val builder = StringBuilder("Data received")
        callback(builder)
    }

    fetchDataTraditional { builder ->
        builder.append(" - Processed")
        println(builder.toString())
    }

    println("\n" + "=".repeat(50) + "\n")

    // Multiple ways to write the same thing
    println("=== Different Ways to Write It ===")
    println("-".repeat(30))

    println("Method 1 - Implicit 'this' (recommended):")
    fetchData {
        append(" - Processed")
        println(toString())
    }

    println("\nMethod 2 - Explicit 'this':")
    fetchData {
        this.append(" - Processed")
        println(this.toString())
    }

    println("\nMethod 3 - Using it (if receiver wasn't specified):")
    fetchData {
        // Can't use 'it' here because lambda has receiver
        // This shows the difference
    }

    println("\nMethod 4 - Multiple operations:")
    fetchData {
        append(" - ")
        append("Processed")
        append(" successfully!")
        println(this)
    }

    println("\n" + "=".repeat(50) + "\n")

    // Practice with different string operations
    println("=== Practice with String Operations ===")
    println("-".repeat(30))

    fun buildMessage(callback: StringBuilder.() -> Unit): String {
        val builder = StringBuilder("Message:")
        builder.callback()
        return builder.toString()
    }

    val message1 = buildMessage {
        append(" Hello")
        append(" World")
        append("!")
    }
    println("Message 1: $message1")

    val message2 = buildMessage {
        append(" Start")
        insert(8, "Custom ") // Insert at position 8
        append(" End")
    }
    println("Message 2: $message2")

    val message3 = buildMessage {
        append(" UPPERCASE")
        setLength(8) // Truncate to "Message:"
        append(" lowercase")
    }
    println("Message 3: $message3")
}