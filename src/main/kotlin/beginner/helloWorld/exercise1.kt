package org.delcom.beginner.helloWorld

// Cara 1: Gunakan object (singleton)
object Exercise1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val name = "Mary"
        val age = 20
        println("$name is $age years old")
    }
}