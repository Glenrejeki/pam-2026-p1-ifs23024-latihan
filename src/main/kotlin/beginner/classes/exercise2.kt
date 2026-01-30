package org.delcom.beginner.classes

// Exercise 2: Nested Data Classes

// Data classes untuk sistem Person
data class Person(val name: Name, val address: Address, val ownsAPet: Boolean = true)
data class Name(val first: String, val last: String)
data class Address(val street: String, val city: City)
data class City(val name: String, val countryCode: String)

fun main() {
    println("=== Exercise 2: Nested Data Classes ===\n")

    // Membuat person sesuai contoh
    val person = Person(
        Name("John", "Smith"),
        Address("123 Fake Street", City("Springfield", "US")),
        ownsAPet = false
    )

    println("Person created: $person")

    println("\n" + "=".repeat(50) + "\n")

    // Mengakses properti nested
    println("Accessing nested properties:")
    println("-".repeat(30))

    println("Full Name: ${person.name.first} ${person.name.last}")
    println("Address: ${person.address.street}, ${person.address.city.name}")
    println("Country: ${person.address.city.countryCode}")
    println("Owns a pet: ${person.ownsAPet}")

    // Destructuring nested objects
    println("\nDestructuring nested objects:")
    println("-".repeat(30))

    val (name, address, ownsAPet) = person
    val (firstName, lastName) = name
    val (street, city) = address
    val (cityName, countryCode) = city

    println("First name: $firstName")
    println("Last name: $lastName")
    println("Street: $street")
    println("City: $cityName")
    println("Country: $countryCode")
    println("Has pet: $ownsAPet")

    // Membuat person lain untuk perbandingan
    println("\n" + "=".repeat(50) + "\n")

    val person2 = Person(
        Name("Jane", "Doe"),
        Address("456 Real Street", City("London", "UK")),
        ownsAPet = true
    )

    println("Person 2: $person2")
    println("Are they equal? ${person == person2}")

    // Modifikasi data class
    println("\nModifying address:")
    println("-".repeat(30))

    val updatedPerson = person.copy(
        address = person.address.copy(
            street = "789 New Street"
        )
    )
    println("Updated person: $updatedPerson")
}