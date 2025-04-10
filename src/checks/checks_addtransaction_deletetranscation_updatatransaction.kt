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


    check(" Adding transaction to system , return true  ",
        result = transactionManager.addTransaction(transaction),
        correctResult = true)

    check(" Deleting transaction to system , return true  ",
        result = transactionManager.deleteTransaction(transaction),
        correctResult = true)

    check(" Updating transaction to system , return true  ",
        result = transactionManager.updateTransaction(transaction),
        correctResult = true)


}


