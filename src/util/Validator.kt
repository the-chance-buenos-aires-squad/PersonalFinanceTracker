package util


import model.TransactionCategory
import model.TransactionType
import java.time.Year
import java.util.*

class Validator {
    fun isInRange(n: Int, rang: Int): Boolean {
        return (n in 1..rang)
    }

    fun isValidMonth(month: Int?): Boolean {
        return (month in 1..12)
    }

    fun isValidYear(year: Int?): Boolean {
        val currentYear = Year.now().value
        return year != null && year in 1900..currentYear

    }

    fun isValidAmount(amount: String): Boolean {
        val number = amount.toDoubleOrNull()
        return number != null && number >= 0
    }

    fun getValidIndexFromInput(input: String?, listSize: Int): Int? {
        val index = input?.toIntOrNull()?.minus(1)
        if (index in 0 until listSize) {
            return index
        } else {
            return null
        }
    }
}

fun String.toUUIDOrNull(): UUID? = runCatching { UUID.fromString(this) }.getOrNull()
