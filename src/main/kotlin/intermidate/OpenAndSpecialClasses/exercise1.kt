package intermediate.openandspecialclass

// Exercise 1 - Delivery Status Tracking with Sealed Classes

// Sealed class definition
sealed class DeliveryStatus {
    data class Pending(val sender: String) : DeliveryStatus()
    data class InTransit(val estimatedDeliveryDate: String) : DeliveryStatus()
    data class Delivered(val deliveryDate: String, val recipient: String) : DeliveryStatus()
    data class Canceled(val reason: String) : DeliveryStatus()
}

// Additional sealed class examples - MOVED TO TOP LEVEL
sealed class PaymentStatus {
    data object Pending : PaymentStatus()
    data class Processing(val transactionId: String) : PaymentStatus()
    data class Completed(val amount: Double, val date: String) : PaymentStatus()
    data class Failed(val error: String) : PaymentStatus()
}

// Sealed classes with inheritance - MOVED TO TOP LEVEL
sealed class ApiResponse {
    data class Success(val data: String) : ApiResponse()
    sealed class Error : ApiResponse() {
        data class NetworkError(val message: String) : Error()
        data class ServerError(val code: Int, val message: String) : Error()
        data class ClientError(val message: String) : Error()
    }
}

fun printDeliveryStatus(status: DeliveryStatus) {
    when (status) {
        is DeliveryStatus.Pending -> {
            println("The package is pending pickup from ${status.sender}.")
        }
        is DeliveryStatus.InTransit -> {
            println("The package is in transit and expected to arrive by ${status.estimatedDeliveryDate}.")
        }
        is DeliveryStatus.Delivered -> {
            println("The package was delivered to ${status.recipient} on ${status.deliveryDate}.")
        }
        is DeliveryStatus.Canceled -> {
            println("The delivery was canceled due to: ${status.reason}.")
        }
    }
}

