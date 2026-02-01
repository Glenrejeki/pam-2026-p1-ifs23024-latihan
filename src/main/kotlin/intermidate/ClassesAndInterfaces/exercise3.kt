package org.delcom.intermediate.classesandinterface

// Exercise 3 - Payment Processing System

// Interface for refundable payments
interface Refundable {
    fun refund(amount: Double)
}

// Abstract class for payment methods
abstract class PaymentMethod(val name: String) {
    fun authorize(amount: Double) {
        println("Authorizing payment of $$amount.")
    }

    abstract fun processPayment(amount: Double)
}

// CreditCard class implementing both PaymentMethod and Refundable
class CreditCard(name: String) : PaymentMethod(name), Refundable {
    override fun processPayment(amount: Double) {
        println("Processing credit card payment of $$amount.")
    }

    override fun refund(amount: Double) {
        println("Refunding $$amount to the credit card.")
    }
}

// Additional payment method classes to demonstrate polymorphism
class PayPal(name: String) : PaymentMethod(name) {
    override fun processPayment(amount: Double) {
        println("Processing PayPal payment of $$amount.")
    }

    fun sendPaymentNotification(email: String) {
        println("Payment notification sent to $email")
    }
}

class BankTransfer(name: String) : PaymentMethod(name), Refundable {
    override fun processPayment(amount: Double) {
        println("Processing bank transfer of $$amount.")
    }

    override fun refund(amount: Double) {
        println("Refunding $$amount to the bank account.")
    }

    fun getBankDetails(): String {
        return "Bank details for $name"
    }
}

fun main() {
    println("=== Payment Processing System ===\n")

    // Basic example from the prompt
    println("1. Basic Example from Prompt:")
    println("-".repeat(40))

    val visa = CreditCard("Visa")

    visa.authorize(100.0)
    visa.processPayment(100.0)
    visa.refund(50.0)

    println("\n" + "=".repeat(60) + "\n")

    // Understanding the implementation
    println("2. Understanding the Implementation:")
    println("-".repeat(40))

    println("Interface Refundable:")
    println("• Defines refund() method")
    println("• Can be implemented by multiple payment types")

    println("\nAbstract class PaymentMethod:")
    println("• Defines common property: name")
    println("• Provides concrete method: authorize()")
    println("• Declares abstract method: processPayment()")

    println("\nClass CreditCard:")
    println("• Extends PaymentMethod (inherits authorize() and name)")
    println("• Implements Refundable interface")
    println("• Provides implementations for processPayment() and refund()")

    println("\n" + "=".repeat(60) + "\n")

    // Creating more payment methods
    println("3. Creating More Payment Methods:")
    println("-".repeat(40))

    val paypal = PayPal("PayPal")
    val bankTransfer = BankTransfer("Bank Transfer")

    println("Testing PayPal:")
    paypal.authorize(200.0)
    paypal.processPayment(200.0)
    paypal.sendPaymentNotification("user@example.com")

    println("\nTesting Bank Transfer:")
    bankTransfer.authorize(500.0)
    bankTransfer.processPayment(500.0)
    bankTransfer.refund(100.0)
    println(bankTransfer.getBankDetails())

    println("\n" + "=".repeat(60) + "\n")

    // Working with polymorphism
    println("4. Working with Polymorphism:")
    println("-".repeat(40))

    val paymentMethods: List<PaymentMethod> = listOf(visa, paypal, bankTransfer)

    println("Processing payments through all methods:")
    paymentMethods.forEach { payment ->
        payment.authorize(75.0)
        payment.processPayment(75.0)
        println()
    }

    println("\n" + "=".repeat(60) + "\n")

    // Type checking and interfaces
    println("5. Type Checking and Interface Implementation:")
    println("-".repeat(40))

    val transactions = listOf(150.0, 75.0, 300.0)

    for ((index, payment) in paymentMethods.withIndex()) {
        val amount = transactions[index]
        println("\nProcessing payment #${index + 1} with ${payment.name}:")

        // Check if payment is refundable
        when (payment) {
            is Refundable -> {
                println("  This payment method is refundable")
                payment.processPayment(amount)
                payment.refund(amount * 0.1) // Refund 10%
            }
            else -> {
                println("  This payment method is NOT refundable")
                payment.processPayment(amount)
            }
        }
    }

    println("\n" + "=".repeat(60) + "\n")

    // Real-world scenario
    println("6. Real-World E-commerce Scenario:")
    println("-".repeat(40))

    data class Order(val id: String, val totalAmount: Double, val paymentMethod: PaymentMethod)

    fun processOrder(order: Order) {
        println("\nProcessing order #${order.id}")
        println("Total amount: $${order.totalAmount}")
        println("Payment method: ${order.paymentMethod.name}")

        order.paymentMethod.authorize(order.totalAmount)
        order.paymentMethod.processPayment(order.totalAmount)

        // Simulate a refund if something went wrong
        if (order.paymentMethod is Refundable) {
            println("\nSimulating partial refund due to item out of stock...")
            (order.paymentMethod as Refundable).refund(order.totalAmount * 0.3)
        }
    }

    val orders = listOf(
        Order("ORD001", 250.0, visa),
        Order("ORD002", 150.0, paypal),
        Order("ORD003", 500.0, bankTransfer)
    )

    orders.forEach { order ->
        processOrder(order)
        println("-".repeat(30))
    }
}