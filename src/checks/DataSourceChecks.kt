package checks


import data_source.InMemoryTransactionsDataSource
import model.Transaction
import model.TransactionCategory
import model.TransactionType
import java.time.LocalDate
import java.util.*

fun main() {
    runAddDeleteUpdateDataSourceTransactionChecks()
    runGetAllGetByIdDataSourceChecks()
}

fun runAddDeleteUpdateDataSourceTransactionChecks() {
    val dataSource = InMemoryTransactionsDataSource()
    val transaction1 = Transaction(
        amount = 100.0,
        transactionCategory = TransactionCategory.FOOD,
        type = TransactionType.EXPENSES,
        date = LocalDate.now()
    )
    val transaction2 = Transaction(
        amount = 200.0,
        transactionCategory = TransactionCategory.RENT,
        type = TransactionType.EXPENSES,
        date = LocalDate.now()
    )

    check(
        message = "when Add transaction should return true",
        result = dataSource.addTransactions(transaction1),
        correctResult = true
    )

    check(
        message = "when Add existing transaction should return false",
        result = dataSource.addTransactions(transaction1),
        correctResult = false
    )

    check(
        message = "when Delete transaction should return true",
        result = dataSource.deleteTransaction(transaction1.id),
        correctResult = true
    )

    check(
        message = "when Delete non-existing transaction should return false",
        result = dataSource.deleteTransaction(transaction1.id),
        correctResult = false
    )


    dataSource.addTransactions(transaction2)
    check(
        message = "when Update transaction should return true",
        result = dataSource.updateTransaction(transaction2),
        correctResult = true
    )

    check(
        message = "when Update non-existing transaction should return false",
        result = dataSource.updateTransaction(transaction1),
        correctResult = false
    )
}

fun runGetAllGetByIdDataSourceChecks() {
    val dataSource = InMemoryTransactionsDataSource()
    val emptyDataSource = dataSource.getAllTransactions()
    val id = UUID.randomUUID()
    check(
        "when no transaction Should return empty list",
        emptyDataSource.isEmpty(),
        true
    )
    val tx = Transaction(
        id = id,
        amount = 100.0,
        transactionCategory = TransactionCategory.FOOD,
        type = TransactionType.EXPENSES,
        date = LocalDate.now()
    )
    dataSource.addTransactions(tx)
    check(
        "when transaction found Should return transaction with the same id",
        dataSource.getTransactionById(id)?.id == id,
        true
    )
    val fakeId = UUID.randomUUID()
    check(
        "when transaction not found Should return null",
        dataSource.getTransactionById(fakeId) == null,
        true
    )
    val tx1 = Transaction(
        id = UUID.randomUUID(),
        amount = 100.0,
        TransactionCategory.FOOD,
        type = TransactionType.EXPENSES,
        date = LocalDate.now()
    )
    val tx2 = Transaction(
        id = UUID.randomUUID(),
        amount = 200.0,
        TransactionCategory.FOOD,
        type = TransactionType.INCOME,
        date = LocalDate.now()
    )
    dataSource.addTransactions(tx1)
    dataSource.addTransactions(tx2)
    check(
        "when we have 3 transactions Should return list of size 3",
        dataSource.getAllTransactions().size == 3,
        true
    )
}
