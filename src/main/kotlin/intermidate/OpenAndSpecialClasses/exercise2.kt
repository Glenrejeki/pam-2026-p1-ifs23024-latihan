package intermediate.openandspecialclasses

// Exercise 2 - Status Handling with Sealed Classes and Enum Classes

// Sealed class definition with nested enum
sealed class Status {
    data object Loading : Status()

    data class Error(val problem: Problem) : Status() {
        // Nested enum class for error types
        enum class Problem {
            NETWORK,
            TIMEOUT,
            UNKNOWN
        }
    }

    data class OK(val data: List<String>) : Status()
}

// Additional sealed class with more complex error handling
sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()

    data class Error(val error: ApiError) : ApiResult<Nothing>() {
        sealed class ApiError {
            data object NetworkError : ApiError()
            data class HttpError(val code: Int, val message: String) : ApiError()
            data class ValidationError(val field: String, val message: String) : ApiError()
            data object TimeoutError : ApiError()
        }
    }

    data object Loading : ApiResult<Nothing>()
}

// Moved LogLevel enum to top level
enum class LogLevel {
    DEBUG, INFO, WARNING, ERROR, CRITICAL
}

// Moved LogEntry sealed class to top level
sealed class LogEntry {
    data class Message(val level: LogLevel, val text: String) : LogEntry()
    data class Error(val exception: Throwable, val context: String) : LogEntry()
    data class Metric(val name: String, val value: Double, val tags: Map<String, String>) : LogEntry()
}

fun handleStatus(status: Status) {
    when (status) {
        is Status.Loading -> println("Loading...")
        is Status.OK -> println("Data received: ${status.data}")
        is Status.Error -> when (status.problem) {
            Status.Error.Problem.NETWORK -> println("Network issue")
            Status.Error.Problem.TIMEOUT -> println("Request timed out")
            Status.Error.Problem.UNKNOWN -> println("Unknown error occurred")
        }
    }
}

