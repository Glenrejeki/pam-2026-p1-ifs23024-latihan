package intermediate.objects

// Exercise 2 - Vehicle System with Object Declaration

// Interface definition
interface Vehicle {
    val name: String
    fun move(): String
}

// FlyingSkateboard object declaration
object FlyingSkateboard : Vehicle {
    override val name = "Flying Skateboard"
    override fun move() = "Glides through the air with a hover engine"

    fun fly(): String = "Woooooooo"
}

// Additional vehicle objects to demonstrate different patterns
object ElectricCar : Vehicle {
    override val name = "Electric Car"
    override fun move() = "Drives silently on the road"

    fun charge(): String = "Charging battery..."
}

object SpeedBoat : Vehicle {
    override val name = "Speed Boat"
    override fun move() = "Cruises through the water"

    fun anchor(): String = "Dropping anchor"
}

// Companion object example
class RegularCar(private val brand: String) : Vehicle {
    override val name = "$brand Car"
    override fun move() = "Driving on the road"

    companion object Factory {
        fun createSportsCar(): RegularCar = RegularCar("Sports")
        fun createFamilyCar(): RegularCar = RegularCar("Family")
    }
}

// Moved Logger and AppConfig to top-level - CANNOT be inside main()
object Logger {
    private const val PREFIX = "[APP]"

    fun info(message: String) {
        println("$PREFIX INFO: $message")
    }

    fun error(message: String) {
        println("$PREFIX ERROR: $message")
    }

    fun debug(message: String) {
        println("$PREFIX DEBUG: $message")
    }
}

// Moved AppConfig to top-level - CANNOT be inside main()
object AppConfig {
    const val VERSION = "1.0.0"
    const val TIMEOUT = 5000L
    const val MAX_CONNECTIONS = 10

    fun getDatabaseUrl(): String = "jdbc:mysql://localhost:3306/app_db"
}

// Moved SoundMaker interface to top-level - CANNOT be inside main()
interface SoundMaker {
    fun makeSound(): String
}

