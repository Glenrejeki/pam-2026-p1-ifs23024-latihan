package org.delcom.intermediate.classesandinterface

// Exercise 4 - Messenger with Delegation Pattern

// Interface definition
interface Messenger {
    fun sendMessage(message: String)
    fun receiveMessage(): String
}

// Basic implementation
class BasicMessenger : Messenger {
    override fun sendMessage(message: String) {
        println("Sending message: $message")
    }

    override fun receiveMessage(): String {
        return "You've got a new message!"
    }
}

// SmartMessenger using delegation pattern
class SmartMessenger(val basicMessenger: BasicMessenger) : Messenger by basicMessenger {
    override fun sendMessage(message: String) {
        println("Sending a smart message: $message")
        basicMessenger.sendMessage("[smart] $message")
    }
}

// Additional messenger types to demonstrate different patterns
class EncryptedMessenger(val basicMessenger: BasicMessenger) : Messenger by basicMessenger {
    override fun sendMessage(message: String) {
        val encryptedMessage = encrypt(message)
        println("Sending encrypted message")
        basicMessenger.sendMessage(encryptedMessage)
    }

    private fun encrypt(message: String): String {
        // Simple Caesar cipher for demonstration
        return message.map { char ->
            if (char.isLetter()) {
                val base = if (char.isUpperCase()) 'A' else 'a'
                ((char - base + 3) % 26 + base.code).toChar()
            } else {
                char
            }
        }.joinToString("")
    }
}

class TimestampedMessenger(val basicMessenger: BasicMessenger) : Messenger by basicMessenger {
    override fun sendMessage(message: String) {
        val timestamp = java.time.LocalDateTime.now()
        println("Message sent at: $timestamp")
        basicMessenger.sendMessage("[$timestamp] $message")
    }
}

fun main() {
    println("=== Messenger System with Delegation Pattern ===\n")

    // Basic example from the prompt
    println("1. Basic Example from Prompt:")
    println("-".repeat(40))

    val basicMessenger = BasicMessenger()
    val smartMessenger = SmartMessenger(basicMessenger)

    basicMessenger.sendMessage("Hello!")
    // Sending message: Hello!
    println(smartMessenger.receiveMessage())
    // You've got a new message!
    smartMessenger.sendMessage("Hello from SmartMessenger!")
    // Sending a smart message: Hello from SmartMessenger!
    // Sending message: [smart] Hello from SmartMessenger!

    println("\n" + "=".repeat(60) + "\n")

    // Understanding delegation
    println("2. Understanding Delegation Pattern:")
    println("-".repeat(40))

    println("Delegation syntax:")
    println("  class SmartMessenger(val basicMessenger: BasicMessenger) : Messenger by basicMessenger")
    println("\nKey concepts:")
    println("• 'by' keyword indicates delegation")
    println("• SmartMessenger delegates receiveMessage() to basicMessenger")
    println("• SmartMessenger overrides sendMessage() to add smart behavior")
    println("• No code duplication for receiveMessage()")

    println("\nDelegated methods:")
    println("• receiveMessage() → Automatically delegated to basicMessenger")
    println("• sendMessage() → Overridden in SmartMessenger")

    println("\n" + "=".repeat(60) + "\n")

    // Working with different messenger types
    println("3. Working with Different Messenger Types:")
    println("-".repeat(40))

    val encryptedMessenger = EncryptedMessenger(basicMessenger)
    val timestampedMessenger = TimestampedMessenger(basicMessenger)

    println("Testing EncryptedMessenger:")
    encryptedMessenger.sendMessage("Secret Message")

    println("\nTesting TimestampedMessenger:")
    timestampedMessenger.sendMessage("Important Update")
    println("Received: ${timestampedMessenger.receiveMessage()}")

    println("\n" + "=".repeat(60) + "\n")

    // Chaining decorators
    println("4. Chaining Decorators (Advanced):")
    println("-".repeat(40))

    // Fixed the chained messenger - parameter name is consistent
    class ChainedMessenger(val basic: BasicMessenger) : Messenger by basic {
        override fun sendMessage(message: String) {
            println("=== Message Processing Chain ===")
            println("1. Smart preprocessing...")
            val smartMessage = "[smart] $message"

            println("2. Adding timestamp...")
            val timestamp = java.time.LocalDateTime.now()
            val timestampedMessage = "[$timestamp] $smartMessage"

            println("3. Sending through basic messenger...")
            basic.sendMessage(timestampedMessage)

            println("4. Post-send analytics...")
            println("Message analytics: Length=${message.length}, Words=${message.split(" ").size}")
        }
    }

    val chainedMessenger = ChainedMessenger(basicMessenger)
    chainedMessenger.sendMessage("Chained message example")

    println("\n" + "=".repeat(60) + "\n")

    // Polymorphism with messengers
    println("5. Polymorphism with Different Messengers:")
    println("-".repeat(40))

    val messengers: List<Messenger> = listOf(
        basicMessenger,
        smartMessenger,
        encryptedMessenger,
        timestampedMessenger
    )

    println("Sending message through all messengers:")
    messengers.forEachIndexed { index, messenger ->
        println("\nMessenger ${index + 1}: ${messenger.javaClass.simpleName}")
        messenger.sendMessage("Test message ${index + 1}")
    }

    println("\nReceiving messages:")
    messengers.forEachIndexed { index, messenger ->
        println("Messenger ${index + 1}: ${messenger.receiveMessage()}")
    }

    println("\n" + "=".repeat(60) + "\n")

    // Real-world scenario
    println("6. Real-World Chat Application:")
    println("-".repeat(40))

    data class User(val name: String, val messenger: Messenger)

    fun sendGroupMessage(users: List<User>, message: String) {
        println("\nSending group message to ${users.size} users:")
        println("Message: \"$message\"")
        println("-".repeat(30))

        users.forEach { user ->
            println("\nSending to ${user.name}:")
            user.messenger.sendMessage(message)
        }
    }

    val users = listOf(
        User("Alice", basicMessenger),
        User("Bob", smartMessenger),
        User("Charlie", encryptedMessenger),
        User("Diana", timestampedMessenger)
    )

    sendGroupMessage(users, "Meeting at 3 PM today!")

    println("\n" + "=".repeat(60) + "\n")

    // Understanding 'by' keyword
    println("7. Understanding the 'by' Keyword:")
    println("-".repeat(40))

    println("Without delegation (traditional inheritance):")
    println("  class SmartMessenger : BasicMessenger() {")
    println("      override fun sendMessage(message: String) { ... }")
    println("  }")
    println("  → Problem: Tight coupling, can't change implementation at runtime")

    println("\nWith delegation:")
    println("  class SmartMessenger(val basicMessenger: BasicMessenger) : Messenger by basicMessenger")
    println("  → Advantages:")
    println("    1. Loose coupling")
    println("    2. Can swap implementation at runtime")
    println("    3. Follows composition over inheritance principle")
    println("    4. Can combine multiple behaviors")
}