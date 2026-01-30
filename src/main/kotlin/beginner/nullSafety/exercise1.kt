package org.delcom.beginner.nullSafety

// Exercise 1: Null Safety - Employee Salary Lookup
data class Employee(val name: String, val salary: Int)

// Function that may return null
fun employeeById(id: Int): Employee? = when(id) {
    1 -> Employee("Mary", 20)
    2 -> null
    3 -> Employee("John", 21)
    4 -> Employee("Ann", 23)
    else -> null
}

// Solution: Get salary or 0 if employee is null
fun salaryById(id: Int): Int = employeeById(id)?.salary ?: 0

fun main() {
    println("=== Exercise 1: Null Safety - Employee Salary ===\n")

    // Test semua ID dari 1 sampai 5
    println("Testing IDs 1 through 5:")
    println("-".repeat(30))

    for (id in 1..5) {
        val employee = employeeById(id)
        val salary = salaryById(id)
        println("ID $id: Employee = $employee, Salary = $salary")
    }

    println("\n" + "=".repeat(50) + "\n")

    // Calculate total salary (as per the exercise)
    val totalSalary = (1..5).sumOf { id -> salaryById(id) }
    println("Total salary for IDs 1-5: $totalSalary")

    println("\n" + "=".repeat(50) + "\n")

    // Demonstrasi berbagai cara handling null
    println("Different Ways to Handle Null:")
    println("-".repeat(30))

    val testId = 2 // ID yang mengembalikan null

    // Method 1: Safe call operator dengan elvis operator
    println("1. Safe call + Elvis operator:")
    val salary1 = employeeById(testId)?.salary ?: 0
    println("   ID $testId salary: $salary1")

    // Method 2: Using let scope function
    println("\n2. Using let scope function:")
    val salary2 = employeeById(testId)?.let {
        println("   Found employee: ${it.name}")
        it.salary
    } ?: run {
        println("   Employee with ID $testId not found")
        0
    }
    println("   Salary: $salary2")

    // Method 3: Traditional if-else
    println("\n3. Traditional if-else:")
    val employee = employeeById(testId)
    val salary3 = if (employee != null) {
        employee.salary
    } else {
        0
    }
    println("   Salary: $salary3")

    // Method 4: Using also for logging
    println("\n4. Using also for logging:")
    val salary4 = employeeById(testId)?.also {
        println("   Processing salary for ${it.name}")
    }?.salary ?: 0
    println("   Salary: $salary4")

    println("\n" + "=".repeat(50) + "\n")

    // Demonstrasi null safety operators
    println("Null Safety Operators in Action:")
    println("-".repeat(30))

    val employee1 = employeeById(1) // Non-null
    val employee2 = employeeById(2) // Null

    // Safe call operator (?.)
    println("Safe call operator:")
    println("   Employee 1 name: ${employee1?.name}")
    println("   Employee 2 name: ${employee2?.name}")

    // Elvis operator (?:)
    println("\nElvis operator:")
    val name1 = employee1?.name ?: "Unknown"
    val name2 = employee2?.name ?: "Unknown"
    println("   Employee 1 name (with default): $name1")
    println("   Employee 2 name (with default): $name2")

    // Not-null assertion (!!) - HATI-HATI!
    println("\nNot-null assertion (!!) - Dangerous:")
    try {
        println("   Employee 1 salary with !!: ${employee1!!.salary}")
        println("   Employee 2 salary with !!: ${employee2!!.salary}") // Will throw exception
    } catch (e: NullPointerException) {
        println("   ❌ NullPointerException caught for employee2!")
    }

    println("\n" + "=".repeat(50) + "\n")

    // Extended example with more operations
    println("Extended Example - Employee Management:")
    println("-".repeat(30))

    fun getEmployeeInfo(id: Int): String {
        return employeeById(id)?.let { employee ->
            """
            Employee ID: $id
            Name: ${employee.name}
            Salary: $${employee.salary}
            Annual Salary: $${employee.salary * 12}
            """.trimIndent()
        } ?: "Employee with ID $id not found in database"
    }

    println(getEmployeeInfo(1))
    println("\n" + "-".repeat(20))
    println(getEmployeeInfo(2))
    println("\n" + "-".repeat(20))
    println(getEmployeeInfo(3))
}