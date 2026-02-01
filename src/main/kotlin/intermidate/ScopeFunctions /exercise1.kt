package org.delcom.intermidate.ScopeFunctions

// Exercise 1 - Versi sederhana tanpa error
fun main() {
    println("=== Refactoring with Safe Calls and let ===\n")

    data class ProductInfo(val priceInDollars: Double?)

    class Product {
        fun getProductInfo(): ProductInfo? {
            return ProductInfo(100.0)
        }
    }

    fun convertToEuros(dollars: Double): Double = dollars * 0.85

    // Original function
    fun Product.getPriceInEurosOriginal(): Double? {
        val info = getProductInfo()
        if (info == null) return null
        val price = info.priceInDollars
        if (price == null) return null
        return convertToEuros(price)
    }

    // Refactored function
    fun Product.getPriceInEurosRefactored(): Double? =
        getProductInfo()?.priceInDollars?.let { convertToEuros(it) }

    // Testing
    val product = Product()

    println("Original: ${product.getPriceInEurosOriginal()}")
    println("Refactored: ${product.getPriceInEurosRefactored()}")

    // Demo safe calls
    println("\n=== Safe Calls Demonstration ===")

    val productWithNullInfo = object {
        fun getProductInfo(): ProductInfo? = null
        fun getPriceInEuros(): Double? =
            getProductInfo()?.priceInDollars?.let { convertToEuros(it) }
    }

    val productWithNullPrice = object {
        fun getProductInfo(): ProductInfo? = ProductInfo(null)
        fun getPriceInEuros(): Double? =
            getProductInfo()?.priceInDollars?.let { convertToEuros(it) }
    }

    println("Product with null info: ${productWithNullInfo.getPriceInEuros()}")
    println("Product with null price: ${productWithNullPrice.getPriceInEuros()}")

    // Chaining example
    println("\n=== Chaining Example ===")

    val chainResult = product.getProductInfo()
        ?.priceInDollars
        ?.let { convertToEuros(it) }
        ?.let { "€${String.format("%.2f", it)}" }
        ?: "Price not available"

    println("Formatted price: $chainResult")
}