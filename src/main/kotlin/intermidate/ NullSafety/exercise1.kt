package org.delcom.intermidate.` NullSafety`

class exercise1 {
}

data class User(val name: String?)

fun getNotificationPreferences(
    user: Any,
    emailEnabled: Boolean,
    smsEnabled: Boolean
): List<String> {

    // Safe cast: jika bukan User, langsung return list kosong
    val validUser = user as? User ?: return emptyList()

    // Elvis operator: jika nama null, default ke "Guest"
    val userName = validUser.name ?: "Guest"

    // takeIf: hanya ditambahkan jika kondisinya true
    return listOfNotNull(
        "Email Notifications enabled for $userName".takeIf { emailEnabled },
        "SMS Notifications enabled for $userName".takeIf { smsEnabled }
    )
}

fun main() {

    val user1 = User("Alice")
    val user2 = User(null)
    val invalidUser = "NotAUser"

    println(getNotificationPreferences(user1, emailEnabled = true, smsEnabled = false))
    // [Email Notifications enabled for Alice]

    println(getNotificationPreferences(user2, emailEnabled = false, smsEnabled = true))
    // [SMS Notifications enabled for Guest]

    println(getNotificationPreferences(invalidUser, emailEnabled = true, smsEnabled = true))
    // []
}
