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
        " When delete transaction to system should return true  ",
        result = transactionManager.deleteTransaction(transaction.id),
        correctResult = true
    )

    check(
        " when Update transaction to system should return true  ",
        result = transactionManager.updateTransaction(transaction),
        correctResult = true
    )


}


