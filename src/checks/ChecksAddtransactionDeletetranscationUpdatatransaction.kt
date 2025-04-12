package checks

import data_source.InMemoryTransactionsDataSource
import model.Transaction
import model.TransactionCategory
import model.TransactionType
import repository.TransactionManager
import java.time.LocalDate


fun main() {

    val dataSource = InMemoryTransactionsDataSource()
    val transactionManager = TransactionManager(dataSource)
    val index = 0
    val originalTransaction = transactionManager.getAllTransactions()[index].copy() // snapshot before change
    val updatedTransaction = originalTransaction.copy(amount = 200.0)
    val result = transactionManager.updateTransaction(index, updatedTransaction)
    val afterUpdateTransaction = transactionManager.getAllTransactions()[index]

    val transaction = Transaction(
        amount = 100.0,
        transactionCategory = TransactionCategory.FOOD,
        type = TransactionType.EXPENSES,
        date = LocalDate.now() // Use the current date
    )
    check(
        " When Add transaction to system should return true",
        result = transactionManager.addTransaction(transaction),
        correctResult = true
    )
    check(
        "when update transaction it should return true",
        result = result,
        correctResult = true
    )
    check(
        "the transaction should be changed after update",
        result = originalTransaction != afterUpdateTransaction,
        correctResult = true
    )
    check(
        " When delete transaction to system should return true  ",
        result = transactionManager.deleteTransaction(transaction.id),
        correctResult = true
    )

}


