package org.delcom.intermidate.Properties

class exercise1 {
}

/**
 * Mengembalikan daftar indeks buku yang stoknya habis (nilai = 0)
 */
fun findOutOfStockBooks(inventory: List<Int>): List<Int> {
    return buildList {
        for (index in inventory.indices) {
            if (inventory[index] == 0) {
                add(index)
            }
        }
    }
}

fun main() {
    val inventory = listOf(3, 0, 7, 0, 5)
    println(findOutOfStockBooks(inventory))
    // Output: [1, 3]
}
