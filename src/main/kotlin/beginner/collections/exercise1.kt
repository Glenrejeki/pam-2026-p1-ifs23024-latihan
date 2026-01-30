package org.delcom.beginner.collections

// Exercise 1 - Working with Lists
fun main() {
    val greenNumbers = listOf(1, 4, 23)
    val redNumbers = listOf(17, 2)

    println("Exercise 1: List Operations")
    println("===========================\n")

    // 1. Menampilkan list
    println("Green Numbers: $greenNumbers")
    println("Red Numbers: $redNumbers\n")

    // 2. Total jumlah elemen
    val totalCount = greenNumbers.count() + redNumbers.count()
    println("Total number of elements: $totalCount")

    // 3. Jumlahkan semua angka
    val totalSum = greenNumbers.sum() + redNumbers.sum()
    println("Sum of all numbers: $totalSum")

    // 4. Gabungkan dan urutkan
    val combinedList = greenNumbers + redNumbers
    val sortedList = combinedList.sorted()
    println("Combined and sorted list: $sortedList")

    // 5. Cari angka terbesar dan terkecil
    val maxNumber = combinedList.max()
    val minNumber = combinedList.min()
    println("Maximum number: $maxNumber")
    println("Minimum number: $minNumber")

    // 6. Operasi lain
    println("\nAdditional Operations:")
    println("Contains 23? ${combinedList.contains(23)}")
    println("Contains 5? ${combinedList.contains(5)}")
    println("Average: ${combinedList.average()}")
    println("Even numbers: ${combinedList.filter { it % 2 == 0 }}")
    println("Odd numbers: ${combinedList.filter { it % 2 != 0 }}")
}