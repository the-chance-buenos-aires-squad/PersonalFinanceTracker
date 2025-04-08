package domain.model

import java.time.LocalDate
import java.util.*

data class Transaction(
    val id: UUID = UUID.randomUUID(),   // Automatically generate a unique ID
    val amount: Double,                // The amount of the transaction
    val category: Category,            // Category of the transaction (e.g., Food, Rent, etc.)
    val type: TransactionType,         // Type of the transaction (Income or Expense)
    val date: LocalDate                // Date of the transaction
)