fun main() {
    println("=== Vehicle System with Object Declarations ===\n")

    // Basic example from the prompt
    println("1. Basic Example from Prompt:")
    println("-".repeat(40))

    println("${FlyingSkateboard.name}: ${FlyingSkateboard.move()}")
    // Flying Skateboard: Glides through the air with a hover engine
    println("${FlyingSkateboard.name}: ${FlyingSkateboard.fly()}")
    // Flying Skateboard: Woooooooo

    println("\n" + "=".repeat(60) + "\n")

    // Understanding object declarations
    println("2. Understanding Object Declarations:")
    println("-".repeat(40))

    println("What are object declarations?")
    println("• Singleton objects (only one instance exists)")
    println("• Created lazily when first accessed")
    println("• Can inherit from classes and interfaces")
    println("• Useful for utility classes, services, constants")

    println("\nSyntax:")
    println("  object ObjectName : ParentClass/Interface {")
    println("      // properties and methods")
    println("  }")

    println("\nKey characteristics:")
    println("1. No constructor (can't create instances with 'new')")
    println("2. Accessed directly by name: ObjectName.method()")
    println("3. Thread-safe initialization")

    println("\n" + "=".repeat(60) + "\n")

    // Working with multiple vehicle objects
    println("3. Working with Multiple Vehicle Objects:")
    println("-".repeat(40))

    val vehicles: List<Vehicle> = listOf(FlyingSkateboard, ElectricCar, SpeedBoat)

    println("All vehicles in the system:")
    vehicles.forEachIndexed { index, vehicle ->
        println("${index + 1}. ${vehicle.name}: ${vehicle.move()}")
    }

    println("\nTesting specific vehicle capabilities:")

    println("\nFlyingSkateboard:")
    println("- Move: ${FlyingSkateboard.move()}")
    println("- Fly: ${FlyingSkateboard.fly()}")

    println("\nElectricCar:")
    println("- Move: ${ElectricCar.move()}")
    println("- Charge: ${ElectricCar.charge()}")

    println("\nSpeedBoat:")
    println("- Move: ${SpeedBoat.move()}")
    println("- Anchor: ${SpeedBoat.anchor()}")

    println("\n" + "=".repeat(60) + "\n")

    // Object vs Class comparison - FIXED: Removed invalid === comparison
    println("4. Object Declarations vs Classes:")
    println("-".repeat(40))

    val car1 = RegularCar.createSportsCar()
    val car2 = RegularCar.createFamilyCar()

    println("Regular Class instances:")
    println("car1: ${car1.name} - ${car1.move()}")
    println("car2: ${car2.name} - ${car2.move()}")
    println("Are they the same instance? ${car1 === car2}")
    println("Are they equal? ${car1 == car2}") // This will check equality

    println("\nObject declarations (singletons):")
    println("FlyingSkateboard instance: ${FlyingSkateboard.name}")
    println("ElectricCar instance: ${ElectricCar.name}")
    // REMOVED: println("Are they the same? ${FlyingSkateboard === ElectricCar}") // This doesn't make sense
    println("Are they equal? ${FlyingSkateboard == ElectricCar}") // This will check equality

    println("\nKey differences:")
    println("• Objects: Single instance, accessed by name")
    println("• Classes: Multiple instances, created with constructor")
    println("• Objects: Perfect for services, utilities, constants")
    println("• Classes: Perfect for data that needs multiple instances")

    println("\nDemonstrating singleton behavior:")
    println("FlyingSkateboard === FlyingSkateboard: ${FlyingSkateboard === FlyingSkateboard}") // Always true
    println("ElectricCar === ElectricCar: ${ElectricCar === ElectricCar}") // Always true

    println("\n" + "=".repeat(60) + "\n")

    // Companion objects
    println("5. Companion Objects (Factory Pattern):")
    println("-".repeat(40))

    val sportsCar = RegularCar.createSportsCar()
    val familyCar = RegularCar.createFamilyCar()

    println("Factory-created vehicles:")
    println("Sports car: ${sportsCar.name} - ${sportsCar.move()}")
    println("Family car: ${familyCar.name} - ${familyCar.move()}")

    println("\nCompanion object features:")
    println("• Belongs to a class (not standalone like object declarations)")
    println("• Can have a name or be anonymous")
    println("• Useful for factory methods, constants related to class")

    println("\n" + "=".repeat(60) + "\n")

    // Real-world use cases
    println("6. Real-World Use Cases:")
    println("-".repeat(40))

    println("Logger utility (singleton):")
    Logger.info("Application started")
    Logger.debug("Loading vehicles...")
    Logger.error("Network connection lost")

    println("\nApp configuration:")
    println("Version: ${AppConfig.VERSION}")
    println("Timeout: ${AppConfig.TIMEOUT}ms")
    println("Max connections: ${AppConfig.MAX_CONNECTIONS}")
    println("Database URL: ${AppConfig.getDatabaseUrl()}")

    println("\n" + "=".repeat(60) + "\n")

    // Object expressions (anonymous objects)
    println("7. Object Expressions (Anonymous Objects):")
    println("-".repeat(40))

    println("Creating anonymous vehicle on the fly:")

    val customVehicle = object : Vehicle {
        override val name = "Custom Hovercraft"
        override fun move() = "Hovers above ground and water"

        fun customFeature(): String = "Has a built-in coffee maker"
    }

    println("Custom vehicle: ${customVehicle.name}")
    println("Movement: ${customVehicle.move()}")
    println("Special feature: ${customVehicle.customFeature()}")

    // Using the top-level SoundMaker interface
    val talkingCar = object : Vehicle, SoundMaker {
        override val name = "Talking Car"
        override fun move() = "Drives while chatting"
        override fun makeSound() = "Beep beep! Hello there!"
    }

    println("\nTalking car:")
    println("Name: ${talkingCar.name}")
    println("Move: ${talkingCar.move()}")
    println("Sound: ${talkingCar.makeSound()}") // This uses the makeSound() function

    // Demonstrate that makeSound() is used by calling it again
    println("Sound check: ${talkingCar.makeSound()}")
}