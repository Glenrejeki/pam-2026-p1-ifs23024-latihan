package org.delcom.intermediate.objects

// Exercise 3 - Temperature Data Class with Companion Object

data class Temperature(val celsius: Double) {
    val fahrenheit: Double = celsius * 9 / 5 + 32
    val kelvin: Double = celsius + 273.15

    companion object {
        // Factory method to create Temperature from Fahrenheit
        fun fromFahrenheit(fahrenheit: Double): Temperature =
            Temperature((fahrenheit - 32) * 5 / 9)

        // Factory method to create Temperature from Kelvin
        fun fromKelvin(kelvin: Double): Temperature =
            Temperature(kelvin - 273.15)

        // Factory method to create Temperature with validation
        fun create(celsius: Double): Temperature {
            require(celsius >= -273.15) { "Temperature cannot be below absolute zero (-273.15°C)" }
            return Temperature(celsius)
        }

        // Constants related to Temperature
        val ABSOLUTE_ZERO = Temperature(-273.15)
        val FREEZING_POINT = Temperature(0.0)
        val BOILING_POINT = Temperature(100.0)
        val ROOM_TEMPERATURE = Temperature(20.0)

        // Utility function
        fun convert(celsius: Double, toUnit: String): String {
            return when (toUnit.lowercase()) {
                "f" -> "${celsius * 9 / 5 + 32}°F"
                "k" -> "${celsius + 273.15}K"
                else -> "${celsius}°C"
            }
        }
    }

    // Helper methods for the instance
    fun toFahrenheitString(): String = "${String.format("%.2f", fahrenheit)}°F"
    fun toCelsiusString(): String = "${String.format("%.2f", celsius)}°C"
    fun toKelvinString(): String = "${String.format("%.2f", kelvin)}K"

    // Comparison methods
    fun isFreezing(): Boolean = celsius <= 0
    fun isBoiling(): Boolean = celsius >= 100
    fun isRoomTemperature(): Boolean = celsius in 18.0..25.0
}

// Moved WeatherReport outside of main() to fix the companion object error
data class WeatherReport(
    val location: String,
    val temperature: Temperature,
    val humidity: Int,
    val condition: String
) {
    // Companion object is now valid since WeatherReport is top-level
    companion object {
        fun parseFromApi(data: Map<String, Any>): WeatherReport {
            val celsius = data["temp_c"] as Double
            return WeatherReport(
                location = data["location"] as String,
                temperature = Temperature(celsius),
                humidity = data["humidity"] as Int,
                condition = data["condition"] as String
            )
        }
    }

    fun display() {
        println("\nWeather in $location:")
        println("• Temperature: ${temperature.toCelsiusString()} / ${temperature.toFahrenheitString()}")
        println("• Humidity: $humidity%")
        println("• Condition: $condition")
        println("• Status: ${getTemperatureStatus()}")
    }

    private fun getTemperatureStatus(): String {
        return when {
            temperature.isFreezing() -> "❄️ Freezing"
            temperature.celsius < 10 -> "🥶 Cold"
            temperature.isRoomTemperature() -> "😊 Comfortable"
            temperature.celsius > 30 -> "🥵 Hot"
            temperature.isBoiling() -> "♨️ Boiling Hot"
            else -> "🌡️ Normal"
        }
    }
}

