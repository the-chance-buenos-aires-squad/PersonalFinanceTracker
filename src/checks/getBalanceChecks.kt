package checks

import data_source.InMemoryTransactionsDataSource
import repository.TransactionManager


fun runBalanceChecks() {
    val dataSource = InMemoryTransactionsDataSource()
    val transactionManager = TransactionManager(dataSource).getBalance()
    check(
        message = "Balance Greater Than Zero",
        result = transactionManager >= 0,
        correctResult = true
    )
    check(
        message = "Invalid Balance return false",
        result = transactionManager < 0,
        correctResult = false
    )
}



