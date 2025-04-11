package model

import java.time.LocalDate
import java.util.*

data class Transaction(
    val id: UUID = UUID.randomUUID(),
    val amount: Double,
    val transactionCategory: TransactionCategory,
    val type: TransactionType,
    val date: LocalDate = LocalDate.now()
)

fun Transaction.toFileString(): String {
    return listOf(
        id.toString(),
        amount.toString(),
        transactionCategory.name,
        type.name,
        date.toString()
    ).joinToString("|")
}

fun Transaction.fromFileString(transactionText: String): Transaction {
    val parts = transactionText.split("|")
    return Transaction(
        id = UUID.fromString(parts[0]),
        amount = parts[1].toDouble(),
        transactionCategory = TransactionCategory.valueOf(parts[2]),
        type = TransactionType.valueOf(parts[3]),
        date = LocalDate.parse(parts[4])
    )
}
