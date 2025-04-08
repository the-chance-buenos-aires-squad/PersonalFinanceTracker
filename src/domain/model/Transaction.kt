package domain.model

import java.time.LocalDate
import java.util.*

data class Transaction(
    val id: UUID = UUID.randomUUID(),
    val amount: Double,
    val category: Category,
    val type: TransactionType,
    val date: LocalDate
)