package checks

import data_source.TransactionDataSource
import model.MonthlySummary
import model.Transaction
import model.TransactionCategory
import model.TransactionType
import repository.TransactionManager
import java.time.LocalDate
import java.util.*

fun main() {
    val someDemoTransaction = listOf(
        Transaction(
            amount = 20000.0,
            type = TransactionType.INCOME,
            transactionCategory = TransactionCategory.SALARY,
            date = LocalDate.of(2025, 1, 1)
        ),
        Transaction(
            amount = 1000.0,
            type = TransactionType.INCOME,
            transactionCategory = TransactionCategory.OTHER,
            date = LocalDate.of(2025, 1, 4)
        ),
        Transaction(
            amount = 8000.0,
            type = TransactionType.EXPENSES,
            transactionCategory = TransactionCategory.RENT,
            date = LocalDate.of(2025, 1, 5)
        ),
        Transaction(
            amount = 500.0,
            type = TransactionType.EXPENSES,
            transactionCategory = TransactionCategory.TRANSPORT,
            date = LocalDate.of(2025, 1, 20)
        ),
        Transaction(
            amount = 1000.0,
            type = TransactionType.INCOME,
            transactionCategory = TransactionCategory.OTHER,
            date = LocalDate.of(2025, 1, 31)
        )
    )
    val dataSource = object : TransactionDataSource {
        override fun addTransactions(transaction: Transaction): Boolean {
            TODO("Not yet implemented")
        }

        override fun deleteTransaction(id: UUID): Boolean {
            TODO("Not yet implemented")
        }

        override fun getAllTransactions(): List<Transaction> = someDemoTransaction
        override fun getTransactionById(id: UUID): Transaction {
            TODO("Not yet implemented")
        }

        override fun updateTransaction(transaction: Transaction): Boolean {
            TODO("Not yet implemented")
        }
    }
    val repository = TransactionManager(dataSource)
    check(
        "When Valid Month It Should return MonthlySummary successfully",
        repository.getMonthlySummaryReport(1),
        MonthlySummary(
            1,
            2025,
            22_000.0,
            8_500.0,
            13_500.0
        )
    )
    check(
        "When there are no transactions in the specified month, it should return null",
        repository.getMonthlySummaryReport(2),
        null
    )
    check(
        "When the user enters a negative month number, it should return null",
        repository.getMonthlySummaryReport(-1),
        null
    )
    check(
        "When the user enters a month less than 1, it should return null",
        repository.getMonthlySummaryReport(0),
        null
    )
    check(
        "When the user enters a month greater than 12, it should return null",
        repository.getMonthlySummaryReport(13),
        null
    )
    check(
        "When the user enters a negative year, it should return null",
        repository.getMonthlySummaryReport(1, -1),
        null
    )
    check(
        "When the specified year has no transactions but the month is valid, it should return null",
        repository.getMonthlySummaryReport(1, 2000),
        null
    )
    check(
        "When the user enters a future year, it should return null",
        repository.getMonthlySummaryReport(1, 2030),
        null
    )

}

fun <T> check(
    message: String,
    result: T,
    correctResult: T
) {
    println(message)
    if (result == correctResult) println("successful") else println("fiald")
    println("--------")
}
