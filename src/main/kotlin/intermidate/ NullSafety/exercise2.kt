package org.delcom.intermidate.` NullSafety`

class exercise2 {
}

data class Subscription(val name: String, val isActive: Boolean)

fun getActiveSubscription(subscriptions: List<Subscription>): Subscription? {
    // Mengembalikan satu subscription aktif saja
    // Jika tidak ada atau lebih dari satu yang aktif → null
    return subscriptions.singleOrNull { it.isActive }
}

fun main() {

    val userWithPremiumPlan = listOf(
        Subscription("Basic Plan", false),
        Subscription("Premium Plan", true)
    )

    val userWithConflictingPlans = listOf(
        Subscription("Basic Plan", true),
        Subscription("Premium Plan", true)
    )

    println(getActiveSubscription(userWithPremiumPlan))
    // Subscription(name=Premium Plan, isActive=true)

    println(getActiveSubscription(userWithConflictingPlans))
    // null
}