fun main() {
    println("=== Delivery Status Tracking with Sealed Classes ===\n")

    // Basic example from the prompt
    println("1. Basic Example from Prompt:")
    println("-".repeat(40))

    val status1: DeliveryStatus = DeliveryStatus.Pending("Alice")
    val status2: DeliveryStatus = DeliveryStatus.InTransit("2024-11-20")
    val status3: DeliveryStatus = DeliveryStatus.Delivered("2024-11-18", "Bob")
    val status4: DeliveryStatus = DeliveryStatus.Canceled("Address not found")

    printDeliveryStatus(status1)
    // The package is pending pickup from Alice.
    printDeliveryStatus(status2)
    // The package is in transit and expected to arrive by 2024-11-20.
    printDeliveryStatus(status3)
    // The package was delivered to Bob on 2024-11-18.
    printDeliveryStatus(status4)
    // The delivery was canceled due to: Address not found.

    println("\n" + "=".repeat(60) + "\n")

    // Understanding sealed classes
    println("2. Understanding Sealed Classes:")
    println("-".repeat(40))

    println("What are sealed classes?")
    println("• Restricted class hierarchies")
    println("• All subclasses must be declared in the same file")
    println("• Perfect for representing fixed set of types")
    println("• Enable exhaustive when expressions (no else needed)")

    println("\nKey characteristics:")
    println("1. Can't be instantiated directly")
    println("2. Subclasses are known at compile time")
    println("3. When expression must handle all cases (or have else)")
    println("4. Useful for state machines, API responses, error handling")

    println("\nSyntax:")
    println("  sealed class ClassName {")
    println("      data class Subclass1(val param: Type) : ClassName()")
    println("      data class Subclass2(val param: Type) : ClassName()")
    println("      object Subclass3 : ClassName()")
    println("  }")

    println("\n" + "=".repeat(60) + "\n")

    // Working with sealed classes
    println("3. Working with Sealed Classes:")
    println("-".repeat(40))

    val allStatuses: List<DeliveryStatus> = listOf(status1, status2, status3, status4)

    println("All delivery statuses:")
    allStatuses.forEachIndexed { index, status ->
        println("\nStatus ${index + 1}:")
        printDeliveryStatus(status)
    }

    println("\n" + "=".repeat(60) + "\n")

    // Exhaustive when expressions
    println("4. Exhaustive When Expressions:")
    println("-".repeat(40))

    fun getStatusDescription(status: DeliveryStatus): String {
        return when (status) {
            is DeliveryStatus.Pending -> "Waiting for pickup"
            is DeliveryStatus.InTransit -> "On the way"
            is DeliveryStatus.Delivered -> "Successfully delivered"
            is DeliveryStatus.Canceled -> "Delivery canceled"
            // No else needed - compiler knows all cases are covered!
        }
    }

    println("Status descriptions:")
    allStatuses.forEach { status ->
        println("${status::class.simpleName}: ${getStatusDescription(status)}")
    }

    println("\n" + "=".repeat(60) + "\n")

    // Real-world example: Package tracking system
    println("5. Real-World Package Tracking System:")
    println("-".repeat(40))

    data class Package(
        val id: String,
        val description: String,
        var status: DeliveryStatus,
        val weight: Double
    ) {
        fun updateStatus(newStatus: DeliveryStatus) {
            println("\nUpdating package #$id status:")
            println("From: ${status::class.simpleName}")
            println("To: ${newStatus::class.simpleName}")
            status = newStatus
            printDeliveryStatus(status)
        }

        fun getTrackingInfo(): String {
            return when (status) {
                is DeliveryStatus.Pending -> "Package not yet picked up"
                is DeliveryStatus.InTransit -> "Estimated delivery: ${(status as DeliveryStatus.InTransit).estimatedDeliveryDate}"
                is DeliveryStatus.Delivered -> "Delivered on ${(status as DeliveryStatus.Delivered).deliveryDate}"
                is DeliveryStatus.Canceled -> "Canceled: ${(status as DeliveryStatus.Canceled).reason}"
            }
        }
    }

    val package1 = Package("PKG001", "Books", DeliveryStatus.Pending("Bookstore"), 2.5)
    val package2 = Package("PKG002", "Electronics", DeliveryStatus.InTransit("2024-11-25"), 5.0)

    println("Package tracking:")
    println("Package 1: ${package1.description}")
    println("Current status: ${package1.getTrackingInfo()}")

    println("\nPackage 2: ${package2.description}")
    println("Current status: ${package2.getTrackingInfo()}")

    // Simulate status updates
    package1.updateStatus(DeliveryStatus.InTransit("2024-11-22"))
    package1.updateStatus(DeliveryStatus.Delivered("2024-11-21", "Charlie"))

    println("\n" + "=".repeat(60) + "\n")

    // Payment status example (another sealed class)
    println("6. Another Example: Payment Status:")
    println("-".repeat(40))

    fun processPayment(status: PaymentStatus) {
        when (status) {
            PaymentStatus.Pending -> println("Payment is pending approval")
            is PaymentStatus.Processing -> println("Processing transaction: ${status.transactionId}")
            is PaymentStatus.Completed -> println("Payment of $${status.amount} completed on ${status.date}")
            is PaymentStatus.Failed -> println("Payment failed: ${status.error}")
        }
    }

    val payment1 = PaymentStatus.Pending
    val payment2 = PaymentStatus.Processing("TXN12345")
    val payment3 = PaymentStatus.Completed(99.99, "2024-11-19")
    val payment4 = PaymentStatus.Failed("Insufficient funds")

    println("Payment processing:")
    listOf(payment1, payment2, payment3, payment4).forEach { processPayment(it) }

    println("\n" + "=".repeat(60) + "\n")

    // Sealed classes with inheritance
    println("7. Sealed Classes with Inheritance:")
    println("-".repeat(40))

    fun handleResponse(response: ApiResponse) {
        when (response) {
            is ApiResponse.Success -> println("Success: ${response.data}")
            is ApiResponse.Error.NetworkError -> println("Network error: ${response.message}")
            is ApiResponse.Error.ServerError -> println("Server error ${response.code}: ${response.message}")
            is ApiResponse.Error.ClientError -> println("Client error: ${response.message}")
        }
    }

    println("API Response handling:")
    handleResponse(ApiResponse.Success("User data loaded"))
    handleResponse(ApiResponse.Error.NetworkError("Connection timeout"))
    handleResponse(ApiResponse.Error.ServerError(500, "Internal server error"))

    println("\n" + "=".repeat(60) + "\n")

    // Pattern matching with sealed classes
    println("8. Advanced Pattern Matching:")
    println("-".repeat(40))

    fun analyzeDelivery(status: DeliveryStatus) {
        when (status) {
            is DeliveryStatus.Pending -> {
                println("Action: Contact sender ${status.sender}")
                println("Priority: Low")
            }
            is DeliveryStatus.InTransit -> {
                println("Action: Monitor delivery")
                println("Priority: Medium")
                println("ETA: ${status.estimatedDeliveryDate}")
            }
            is DeliveryStatus.Delivered -> {
                println("Action: Confirm with recipient ${status.recipient}")
                println("Priority: Low")
                println("Completion date: ${status.deliveryDate}")
            }
            is DeliveryStatus.Canceled -> {
                println("Action: Investigate reason")
                println("Priority: High")
                println("Issue: ${status.reason}")
            }
        }
    }

    println("Delivery analysis:")
    allStatuses.forEach { status ->
        println("\n--- Analyzing ${status::class.simpleName} ---")
        analyzeDelivery(status)
    }
}