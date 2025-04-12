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