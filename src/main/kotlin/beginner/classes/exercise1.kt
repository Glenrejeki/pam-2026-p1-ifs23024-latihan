package org.delcom.beginner.classes

class exercise1 {
}

data class EmployeeExercise1(val name: String, var salary: Int)

fun main() {
    val emp = EmployeeExercise1("Mary", 20)
    println(emp)
    emp.salary += 10
    println(emp)
}
