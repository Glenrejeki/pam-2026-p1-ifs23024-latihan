package org.delcom.intermediate.objects

// Exercise 1 - Coffee Shop Order Tracking with Data Objects

// Interface definition
interface Order {
    val orderId: String
    val customerName: String
    val orderTotal: Double
}

// First data object
data object OrderOne : Order {
    override val orderId = "001"
    override val customerName = "Alice"
    override val orderTotal = 15.50
}

// Second data object
data object OrderTwo : Order {
    override val orderId = "002"
    override val customerName = "Bob"
    override val orderTotal = 12.75
}

// Additional data objects for more examples
data object OrderThree : Order {
    override val orderId = "003"
    override val customerName = "Charlie"
    override val orderTotal = 8.25
}

// A regular class for comparison
class RegularOrder(
    override val orderId: String,
    override val customerName: String,
    override val orderTotal: Double
) : Order

// Configuration data object - MOVED OUTSIDE of main()
data object DatabaseConfig : Order {
    override val orderId = "CONFIG-001"
    override val customerName = "System"
    override val orderTotal = 0.0
    const val connectionString = "jdbc:mysql://localhost:3306/coffee_shop"
    const val timeout = 5000
}

// Sealed interface and status objects - MOVED OUTSIDE of main()
sealed interface OrderStatus
data object Pending : OrderStatus
data object Processing : OrderStatus
data object Completed : OrderStatus
data object Cancelled : OrderStatus

fun main() {
    println("=== Coffee Shop Order Tracking ===\n")

    // Basic example from the prompt
    println("1. Basic Example from Prompt:")
    println("-".repeat(40))

    // Print the name of each data object
    println("Order name: $OrderOne")
    // Order name: OrderOne
    println("Order name: $OrderTwo")
    // Order name: OrderTwo

    // Check if the orders are identical
    println("Are the two orders identical? ${OrderOne == OrderTwo}")
    // Are the two orders identical? false

    if (OrderOne == OrderTwo) {
        println("The orders are identical.")
    } else {
        println("The orders are unique.")
        // The orders are unique.
    }

    println("Do the orders have the same customer name? ${OrderOne.customerName == OrderTwo.customerName}")
    // Do the orders have the same customer name? false

    println("\n" + "=".repeat(60) + "\n")

    // Understanding data objects
    println("2. Understanding Data Objects:")
    println("-".repeat(40))

    println("What are data objects?")
    println("• Single instance objects (singletons)")
    println("• Automatically have toString(), equals(), hashCode()")
    println("• Perfect for representing fixed, immutable data")

    println("\nKey characteristics of data objects:")
    println("1. Singleton instance (only one exists)")
    println("2. Immutable by nature")
    println("3. Useful for configuration, constants, predefined states")
    println("4. Can implement interfaces")

    println("\nSyntax:")
    println("  data object ObjectName : Interface {")
    println("      override val property = value")
    println("  }")

    println("\n" + "=".repeat(60) + "\n")

    // Data object properties
    println("3. Data Object Properties and Methods:")
    println("-".repeat(40))

    println("OrderOne details:")
    println("• Order ID: ${OrderOne.orderId}")
    println("• Customer: ${OrderOne.customerName}")
    println("• Total: $${OrderOne.orderTotal}")
    println("• Hash code: ${OrderOne.hashCode()}")

    println("\nOrderTwo details:")
    println("• Order ID: ${OrderTwo.orderId}")
    println("• Customer: ${OrderTwo.customerName}")
    println("• Total: $${OrderTwo.orderTotal}")
    println("• Hash code: ${OrderTwo.hashCode()}")

    println("\nTesting equality:")
    println("OrderOne == OrderOne: ${OrderOne == OrderOne}")
    println("OrderTwo == OrderTwo: ${OrderTwo == OrderTwo}")

    println("\n" + "=".repeat(60) + "\n")

    // Working with multiple data objects
    println("4. Working with Multiple Data Objects:")
    println("-".repeat(40))

    val allOrders: List<Order> = listOf(OrderOne, OrderTwo, OrderThree)

    println("All orders in the system:")
    allOrders.forEachIndexed { index, order ->
        println("${index + 1}. Order ID: ${order.orderId}, Customer: ${order.customerName}, Total: $${order.orderTotal}")
    }

    println("\nTotal revenue: $${allOrders.sumOf { it.orderTotal }}")
    println("Number of customers: ${allOrders.map { it.customerName }.distinct().size}")

    println("\n" + "=".repeat(60) + "\n")

    // Comparison: Data Objects vs Regular Classes
    println("5. Data Objects vs Regular Classes:")
    println("-".repeat(40))

    val regularOrder1 = RegularOrder("001", "Alice", 15.50)
    val regularOrder2 = RegularOrder("001", "Alice", 15.50)

    println("Regular class instances:")
    println("regularOrder1 == regularOrder2: ${regularOrder1 == regularOrder2}")
    println("regularOrder1 === regularOrder2: ${regularOrder1 === regularOrder2}")
    println("hashCode comparison: ${regularOrder1.hashCode()} vs ${regularOrder2.hashCode()}")

    println("\nData objects:")
    println("OrderOne == OrderOne: ${OrderOne == OrderOne}")
    println("OrderOne === OrderOne: ${OrderOne === OrderOne}")

    println("\nKey differences:")
    println("• Data objects are singletons (=== returns true for same object)")
    println("• Regular classes can have multiple instances with same data")
    println("• Data objects are created once and reused")

    println("\n" + "=".repeat(60) + "\n")

    // Real-world use cases
    println("6. Real-World Use Cases for Data Objects:")
    println("-".repeat(40))

    println("Configuration objects:")
    println("System configuration:")
    println("Database: ${DatabaseConfig.connectionString}")
    println("Timeout: ${DatabaseConfig.timeout}ms")

    println("\nStatus objects:")
    val statuses: List<OrderStatus> = listOf(Pending, Processing, Completed, Cancelled)
    println("Available order statuses:")
    statuses.forEach { println("- $it") }

    println("\n" + "=".repeat(60) + "\n")

    // Pattern matching with data objects
    println("7. Pattern Matching with Data Objects:")
    println("-".repeat(40))

    fun processOrder(order: Order) {
        when (order) {
            OrderOne -> {
                println("Processing Alice's order (VIP customer)")
                println("Applying 10% VIP discount")
                val discountedTotal = order.orderTotal * 0.9
                println("Original total: $${order.orderTotal}")
                println("Discounted total: $$discountedTotal")
            }
            OrderTwo -> {
                println("Processing Bob's order (regular customer)")
                println("No discount applicable")
                println("Total: $${order.orderTotal}")
            }
            OrderThree -> {
                println("Processing Charlie's order (student)")
                println("Applying 15% student discount")
                val discountedTotal = order.orderTotal * 0.85  // Fixed: was 0.15 (85% discount!)
                println("Original total: $${order.orderTotal}")
                println("Discounted total: $$discountedTotal")
            }
            else -> {
                println("Processing unknown order")
                println("Total: $${order.orderTotal}")
            }
        }
    }

    println("Processing orders with pattern matching:")
    allOrders.forEach { order ->
        println("\n--- Processing ${order.customerName}'s order ---")
        processOrder(order)
    }
}