package checks

import data_source.InMemoryTransactionsDataSource
import model.*
import repository.TransactionManager
import java.time.LocalDate

fun main() {

    val dataSource = InMemoryTransactionsDataSource()
    val memoryTransactionsDataSource = dataSource.getAllTransactions()
    val manger = TransactionManager(dataSource)
    check(
        "When Valid Month It Should return MonthlySummary successfully",
        manger.getMonthlySummaryReport(1),
        MonthlySummary(
            totalIncome = 22_000.0,
            totalExpense = 8_500.0,
            incomeList = memoryTransactionsDataSource.filter { it.type == TransactionType.INCOME },
            expenseList = memoryTransactionsDataSource.filter { it.type == TransactionType.EXPENSES },
            highestIncomeCategory = TopCategory(20000.0, TransactionCategory.SALARY),
            highestExpenseCategory = TopCategory(8000.0, TransactionCategory.RENT)
        )
    )
    check(
        "When Valid Month but no expense" +
                "It Should return MonthlySummary empty expenseList and highestExpendCategory null",
        manger.getMonthlySummaryReport(1),
        MonthlySummary(
            totalIncome = 22_000.0,
            totalExpense = 0.0,
            incomeList = memoryTransactionsDataSource.filter { it.type == TransactionType.INCOME },
            expenseList = listOf(),
            highestIncomeCategory = TopCategory(20000.0, TransactionCategory.SALARY),
            highestExpenseCategory = null
        )
    )

    check(
        "When there are no transactions in the specified month, it should return null",
        manger.getMonthlySummaryReport(2),
        null
    )
    check(
        "When the user enters a negative month number, it should return null",
        manger.getMonthlySummaryReport(-1),
        null
    )
    check(
        "When the user enters a month less than 1, it should return null",
        manger.getMonthlySummaryReport(0),
        null
    )
    check(
        "When the user enters a month greater than 12, it should return null",
        manger.getMonthlySummaryReport(13),
        null
    )
    check(
        "When the user enters a negative year, it should return null",
        manger.getMonthlySummaryReport(1, -1),
        null
    )
    check(
        "When the specified year has no transactions but the month is valid, it should return null",
        manger.getMonthlySummaryReport(1, 2000),
        null
    )
    check(
        "When the user enters a future year, it should return null",
        manger.getMonthlySummaryReport(1, 2030),
        null
    )

}

fun <T> check(
    message: String,
    result: T,
    correctResult: T
) {
    println(message)
    if (result == correctResult) println("successful") else println("fiald")
    println("--------")
}
