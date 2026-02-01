package org.delcom.intermidate.Properties

class exercise3 {
}

/**
 * Fungsi pengecekan kesehatan application server
 */
fun checkAppServer(): Boolean {
    println("Performing application server health check...")
    return true
}

/**
 * Fungsi pengecekan kesehatan database
 */
fun checkDatabase(): Boolean {
    println("Performing database health check...")
    return false
}

fun main() {

    // Lazy properties → fungsi mahal hanya dijalankan saat dibutuhkan
    val isAppServerHealthy by lazy { checkAppServer() }
    val isDatabaseHealthy by lazy { checkDatabase() }

    when {
        isAppServerHealthy -> println("Application server is online and healthy")
        isDatabaseHealthy -> println("Database is healthy")
        else -> println("System is offline")
    }

    /*
     Output:
     Performing application server health check...
     Application server is online and healthy
     */
}