fun main() {
    println("=== Status Handling with Sealed and Enum Classes ===\n")

    // Basic example from the prompt
    println("1. Basic Example from Prompt:")
    println("-".repeat(40))

    val status1: Status = Status.Error(Status.Error.Problem.NETWORK)
    val status2: Status = Status.OK(listOf("Data1", "Data2"))
    val status3: Status = Status.Loading

    handleStatus(status1)
    handleStatus(status2)
    handleStatus(status3)

    println("\n" + "=".repeat(60) + "\n")

    // Understanding the structure
    println("2. Understanding the Structure:")
    println("-".repeat(40))

    println("Sealed class Status hierarchy:")
    println("• Status.Loading (object)")
    println("• Status.Error (data class)")
    println("  └── enum class Problem (nested)")
    println("      ├── NETWORK")
    println("      ├── TIMEOUT")
    println("      └── UNKNOWN")
    println("• Status.OK (data class)")

    println("\nKey concepts:")
    println("• Nested enum class: Problem is defined inside Error class")
    println("• Access pattern: Status.Error.Problem.NETWORK")
    println("• Type safety: Compiler knows all possible status types")
    println("• Pattern matching: Exhaustive when expressions")

    println("\n" + "=".repeat(60) + "\n")

    // Working with different statuses
    println("3. Working with Different Statuses:")
    println("-".repeat(40))

    val allStatuses: List<Status> = listOf(
        Status.Loading,
        Status.Error(Status.Error.Problem.TIMEOUT),
        Status.Error(Status.Error.Problem.UNKNOWN),
        Status.OK(listOf("A", "B", "C"))
    )

    println("Processing all statuses:")
    allStatuses.forEachIndexed { index, status ->
        println("\nStatus ${index + 1}:")
        handleStatus(status)
    }

    println("\n" + "=".repeat(60) + "\n")

    // Enhanced status handling
    println("4. Enhanced Status Handling:")
    println("-".repeat(40))

    fun getStatusDetails(status: Status): Map<String, Any> {
        return when (status) {
            is Status.Loading -> mapOf(
                "type" to "LOADING",
                "message" to "Please wait...",
                "canRetry" to false
            )

            is Status.OK -> mapOf(
                "type" to "SUCCESS",
                "dataSize" to status.data.size,
                "message" to "Received ${status.data.size} items"
            )

            is Status.Error -> {
                val errorMessage = when (status.problem) {
                    Status.Error.Problem.NETWORK -> "Check your internet connection"
                    Status.Error.Problem.TIMEOUT -> "Server took too long to respond"
                    Status.Error.Problem.UNKNOWN -> "An unexpected error occurred"
                }

                mapOf(
                    "type" to "ERROR",
                    "errorType" to status.problem.name,
                    "message" to errorMessage,
                    // ✅ FIX: use parentheses so it becomes Boolean inside the Pair
                    "canRetry" to (status.problem != Status.Error.Problem.UNKNOWN)
                )
            }
        }
    }

    println("Status details:")
    allStatuses.forEach { status ->
        val details = getStatusDetails(status)
        println("\n${status::class.simpleName}:")
        details.forEach { (key, value) -> println("  $key: $value") }
    }

    println("\n" + "=".repeat(60) + "\n")

    // Real-world API example
    println("5. Real-World API Handling Example:")
    println("-".repeat(40))

    fun <T> handleApiResult(result: ApiResult<T>) {
        when (result) {
            is ApiResult.Success -> {
                println("✅ Success!")
                println("Data: ${result.data}")
            }

            is ApiResult.Error -> {
                println("❌ Error:")
                when (val err = result.error) {
                    is ApiResult.Error.ApiError.NetworkError -> {
                        println("  Type: Network Error")
                        println("  Action: Check connection and retry")
                    }

                    is ApiResult.Error.ApiError.HttpError -> {
                        println("  Type: HTTP Error ${err.code}")
                        println("  Message: ${err.message}")
                        println("  Action: ${if (err.code >= 500) "Contact support" else "Fix request"}")
                    }

                    is ApiResult.Error.ApiError.ValidationError -> {
                        println("  Type: Validation Error")
                        println("  Field: ${err.field}")
                        println("  Issue: ${err.message}")
                        println("  Action: Correct the ${err.field} field")
                    }

                    is ApiResult.Error.ApiError.TimeoutError -> {
                        println("  Type: Timeout Error")
                        println("  Action: Increase timeout or retry")
                    }
                }
            }

            is ApiResult.Loading -> {
                println("⏳ Loading...")
                println("Please wait while we fetch your data")
            }
        }
    }

    println("API Result Examples:")

    val apiResults: List<ApiResult<List<String>>> = listOf(
        ApiResult.Success(listOf("User1", "User2", "User3")),
        ApiResult.Error(ApiResult.Error.ApiError.NetworkError),
        ApiResult.Error(ApiResult.Error.ApiError.HttpError(404, "Not Found")),
        ApiResult.Error(ApiResult.Error.ApiError.ValidationError("email", "Invalid email format")),
        ApiResult.Loading
    )

    apiResults.forEachIndexed { index, result ->
        println("\n--- API Result ${index + 1} ---")
        handleApiResult(result)
    }

    println("\n" + "=".repeat(60) + "\n")

    // Combining enums and sealed classes
    println("6. Combining Enums and Sealed Classes:")
    println("-".repeat(40))

    fun processLog(entry: LogEntry) {
        when (entry) {
            is LogEntry.Message -> {
                val symbol = when (entry.level) {
                    LogLevel.DEBUG -> "🔍"
                    LogLevel.INFO -> "ℹ️"
                    LogLevel.WARNING -> "⚠️"
                    LogLevel.ERROR -> "❌"
                    LogLevel.CRITICAL -> "🚨"
                }
                println("$symbol [${entry.level}] ${entry.text}")
            }

            is LogEntry.Error -> {
                println("💥 ERROR: ${entry.exception.message}")
                println("   Context: ${entry.context}")
            }

            is LogEntry.Metric -> {
                println("📊 METRIC: ${entry.name} = ${entry.value}")
                if (entry.tags.isNotEmpty()) println("   Tags: ${entry.tags}")
            }
        }
    }

    println("Log Processing:")
    val logs = listOf(
        LogEntry.Message(LogLevel.INFO, "Application started"),
        LogEntry.Message(LogLevel.WARNING, "Memory usage is high"),
        LogEntry.Error(RuntimeException("Database connection failed"), "User login attempt"),
        LogEntry.Metric("response_time", 145.67, mapOf("endpoint" to "/api/users", "method" to "GET"))
    )

    logs.forEach { processLog(it) }

    println("\n" + "=".repeat(60) + "\n")

    // Advanced pattern: Factory methods
    println("7. Advanced Pattern: Factory Methods:")
    println("-".repeat(40))

    fun createStatusFromResponse(responseCode: Int, data: List<String>? = null): Status {
        return when (responseCode) {
            in 200..299 -> if (data != null) Status.OK(data) else Status.Error(Status.Error.Problem.UNKNOWN)
            408 -> Status.Error(Status.Error.Problem.TIMEOUT)
            in 500..599 -> Status.Error(Status.Error.Problem.NETWORK)
            -1 -> Status.Loading
            else -> Status.Error(Status.Error.Problem.UNKNOWN)
        }
    }

    println("Creating statuses from response codes:")

    data class TestCase(val code: Int, val data: List<String>?)

    val testCases = listOf(
        TestCase(200, listOf("item1", "item2")),
        TestCase(408, null),
        TestCase(500, null),
        TestCase(-1, null),
        TestCase(404, null)
    )

    testCases.forEach { testCase ->
        val status = createStatusFromResponse(testCase.code, testCase.data)
        println("\nResponse ${testCase.code} → ${status::class.simpleName}")
        handleStatus(status)
    }
}
