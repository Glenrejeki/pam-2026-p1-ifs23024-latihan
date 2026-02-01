package org.delcom.intermediate.classesandinterface

// Exercise 1 - Smart Home System
fun main() {
    println("=== Smart Home System ===\n")

    // Abstract class definition
    abstract class SmartDevice(val name: String) {
        abstract fun turnOn()
        abstract fun turnOff()
    }

    // SmartLight class
    class SmartLight(name: String) : SmartDevice(name) {
        override fun turnOn() {
            println("$name is now ON.")
        }

        override fun turnOff() {
            println("$name is now OFF.")
        }

        fun adjustBrightness(level: Int) {
            println("Adjusting $name brightness to $level%.")
        }
    }

    // SmartThermostat class
    class SmartThermostat(name: String) : SmartDevice(name) {
        override fun turnOn() {
            println("$name thermostat is now heating.")
        }

        override fun turnOff() {
            println("$name thermostat is now off.")
        }

        fun adjustTemperature(temperature: Int) {
            println("$name thermostat set to $temperature°C.")
        }
    }

    // Test the implementation
    println("1. Testing Basic Implementation:")
    println("-".repeat(40))

    val livingRoomLight = SmartLight("Living Room Light")
    val bedroomThermostat = SmartThermostat("Bedroom Thermostat")

    livingRoomLight.turnOn()
    livingRoomLight.adjustBrightness(10)
    livingRoomLight.turnOff()

    println()

    bedroomThermostat.turnOn()
    bedroomThermostat.adjustTemperature(5)
    bedroomThermostat.turnOff()

    println("\n" + "=".repeat(60) + "\n")

    // Understanding abstract classes
    println("2. Understanding Abstract Classes:")
    println("-".repeat(40))

    println("Abstract class SmartDevice:")
    println("• Cannot be instantiated directly")
    println("• Defines common properties (name)")
    println("• Declares abstract methods (turnOn, turnOff)")
    println("• Child classes MUST implement abstract methods")

    println("\nChild class SmartLight:")
    println("• Inherits from SmartDevice")
    println("• Provides implementations for turnOn/turnOff")
    println("• Adds its own method adjustBrightness")

    println("\nChild class SmartThermostat:")
    println("• Inherits from SmartDevice")
    println("• Provides implementations for turnOn/turnOff")
    println("• Adds its own method adjustTemperature")

    println("\n" + "=".repeat(60) + "\n")

    // Creating more devices
    println("3. Creating More Smart Devices:")
    println("-".repeat(40))

    // SmartTV class
    class SmartTV(name: String) : SmartDevice(name) {
        private var channel: Int = 1
        private var volume: Int = 20

        override fun turnOn() {
            println("$name is now ON. Welcome!")
        }

        override fun turnOff() {
            println("$name is now OFF. Goodbye!")
        }

        fun changeChannel(newChannel: Int) {
            channel = newChannel
            println("$name changed to channel $channel")
        }

        fun adjustVolume(level: Int) {
            volume = level.coerceIn(0, 100)
            println("$name volume set to $volume%")
        }

        fun getCurrentChannel(): Int = channel
        fun getCurrentVolume(): Int = volume
    }

    // SmartSpeaker class
    class SmartSpeaker(name: String) : SmartDevice(name) {
        private var isPlaying: Boolean = false
        private var song: String = "No song playing"

        override fun turnOn() {
            println("$name is now ON. Hello!")
        }

        override fun turnOff() {
            println("$name is now OFF.")
            isPlaying = false
        }

        fun playMusic(songName: String) {
            isPlaying = true
            song = songName
            println("$name is now playing: $songName")
        }

        fun stopMusic() {
            isPlaying = false
            println("$name stopped playing music")
        }

        fun getNowPlaying(): String = if (isPlaying) song else "No music playing"
    }

    val livingRoomTV = SmartTV("Living Room TV")
    val kitchenSpeaker = SmartSpeaker("Kitchen Speaker")

    println("Testing SmartTV:")
    livingRoomTV.turnOn()
    livingRoomTV.changeChannel(5)
    livingRoomTV.adjustVolume(30)
    println("Current channel: ${livingRoomTV.getCurrentChannel()}")
    livingRoomTV.turnOff()

    println("\nTesting SmartSpeaker:")
    kitchenSpeaker.turnOn()
    kitchenSpeaker.playMusic("Happy Song")
    println("Now playing: ${kitchenSpeaker.getNowPlaying()}")
    kitchenSpeaker.stopMusic()
    kitchenSpeaker.turnOff()

    println("\n" + "=".repeat(60) + "\n")

    // Working with polymorphism
    println("4. Working with Polymorphism:")
    println("-".repeat(40))

    val smartDevices: List<SmartDevice> = listOf(
        SmartLight("Bedroom Light"),
        SmartThermostat("Hallway Thermostat"),
        SmartTV("Bedroom TV"),
        SmartSpeaker("Living Room Speaker")
    )

    println("Turning on all smart devices:")
    smartDevices.forEach { device ->
        device.turnOn()
    }

    println("\nTurning off all smart devices:")
    smartDevices.forEach { device ->
        device.turnOff()
    }

    // Type checking and casting
    println("\n5. Type Checking and Smart Casting:")
    println("-".repeat(40))

    for (device in smartDevices) {
        println("\nDevice: ${device.name}")

        when (device) {
            is SmartLight -> {
                println("  Type: Smart Light")
                device.adjustBrightness(75)
            }
            is SmartThermostat -> {
                println("  Type: Smart Thermostat")
                device.adjustTemperature(22)
            }
            is SmartTV -> {
                println("  Type: Smart TV")
                device.changeChannel(3)
                device.adjustVolume(25)
                // Actually use getCurrentVolume() to fix the warning
                println("  Current volume: ${device.getCurrentVolume()}")
            }
            is SmartSpeaker -> {
                println("  Type: Smart Speaker")
                device.playMusic("Relaxing Music")
            }
        }
    }
}