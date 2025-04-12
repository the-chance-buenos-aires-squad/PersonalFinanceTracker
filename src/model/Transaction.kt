package model

import java.time.LocalDate
import java.util.*

data class Transaction(
    val id: UUID = UUID.randomUUID(),
    var amount: Double,
    var transactionCategory: TransactionCategory,
    var type: TransactionType,
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

fun String.fromFileString(): Transaction {
    val parts = this.split("|")
    return Transaction(
        id = UUID.fromString(parts[0]),
        amount = parts[1].toDouble(),
        transactionCategory = TransactionCategory.valueOf(parts[2]),
        type = TransactionType.valueOf(parts[3]),
        date = LocalDate.parse(parts[4])
    )
}