fun main() {
    println("=== Temperature System with Companion Object ===\n")

    // Basic example from the prompt
    println("1. Basic Example from Prompt:")
    println("-".repeat(40))

    val fahrenheit = 90.0
    val temp = Temperature.fromFahrenheit(fahrenheit)
    println("${temp.celsius}°C is $fahrenheit°F")
    // 32.22222222222222°C is 90.0°F
    println("Formatted: ${temp.toCelsiusString()} = ${temp.toFahrenheitString()}")

    println("\n" + "=".repeat(60) + "\n")

    // Understanding companion objects
    println("2. Understanding Companion Objects:")
    println("-".repeat(40))

    println("What are companion objects?")
    println("• Objects that belong to a class (not standalone)")
    println("• Accessed via class name: ClassName.companionMethod()")
    println("• Can have properties and methods")
    println("• Useful for factory methods, constants, utilities")

    println("\nKey characteristics:")
    println("1. No need to create instance to use companion members")
    println("2. Can have a name or be anonymous")
    println("3. Can implement interfaces")
    println("4. Members are like 'static' in Java but more powerful")

    println("\nSyntax:")
    println("  class MyClass {")
    println("      companion object {")
    println("          // factory methods, constants, utilities")
    println("      }")
    println("  }")

    println("\n" + "=".repeat(60) + "\n")

    // Using factory methods
    println("3. Using Factory Methods:")
    println("-".repeat(40))

    val temp1 = Temperature.fromFahrenheit(212.0) // Boiling point in Fahrenheit
    val temp2 = Temperature.fromKelvin(373.15)    // Boiling point in Kelvin
    val temp3 = Temperature.create(37.0)          // Body temperature

    println("Factory-created temperatures:")
    println("1. From 212°F: ${temp1.toCelsiusString()} (Boiling: ${temp1.isBoiling()})")
    println("2. From 373.15K: ${temp2.toCelsiusString()} (Boiling: ${temp2.isBoiling()})")
    println("3. From 37°C: ${temp3.toCelsiusString()} (Body temperature)")

    // Test validation
    println("\nTesting validation:")
    try {
        Temperature.create(-300.0) // Below absolute zero
    } catch (e: IllegalArgumentException) {
        println("Validation caught: ${e.message}")
    }

    println("\n" + "=".repeat(60) + "\n")

    // Using constants from companion object
    println("4. Using Constants from Companion Object:")
    println("-".repeat(40))

    println("Important temperature constants:")
    println("• Absolute Zero: ${Temperature.ABSOLUTE_ZERO.toCelsiusString()}")
    println("  Fahrenheit: ${Temperature.ABSOLUTE_ZERO.toFahrenheitString()}")
    println("  Kelvin: ${Temperature.ABSOLUTE_ZERO.toKelvinString()}")

    println("\n• Freezing Point: ${Temperature.FREEZING_POINT.toCelsiusString()}")
    println("  Is freezing? ${Temperature.FREEZING_POINT.isFreezing()}")

    println("\n• Boiling Point: ${Temperature.BOILING_POINT.toCelsiusString()}")
    println("  Is boiling? ${Temperature.BOILING_POINT.isBoiling()}")

    println("\n• Room Temperature: ${Temperature.ROOM_TEMPERATURE.toCelsiusString()}")
    println("  Is room temperature? ${Temperature.ROOM_TEMPERATURE.isRoomTemperature()}")

    println("\n" + "=".repeat(60) + "\n")

    // Using utility functions
    println("5. Using Utility Functions:")
    println("-".repeat(40))

    println("Temperature conversions using companion utility:")
    println("25°C = ${Temperature.convert(25.0, "F")}")
    println("25°C = ${Temperature.convert(25.0, "K")}")
    println("98.6°F in Celsius = ${Temperature.fromFahrenheit(98.6).toCelsiusString()}")

    println("\n" + "=".repeat(60) + "\n")

    // Real-world scenario: Weather app
    println("6. Real-World Scenario: Weather App:")
    println("-".repeat(40))

    // Simulate API data
    val apiData = mapOf(
        "location" to "New York",
        "temp_c" to 22.5,
        "humidity" to 65,
        "condition" to "Partly Cloudy"
    )

    val weatherReport = WeatherReport.parseFromApi(apiData)
    weatherReport.display()

    // Create another weather report directly
    val tokyoWeather = WeatherReport(
        location = "Tokyo",
        temperature = Temperature(28.0),
        humidity = 70,
        condition = "Sunny"
    )
    tokyoWeather.display()

    println("\n" + "=".repeat(60) + "\n")

    // Temperature comparison and operations
    println("7. Temperature Comparisons and Operations:")
    println("-".repeat(40))

    val temperatures = listOf(
        Temperature(0.0),
        Temperature(20.0),
        Temperature(37.0),
        Temperature(100.0),
        Temperature.fromFahrenheit(32.0),
        Temperature.fromKelvin(300.0)
    )

    println("Temperature analysis:")
    temperatures.forEachIndexed { index, temp ->
        println("\nTemp ${index + 1}: ${temp.toCelsiusString()}")
        println("  In Fahrenheit: ${temp.toFahrenheitString()}")
        println("  In Kelvin: ${temp.toKelvinString()}")
        println("  Status: ${getStatus(temp)}")
    }

    println("\nSummary:")
    println("Freezing temps: ${temperatures.count { it.isFreezing() }}")
    println("Room temps: ${temperatures.count { it.isRoomTemperature() }}")
    println("Hot temps (>30°C): ${temperatures.count { it.celsius > 30 }}")

    println("\nAverage temperature: ${temperatures.map { it.celsius }.average().let {
        Temperature(it).toCelsiusString()
    }}")

    println("\n" + "=".repeat(60) + "\n")

    // Data class features demonstration
    println("8. Data Class Features:")
    println("-".repeat(40))

    val originalTemp = Temperature(25.0)
    val copiedTemp = originalTemp.copy(celsius = 30.0)

    println("Original: ${originalTemp.toCelsiusString()}")
    println("Copied with modification: ${copiedTemp.toCelsiusString()}")
    println("Are they equal? ${originalTemp == copiedTemp}")
    println("HashCode original: ${originalTemp.hashCode()}")
    println("HashCode copied: ${copiedTemp.hashCode()}")

    // Destructuring
    val (celsius) = originalTemp
    println("\nDestructured value: $celsius°C")
}

fun getStatus(temp: Temperature): String {
    return when {
        temp.celsius <= 0 -> "Freezing"
        temp.celsius < 15 -> "Cold"
        temp.celsius in 15.0..25.0 -> "Comfortable"
        temp.celsius < 35 -> "Warm"
        else -> "Hot"
    }
}