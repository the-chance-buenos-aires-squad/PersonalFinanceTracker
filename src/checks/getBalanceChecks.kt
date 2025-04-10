package checks

import data_source.InMemoryTransactionsDataSource
import model.Transaction
import model.TransactionCategory
import model.TransactionType
import repository.TransactionManager
import java.time.LocalDate
import java.util.*


fun runBalanceChecks() {
    val transactions = listOf(
        Transaction(UUID.randomUUID(), 1000.0, TransactionCategory.SALARY, TransactionType.INCOME, LocalDate.now()),
        Transaction(UUID.randomUUID(), 200.0, TransactionCategory.SALARY, TransactionType.EXPENSES, LocalDate.now()),
        Transaction(UUID.randomUUID(), 500.0, TransactionCategory.SALARY, TransactionType.INCOME, LocalDate.now()),
        Transaction(UUID.randomUUID(), 100.0, TransactionCategory.SALARY, TransactionType.EXPENSES, LocalDate.now())
    )

    val dataSource = InMemoryTransactionsDataSource()
    val transactionManager = TransactionManager(dataSource)
    for (data in transactions) {
        dataSource.addTransactions(data)
    }
    check(
        message = "when Income greater than expense",
        result = transactionManager.getBalance() == 1200.0,
        correctResult = true
    )

    dataSource.addTransactions(
        Transaction(
            UUID.randomUUID(),
            1200.0,
            TransactionCategory.SALARY,
            TransactionType.EXPENSES,
            LocalDate.now()
        )
    )
    check(
        message = "when income equal than expense it will return zero",
        result = transactionManager.getBalance() == 0.0,
        correctResult = true
    )
    dataSource.addTransactions(
        Transaction(
            UUID.randomUUID(),
            200.0,
            TransactionCategory.SALARY,
            TransactionType.EXPENSES,
            LocalDate.now()
        )
    )
    check(
        message = "when balance less than zero it's invalid value",
        result = transactionManager.getBalance() < 0.0,
        correctResult = false
    )
}



