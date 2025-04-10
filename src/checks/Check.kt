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
    val retrievedTransaction = dataSource.getTransactionById(UUID.randomUUID())

    val transaction1 = Transaction(
        id = UUID.randomUUID(),
        date = LocalDate.now(),
        transactionCategory = TransactionCategory.FOOD,
        type = TransactionType.EXPENSES,
        amount = 50.0
    )

    check(
        message = "The number of transactions should be 3",
        result = transactionManager.getAllTransactions().size == 3,
        correctResult = true
    )
    check(
        message = "The user does not do all transactions",
        result = dataSource.getAllTransactions().size < 5,
        correctResult = true
    )
    check(
        message = "No transactions should be retrieved",
        result = transactionManager.getAllTransactions().isEmpty(),
        correctResult = false
    )


    val transaction = Transaction(
        id = UUID.randomUUID(),
        date = LocalDate.now(),
        transactionCategory = TransactionCategory.FOOD,
        type = TransactionType.EXPENSES,
        amount = 50.0
    )


    check(
        message = "The transaction should be retrieved by its ID",
        result = retrievedTransaction == transaction,
        correctResult = true
    )

    val nonExistentId = UUID.randomUUID()
    transactionManager.getTransactionById(nonExistentId)
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