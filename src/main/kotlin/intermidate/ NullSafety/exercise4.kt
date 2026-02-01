package org.delcom.intermidate.` NullSafety`

class exercise4 {
}

/**
 * Memvalidasi stok sebelum transaksi
 * Mengembalikan -1 jika:
 * - requested null
 * - available null
 * - requested bernilai negatif
 * - requested lebih besar dari available
 */
fun validateStock(requested: Int?, available: Int?): Int {

    // Elvis operator + early return
    val validRequested = requested ?: return -1
    val validAvailable = available ?: return -1

    // Early return untuk validasi logika
    if (validRequested < 0) return -1
    if (validRequested > validAvailable) return -1

    return validRequested
}

fun main() {

    println(validateStock(5, 10))
    // 5

    println(validateStock(null, 10))
    // -1

    println(validateStock(-2, 10))
    // -1
}
