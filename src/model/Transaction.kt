package model

import java.time.LocalDate
import java.util.*

data class Transaction(
    val id: UUID = UUID.randomUUID(),
    val amount: Double,
    val transactionCategory: TransactionCategory,
    val type: TransactionType,
    val date: LocalDate
)