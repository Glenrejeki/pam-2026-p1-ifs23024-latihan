package org.delcom.intermidate.ScopeFunctions

// Exercise 2 - Using apply and also
fun main() {
    println("=== User Email Update with apply and also ===\n")

    data class User(val id: Int, var email: String)

    // Solution from the exercise
    fun updateEmail(user: User, newEmail: String): User =
        user.apply {
            this.email = newEmail
        }.also {
            println("Updating email for user with ID: ${it.id}")
        }

    // Test the function
    val user = User(1, "old_email@example.com")
    println("Original User: $user")
    println()

    val updatedUser = updateEmail(user, "new_email@example.com")
    println("\nUpdated User: $updatedUser")

    println("\n" + "=".repeat(50) + "\n")

    // Understanding what happens
    println("=== Understanding the Code ===")
    println("-".repeat(30))

    println("Step 1: apply { ... }")
    println("  • Operates on 'this' (the user object)")
    println("  • Updates email property")
    println("  • Returns the modified user object")

    println("\nStep 2: also { ... }")
    println("  • Receives the modified user as 'it'")
    println("  • Prints log message with user ID")
    println("  • Returns the same user object")

    println("\n" + "=".repeat(50) + "\n")

    // Visual demonstration
    println("=== Visual Demonstration ===")
    println("-".repeat(30))

    fun demonstrateStepByStep(user: User, newEmail: String): User {
        println("\nStarting with user: $user")

        // Step 1: apply
        val afterApply = user.apply {
            println("\nInside apply block:")
            println("  'this' refers to: $this")
            println("  Setting email from '${this.email}' to '$newEmail'")
            this.email = newEmail
            println("  After modification: $this")
        }
        println("\nAfter apply: $afterApply")

        // Step 2: also
        val finalResult = afterApply.also {
            println("\nInside also block:")
            println("  'it' refers to: $it")
            println("  Printing log: Updating email for user with ID: ${it.id}")
        }
        println("\nFinal result: $finalResult")

        return finalResult
    }

    demonstrateStepByStep(User(99, "demo@example.com"), "updated@example.com")

    println("\n" + "=".repeat(50) + "\n")

    // Testing edge cases
    println("=== Testing Edge Cases ===")
    println("-".repeat(30))

    val testCases = listOf(
        User(100, "user100@test.com") to "user100@new.com",
        User(200, "") to "empty_to_filled@test.com",
        User(300, "very.long.email.address@domain.example.com") to "short@test.com"
    )

    for ((testUser, newEmail) in testCases) {
        println("\nUpdating user ID ${testUser.id}:")
        val result = updateEmail(testUser, newEmail)
        println("Result: $result")
    }
}