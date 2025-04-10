package checks

import data_source.InMemoryTransactionsDataSource
import model.Transaction
import model.TransactionCategory
import model.TransactionType
import repository.TransactionManager
import java.time.LocalDate
import java.util.*

fun main() {
    val dataSource = InMemoryTransactionsDataSource()
    val transactionManager = TransactionManager(dataSource)
     dataSource.addTransactions(
        Transaction(
            amount = 20000.0,
            type = TransactionType.EXPENSES,
            transactionCategory = TransactionCategory.SALARY,
            date = LocalDate.of(2025, 1, 1)
        )
    )
    dataSource.addTransactions(
        Transaction(
            amount = 1000.0,
            type = TransactionType.EXPENSES,
            transactionCategory = TransactionCategory.OTHER,
            date = LocalDate.of(2025, 1, 5)
        )
    )
    dataSource.addTransactions(
        Transaction(
            amount = 1000.0,
            type = TransactionType.EXPENSES,
            transactionCategory = TransactionCategory.OTHER,
            date = LocalDate.of(2025, 1, 10)
        )
    )
    dataSource.addTransactions(
        Transaction(
            amount = 8000.0,
            type = TransactionType.EXPENSES,
            transactionCategory = TransactionCategory.RENT,
            date = LocalDate.of(2025, 1, 14)
        )
    )
    dataSource.addTransactions(
        Transaction(
            amount = 500.0,
            type = TransactionType.EXPENSES,
            transactionCategory = TransactionCategory.TRANSPORT,
            date = LocalDate.of(2025, 1, 20)
        )
    )




    check(
        message = "The number of transactions should be 3",
        result = transactionManager.getAllTransactions().size == 5,
        correctResult = true
    )
    check(
        message = "The length of transactions not enough",
        result = dataSource.getAllTransactions().size < 8,
        correctResult = true
    )
    val emptyDataSource= InMemoryTransactionsDataSource()
    val secondTransaction= TransactionManager(emptyDataSource)


    check(
        message = "when transactions is empty should return true",
        result = secondTransaction.getAllTransactions().isEmpty(),
        correctResult = true
    )








    val retrievedTransaction = dataSource.getTransactionById(UUID.randomUUID())

    check(
        message = "Retrieving by invalid ID should return null",
        result = retrievedTransaction == null,
        correctResult = true
    )

}

fun check(
    message: String,
    result: Boolean,
    correctResult: Boolean
) {
    println(message)
    if (result == correctResult) println("successful") else println("failed")
    println("--------")
}