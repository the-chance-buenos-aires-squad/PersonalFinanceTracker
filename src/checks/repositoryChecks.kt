package checks

import data_source.DefaultDataSource
import domain.model.Category
import model.MonthlySummary
import model.Transaction
import model.TransactionType
import repository.TransactionRepository
import java.time.LocalDate
import java.time.Month

fun main() {

    val someDemoTransaction = listOf(
        Transaction(
            amount = 20000.0,
            type = TransactionType.INCOME,
            category = Category.SALARY,
            date = LocalDate.of(2025,1,1)
        ),
        Transaction(
            amount = 1000.0,
            type = TransactionType.INCOME,
            category = Category.OTHER,
            date = LocalDate.of(2025, 1, 4)
        ),
        Transaction(
            amount = 8000.0,
            type = TransactionType.EXPENSES,
            category = Category.RENT,
            date = LocalDate.of(2025, 1, 5)
        ),
        Transaction(
            amount = 500.0,
            type = TransactionType.EXPENSES,
            category = Category.TRANSPORT,
            date = LocalDate.of(2025, 1, 20)
        ),
        Transaction(
            amount = 1000.0,
            type = TransactionType.INCOME,
            category = Category.OTHER,
            date = LocalDate.of(2025, 1, 31)
        )
    )

    val dataSource = DefaultDataSource;
    val repository = TransactionRepository(dataSource)

    check("check january Month Summary",
        repository.getMonthlySummaryReport(1),
        MonthlySummary(1,
            2025,
            22_000.0,
            8_500.0,
            13_500.0)
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