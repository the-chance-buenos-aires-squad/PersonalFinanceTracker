package util

import model.TransactionCategory
import model.TransactionType


fun getValidTransactionTypeFromInput(input: String?): TransactionType? {
    return when (input?.toIntOrNull()) {
        1 -> TransactionType.INCOME
        2 -> TransactionType.EXPENSES
        else -> null
    }
}

fun getValidCategoryFromInput(input: String?): TransactionCategory? {
    val index = input?.toIntOrNull()
    if (index != null && index in 1..TransactionCategory.entries.size) {
        return TransactionCategory.entries[index - 1]
    } else {
        return null
    }
}

