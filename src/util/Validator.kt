package util


import model.TransactionCategory
import model.TransactionType
import java.time.Year
import java.util.*

class Validator {
    fun notOutOfRang(n: Int, rang: Int): Boolean {
        return (n in 1..rang)
    }

    fun monthIsValid(month: Int?): Boolean {
        return (month in 1..12)
    }

    fun yearIsValid(year: Int?): Boolean {
        val currentYear = Year.now().value
        return year != null && year in 1900..currentYear

    }

    fun amountIsValid(amount: String): Boolean {
        val number = amount.toDoubleOrNull()
        return number != null && number >= 0
    }

    fun getValidIndex(input: String?, listSize: Int): Int? {
        val index = input?.toIntOrNull()?.minus(1)
        if (index in 0 until listSize) {
            return index
        } else {
            return null
        }
    }

    fun getValidTransactionType(input: String?): TransactionType? {
        return when (input?.toIntOrNull()) {
            1 -> TransactionType.INCOME
            2 -> TransactionType.EXPENSES
            else -> null
        }
    }

    fun getValidCategory(input: String?): TransactionCategory? {
        val index = input?.toIntOrNull()
        if (index != null && index in 1..TransactionCategory.entries.size) {
            return TransactionCategory.entries[index - 1]
        } else {
            return null
        }
    }
}

fun String.toUUIDOrNull(): UUID? = runCatching { UUID.fromString(this) }.getOrNull()
