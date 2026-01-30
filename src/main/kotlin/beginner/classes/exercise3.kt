package org.delcom.beginner.classes

import kotlin.random.Random

// Data class yang sama
data class Employee(val name: String, var salary: Int)

// Exercise 3: Random Employee Generator
// Tambahkan 'open' agar bisa di-extend
open class RandomEmployeeGenerator(var minSalary: Int, var maxSalary: Int) {
    // List of potential names - tambahkan 'open' agar bisa di-override
    open val names = listOf("John", "Mary", "Ann", "Paul", "Jack", "Elizabeth",
        "Robert", "Linda", "David", "Susan", "Michael", "Sarah")

    // Function to generate random employee
    open fun generateEmployee(): Employee {
        val randomName = names.random()
        val randomSalary = Random.nextInt(from = minSalary, until = maxSalary)
        return Employee(randomName, randomSalary)
    }

    // Additional functionality
    fun generateMultipleEmployees(count: Int): List<Employee> {
        return List(count) { generateEmployee() }
    }

    fun getRandomName(): String = names.random()

    fun getRandomSalary(): Int = Random.nextInt(from = minSalary, until = maxSalary)
}

fun main() {
    println("=== Exercise 3: Random Employee Generator ===\n")

    // Membuat generator dengan salary range 10-30
    val empGen = RandomEmployeeGenerator(10, 30)

    println("Generating employees with salary range ${empGen.minSalary}-${empGen.maxSalary}:")
    println("-".repeat(60))

    // Generate 3 employees
    println("Employee 1: ${empGen.generateEmployee()}")
    println("Employee 2: ${empGen.generateEmployee()}")
    println("Employee 3: ${empGen.generateEmployee()}")

    // Ubah salary range
    println("\nChanging salary range to 50-100...")
    empGen.minSalary = 50
    empGen.maxSalary = 100

    println("\nGenerating employee with new salary range ${empGen.minSalary}-${empGen.maxSalary}:")
    println("-".repeat(60))
    println("Employee 4: ${empGen.generateEmployee()}")

    println("\n" + "=".repeat(50) + "\n")

    // Additional demonstrations
    println("Additional Features:")
    println("-".repeat(30))

    // Generate multiple employees
    println("\nGenerating 5 employees at once:")
    val employees = empGen.generateMultipleEmployees(5)
    employees.forEachIndexed { index, employee ->
        println("  ${index + 1}. $employee")
    }

    // Statistics
    println("\nStatistics:")
    println("-".repeat(30))

    val totalSalary = employees.sumOf { it.salary }
    val averageSalary = employees.map { it.salary }.average()
    val highestPaid = employees.maxByOrNull { it.salary }
    val lowestPaid = employees.minByOrNull { it.salary }

    println("Total salary: $$totalSalary")
    println("Average salary: $${"%.2f".format(averageSalary)}")
    println("Highest paid: ${highestPaid?.name} ($${highestPaid?.salary})")
    println("Lowest paid: ${lowestPaid?.name} ($${lowestPaid?.salary})")

    // Employee salary adjustment
    println("\nSalary Adjustments:")
    println("-".repeat(30))

    val employee = empGen.generateEmployee()
    println("Original: $employee")

    // Give raises
    employee.salary += 20
    println("After $20 raise: $employee")

    // Give percentage raise
    val percentageRaise = 10 // 10%
    val raiseAmount = (employee.salary * percentageRaise) / 100
    employee.salary += raiseAmount
    println("After $percentageRaise% raise: $employee")

    // Different generator configurations
    println("\n" + "=".repeat(50) + "\n")

    println("Different Generator Configurations:")
    println("-".repeat(30))

    val juniorGen = RandomEmployeeGenerator(20_000, 40_000)
    val seniorGen = RandomEmployeeGenerator(60_000, 120_000)

    println("Junior employee: ${juniorGen.generateEmployee()}")
    println("Senior employee: ${seniorGen.generateEmployee()}")

    // Custom name list example - SEKARANG BISA KERJA
    println("\nCustom Generator with Different Names:")
    println("-".repeat(30))

    class CustomEmployeeGenerator(min: Int, max: Int) : RandomEmployeeGenerator(min, max) {
        // Override the names list dengan nama-nama lain
        override val names = listOf("Alice", "Bob", "Charlie", "Diana", "Eve", "Frank")
    }

    val customGen = CustomEmployeeGenerator(30_000, 50_000)
    println("Custom generator employee: ${customGen.generateEmployee()}")
    println("Another custom employee: ${customGen.generateEmployee()}")
}