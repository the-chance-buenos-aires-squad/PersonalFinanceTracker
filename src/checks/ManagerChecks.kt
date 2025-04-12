package checks

import data_source.InMemoryTransactionsDataSource
import model.*
import manager.ReportManager
import manager.TransactionManager
import java.time.LocalDate
import java.util.*

fun main() {
    runBalanceChecks()
    runMonthlySummaryChecks()
    runGetAllGetByIdChecks()
    runAddDeleteUpdateTransactionChecks()
}

fun runBalanceChecks() {
    val dataSource = InMemoryTransactionsDataSource()
    val reportManager = ReportManager(dataSource)
    val transactions = listOf(
        Transaction(UUID.randomUUID(), 1000.0, TransactionCategory.SALARY, TransactionType.INCOME, LocalDate.now()),
        Transaction(
            UUID.randomUUID(),
            200.0,
            TransactionCategory.SALARY,
            TransactionType.EXPENSES,
            LocalDate.now()
        ),
        Transaction(UUID.randomUUID(), 500.0, TransactionCategory.SALARY, TransactionType.INCOME, LocalDate.now()),
        Transaction(UUID.randomUUID(), 100.0, TransactionCategory.SALARY, TransactionType.EXPENSES, LocalDate.now())
    )
    transactions.forEach {
        dataSource.addTransactions(it)
    }
    check(
        message = "when Income greater than expense should return positive value",
        result = reportManager.getBalance() == 1200.0,
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
        result = reportManager.getBalance() == 0.0,
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
        result = reportManager.getBalance() < 0.0,
        correctResult = false
    )
}

fun runMonthlySummaryChecks() {
    val dataSource = InMemoryTransactionsDataSource()
    val reportManager = ReportManager(dataSource)
    dataSource.addTransactions(
        Transaction(
            amount = 20000.0,
            type = TransactionType.INCOME,
            transactionCategory = TransactionCategory.SALARY,
            date = LocalDate.of(2025, 1, 1)
        )
    )
    dataSource.addTransactions(
        Transaction(
            amount = 1000.0,
            type = TransactionType.INCOME,
            transactionCategory = TransactionCategory.OTHER,
            date = LocalDate.of(2025, 1, 5)
        )
    )
    dataSource.addTransactions(
        Transaction(
            amount = 1000.0,
            type = TransactionType.INCOME,
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
    dataSource.addTransactions(
        Transaction(
            amount = 20000.0,
            type = TransactionType.INCOME,
            transactionCategory = TransactionCategory.SALARY,
            date = LocalDate.of(2025, 3, 1)
        )
    )
    dataSource.addTransactions(
        Transaction(
            amount = 1000.0,
            type = TransactionType.INCOME,
            transactionCategory = TransactionCategory.OTHER,
            date = LocalDate.of(2025, 3, 5)
        )
    )
    dataSource.addTransactions(
        Transaction(
            amount = 1000.0,
            type = TransactionType.INCOME,
            transactionCategory = TransactionCategory.OTHER,
            date = LocalDate.of(2025, 3, 10)
        )
    )
    dataSource.addTransactions(
        Transaction(
            amount = 8000.0,
            type = TransactionType.EXPENSES,
            transactionCategory = TransactionCategory.RENT,
            date = LocalDate.of(2025, 5, 14)
        )
    )
    dataSource.addTransactions(
        Transaction(
            amount = 500.0,
            type = TransactionType.EXPENSES,
            transactionCategory = TransactionCategory.TRANSPORT,
            date = LocalDate.of(2025, 5, 20)
        )
    )
    val memoryTransactionsDataSource = dataSource.getAllTransactions()
    check(
        "When Valid Month It Should return MonthlySummary successfully",
        reportManager.getMonthlySummaryReport(1) == MonthlySummary(
            totalIncome = 22000.0,
            totalExpense = 8500.0,
            incomeList = memoryTransactionsDataSource.filter {
                it.type == TransactionType.INCOME && it.date.monthValue == 1 && it.date.year == LocalDate.now().year
            },
            expenseList = memoryTransactionsDataSource.filter {
                it.type == TransactionType.EXPENSES && it.date.monthValue == 1 && it.date.year == LocalDate.now().year
            },
            highestIncomeCategory = TopCategory(20000.0, TransactionCategory.SALARY),
            highestExpenseCategory = TopCategory(8000.0, TransactionCategory.RENT)
        ),
        true
    )
    check(
        "When Valid Month but no expense" + "It Should return MonthlySummary empty expenseList and highestExpendCategory null",
        reportManager.getMonthlySummaryReport(3) == MonthlySummary(
            totalIncome = 22000.0,
            totalExpense = 0.0,
            incomeList = memoryTransactionsDataSource.filter {
                it.type == TransactionType.INCOME && it.date.monthValue == 3 && it.date.year == LocalDate.now().year
            },
            expenseList = listOf(),
            highestIncomeCategory = TopCategory(20000.0, TransactionCategory.SALARY),
            highestExpenseCategory = null
        ),
        true
    )
    check(
        "When Valid Month but no Income" + "It Should return MonthlySummary empty incomeList and highestIncomeCategory null",
        reportManager.getMonthlySummaryReport(5) == MonthlySummary(
            totalIncome = 0.0,
            totalExpense = 8500.0,
            incomeList = listOf(),
            expenseList = memoryTransactionsDataSource.filter {
                it.type == TransactionType.EXPENSES && it.date.monthValue == 5
                        && it.date.year == LocalDate.now().year
            },
            highestIncomeCategory = null,
            highestExpenseCategory = TopCategory(8000.0, TransactionCategory.RENT)
        ),
        true
    )
    check(
        "When there are no transactions in the specified month, it should return null",
        reportManager.getMonthlySummaryReport(2) == null,
        true
    )
    check(
        "When the user enters a negative month number, it should return null",
        reportManager.getMonthlySummaryReport(-1) == null,
        true
    )
    check(
        "When the user enters a month less than 1, it should return null",
        reportManager.getMonthlySummaryReport(0) == null,
        true
    )
    check(
        "When the user enters a month greater than 12, it should return null",
        reportManager.getMonthlySummaryReport(13) == null,
        true
    )
    check(
        "When the user enters a negative year, it should return null",
        reportManager.getMonthlySummaryReport(1, -1) == null,
        true
    )
    check(
        "When the specified year has no transactions but the month is valid, it should return null",
        reportManager.getMonthlySummaryReport(1, 2000) == null,
        true
    )
    check(
        "When the user enters a future year, it should return null",
        reportManager.getMonthlySummaryReport(1, 2030) == null,
        true
    )
}

fun runGetAllGetByIdChecks() {
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
        message = "when The number of transactions is 5 should return true",
        result = transactionManager.getAllTransactions().size == 5,
        correctResult = true
    )
    val emptyDataSource = InMemoryTransactionsDataSource()
    val secondTransaction = TransactionManager(emptyDataSource)
    check(
        message = "when transactions is empty should return true",
        result = secondTransaction.getAllTransactions().isEmpty(),
        correctResult = true
    )
    val retrievedTransaction = transactionManager.getTransactionById(UUID.randomUUID())
    check(
        message = "when Retrieving by invalid ID should return null",
        result = retrievedTransaction == null,
        correctResult = true
    )
}

fun runAddDeleteUpdateTransactionChecks() {
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
        " when Update transaction to system should return true  ",
        result = transactionManager.updateTransaction(transaction),
        correctResult = true
    )
    check(
        " When delete transaction to system should return true  ",
        result = transactionManager.deleteTransaction(transaction.id),
        correctResult = true
    )
}
