package org.delcom.intermidate.NullSafety

class Exercise3

// Ganti nama agar tidak bentrok dengan User di exercise lain
data class ActiveUser(val username: String, val isActive: Boolean)

fun getActiveUsernames(users: List<ActiveUser>): List<String> {
    return users.mapNotNull { user ->
        user.username.takeIf { user.isActive }
    }
}

fun main() {

    val allUsers = listOf(
        ActiveUser("alice123", true),
        ActiveUser("bob_the_builder", false),
        ActiveUser("charlie99", true)
    )

    println(getActiveUsernames(allUsers))
    // [alice123, charlie99]
}
