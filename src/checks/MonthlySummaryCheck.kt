package checks

import data_source.InMemoryTransactionsDataSource
import model.*
import repository.TransactionManager
import java.time.LocalDate

fun main() {
    val dataSource = InMemoryTransactionsDataSource()
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
    val manger = TransactionManager(dataSource)
    val memoryTransactionsDataSource = dataSource.getAllTransactions()
    check(
        "When Valid Month It Should return MonthlySummary successfully",
        manger.getMonthlySummaryReport(1) == MonthlySummary(
            totalIncome = 22000.0,
            totalExpense = 8500.0,
            incomeList = memoryTransactionsDataSource.filter { it.type == TransactionType.INCOME },
            expenseList = memoryTransactionsDataSource.filter { it.type == TransactionType.EXPENSES },
            highestIncomeCategory = TopCategory(20000.0, TransactionCategory.SALARY),
            highestExpenseCategory = TopCategory(8000.0, TransactionCategory.RENT)
        ), true

    )
    check(
        "When Valid Month but no expense" +
                "It Should return MonthlySummary empty expenseList and highestExpendCategory null",
        manger.getMonthlySummaryReport(1) == MonthlySummary(
            totalIncome = 22000.0,
            totalExpense = 0.0,
            incomeList = memoryTransactionsDataSource.filter { it.type == TransactionType.INCOME },
            expenseList = listOf(),
            highestIncomeCategory = TopCategory(20000.0, TransactionCategory.SALARY),
            highestExpenseCategory = null
        ),
        true

    )
    check(
        "When Valid Month but no Income" +
                "It Should return MonthlySummary empty incomeList and highestIncomeCategory null",
        manger.getMonthlySummaryReport(1) == MonthlySummary(
            totalIncome = 0.0,
            totalExpense = 8500.0,
            incomeList = listOf(),
            expenseList = memoryTransactionsDataSource.filter { it.type == TransactionType.EXPENSES },
            highestIncomeCategory = null,
            highestExpenseCategory = TopCategory(8000.0, TransactionCategory.RENT)
        ),
        true

    )
    check(
        "When there are no transactions in the specified month, it should return null",
        manger.getMonthlySummaryReport(2) == null,
        true
    )
    check(
        "When the user enters a negative month number, it should return null",
        manger.getMonthlySummaryReport(-1) == null,
        true
    )
    check(
        "When the user enters a month less than 1, it should return null",
        manger.getMonthlySummaryReport(0) == null,
        true
    )
    check(
        "When the user enters a month greater than 12, it should return null",
        manger.getMonthlySummaryReport(13) == null,
        true
    )
    check(
        "When the user enters a negative year, it should return null",
        manger.getMonthlySummaryReport(1, -1) == null,
        true
    )
    check(
        "When the specified year has no transactions but the month is valid, it should return null",
        manger.getMonthlySummaryReport(1, 2000) == null,
        true
    )
    check(
        "When the user enters a future year, it should return null",
        manger.getMonthlySummaryReport(1, 2030) == null,
        true
    )
}
