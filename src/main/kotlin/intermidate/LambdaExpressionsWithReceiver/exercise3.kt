package org.delcom.intermidate.LambdaExpressionsWithReceiver

// Exercise 3 - Incrementing List Elements
fun main() {
    println("=== List Increment Extension Function ===\n")

    // Solution from the exercise
    println("1. Given Solution:")
    println("-".repeat(30))

    fun List<Int>.incremented(): List<Int> {
        val originalList = this
        return buildList {
            for (n in originalList) add(n + 1)
        }
    }

    val originalList = listOf(1, 2, 3)
    val newList = originalList.incremented()
    println("Original: $originalList")
    println("Incremented: $newList")

    println("\n" + "=".repeat(50) + "\n")

    // Understanding the code
    println("2. Code Explanation:")
    println("-".repeat(30))

    println("fun List<Int>.incremented(): List<Int> {")
    println("    val originalList = this  // 'this' refers to the List")
    println("    return buildList {      // Creates new MutableList")
    println("        for (n in originalList) add(n + 1)")
    println("        // Inside buildList, 'this' is the MutableList builder")
    println("    }")
    println("}")

    println("\nKey points:")
    println("• Extension function on List<Int>")
    println("• buildList { } creates new list")
    println("• Inside lambda, can call add() directly")
    println("• Returns immutable List<Int>")

    println("\n" + "=".repeat(50) + "\n")

    // Step-by-step breakdown
    println("3. Step-by-Step Breakdown:")
    println("-".repeat(30))

    fun List<Int>.incrementedStepByStep(): List<Int> {
        // Step 1: Store reference to original list
        val originalList = this

        // Step 2: Create new list using buildList
        val result = buildList {
            // Step 3: Inside this lambda, 'this' is MutableList<Int>
            println("    Inside buildList, 'this' is: ${this::class.simpleName}")

            // Step 4: Iterate through original list
            for (n in originalList) {
                println("    Processing element: $n")

                // Step 5: Add incremented value to new list
                add(n + 1)
                println("    Added: ${n + 1}")
            }

            println("    Current builder state: $this")
        }

        return result
    }

    val demoList = listOf(5, 10, 15)
    println("Demonstrating with list: $demoList")
    val result = demoList.incrementedStepByStep()
    println("Final result: $result")

    println("\n" + "=".repeat(50) + "\n")

    // Alternative implementations
    println("4. Alternative Implementations:")
    println("-".repeat(30))

    // Using this directly (no need for originalList variable)
    fun List<Int>.incrementedDirect(): List<Int> =
        buildList {
            for (n in this@incrementedDirect) add(n + 1)
        }

    // Using map (simplest)
    fun List<Int>.incrementedMap(): List<Int> = map { it + 1 }

    // Using forEach with mutable list
    fun List<Int>.incrementedForEach(): List<Int> {
        val result = mutableListOf<Int>()
        this.forEach { result.add(it + 1) }
        return result
    }

    // Using fold
    fun List<Int>.incrementedFold(): List<Int> =
        fold(mutableListOf()) { acc, num ->
            acc.apply { add(num + 1) }
        }

    val testList = listOf(2, 4, 6, 8)
    println("Test list: $testList")
    println("Direct: ${testList.incrementedDirect()}")
    println("Map: ${testList.incrementedMap()}")
    println("ForEach: ${testList.incrementedForEach()}")
    println("Fold: ${testList.incrementedFold()}")

    println("\n" + "=".repeat(50) + "\n")

    // Performance comparison
    println("5. Performance Comparison:")
    println("-".repeat(30))

    val largeList = List(10000) { it } // List from 0 to 9999

    println("Testing with list of ${largeList.size} elements")

    val time1 = System.currentTimeMillis()
    val result1 = largeList.incremented()
    val time2 = System.currentTimeMillis()

    val result2 = largeList.incrementedMap()
    val time3 = System.currentTimeMillis()

    println("buildList time: ${time2 - time1} ms")
    println("map time: ${time3 - time2} ms")
    println("First 5 elements: ${result1.take(5)}")
    println("Last 5 elements: ${result1.takeLast(5)}")
}