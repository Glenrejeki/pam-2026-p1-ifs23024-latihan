package org.delcom.intermidate.LambdaExpressionsWithReceiver

// Exercise 2 - Button Event Handling
fun main() {
    println("=== Button Double Click Detection ===\n")

    data class Position(
        val x: Int,
        val y: Int
    )

    data class ButtonEvent(
        val isRightClick: Boolean,
        val amount: Int,
        val position: Position
    )

    class Button {
        fun onEvent(action: ButtonEvent.() -> Unit) {
            // Simulate a double-click event (not a right-click)
            val event = ButtonEvent(
                isRightClick = false,
                amount = 2,
                position = Position(100, 200)
            )
            event.action() // Trigger the event callback
        }
    }

    // Solution
    println("Solution 1: Basic check")
    println("-".repeat(30))

    val button = Button()
    button.onEvent {
        if (!isRightClick && amount == 2) {
            println("Double click!")
        }
    }

    println("\n" + "=".repeat(50) + "\n")

    // Alternative solutions
    println("Alternative Solutions:")
    println("-".repeat(30))

    println("\nSolution 2: Using when expression")
    button.onEvent {
        when {
            !isRightClick && amount == 2 -> println("Double click!")
            else -> println("Not a double click")
        }
    }

    println("\nSolution 3: With position info")
    button.onEvent {
        if (!isRightClick && amount == 2) {
            val (x, y) = position
            println("Double click at position ($x, $y)!")
        }
    }

    println("\nSolution 4: Using destructuring in condition")
    button.onEvent {
        val (x, y) = position
        if (!isRightClick && amount == 2) {
            println("Double click at coordinates x=$x, y=$y!")
        }
    }

    println("\n" + "=".repeat(50) + "\n")

    // Understanding the pattern
    println("=== Understanding the Pattern ===")
    println("-".repeat(30))

    println("Inside button.onEvent { ... }:")
    println("• 'this' refers to ButtonEvent instance")
    println("• Can access properties directly:")
    println("  - isRightClick (not this.isRightClick)")
    println("  - amount (not this.amount)")
    println("  - position (not this.position)")
    println("• Position can be destructured: val (x, y) = position")

    println("\nEquivalent without lambda receiver:")
    println("-".repeat(30))

    class ButtonWithoutReceiver {
        fun onEvent(action: (ButtonEvent) -> Unit) {
            val event = ButtonEvent(false, 2, Position(100, 200))
            action(event)
        }
    }

    val button2 = ButtonWithoutReceiver()
    button2.onEvent { event ->
        if (!event.isRightClick && event.amount == 2) {
            println("Double click! (without receiver)")
        }
    }

    println("\n" + "=".repeat(50) + "\n")

    // Testing different scenarios
    println("=== Testing Different Scenarios ===")
    println("-".repeat(30))

    class TestButton {
        fun simulateEvent(isRightClick: Boolean, clickCount: Int, action: ButtonEvent.() -> Unit) {
            val event = ButtonEvent(isRightClick, clickCount, Position(50, 75))
            println("\nSimulating: ${if (isRightClick) "Right" else "Left"} click, count: $clickCount")
            event.action()
        }
    }

    val testButton = TestButton()

    // Test different click scenarios
    testButton.simulateEvent(isRightClick = false, clickCount = 1) {
        println("  Result: ${if (!isRightClick && amount == 1) "Single click" else "Not single"}")
    }

    testButton.simulateEvent(isRightClick = false, clickCount = 2) {
        println("  Result: ${if (!isRightClick && amount == 2) "Double click!" else "Not double"}")
    }

    testButton.simulateEvent(isRightClick = true, clickCount = 1) {
        println("  Result: ${if (isRightClick) "Right click" else "Not right"}")
    }

    testButton.simulateEvent(isRightClick = false, clickCount = 3) {
        println("  Result: ${if (amount == 3) "Triple click" else "Not triple"}")
    }
}