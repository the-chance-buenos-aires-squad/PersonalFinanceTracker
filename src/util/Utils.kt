package util

import model.Transaction
import model.TransactionCategory
import model.TransactionType
import model.toFileString
import java.time.LocalDate


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

fun transformTransactionsToTextLines(transactions: List<Transaction>): String {
    var textLines = ""
    transactions.forEach { transaction ->
        textLines += transaction.toFileString() + "\n"
    }
    return textLines
}

