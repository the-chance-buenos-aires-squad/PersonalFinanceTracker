package checks

import data_source.DataSource
import domain.model.Category
import model.Transaction
import model.TransactionType
import repository.TransactionRepository
import java.time.LocalDate
import java.util.*

fun main() {
    val transactions = listOf(
        Transaction(UUID.randomUUID(),1000.0,Category.SALARY,TransactionType.INCOME, LocalDate.now()),
        Transaction(UUID.randomUUID(),200.0,Category.SALARY,TransactionType.EXPENSES, LocalDate.now()),
        Transaction(UUID.randomUUID(),500.0,Category.SALARY,TransactionType.INCOME, LocalDate.now()),
        Transaction(UUID.randomUUID(),100.0,Category.SALARY,TransactionType.EXPENSES, LocalDate.now())
    )
    val dataSource = DataSource()
    val balanceReport=TransactionRepository(dataSource).getBalanceReport(transactions)
    check(
        message = "Test getBalanceReport total income",
        result = balanceReport.contains("Total Income: 1500.0"),
        correctResult = true
    )

    check(
        message = "Test getBalanceReport total expense",
        result = balanceReport.contains("Total Expense: 300.0"),
        correctResult = true
    )
    check(
        message = "Test getBalanceReport return Balance",
        result = balanceReport.contains("Balance: 1200.0"),
        correctResult = true
    )
}

fun <T> check(
    message:String,
    result : T,
    correctResult : T
){
    println(message)
    if (result == correctResult) println("successful") else println("fiald")
    println("--------")
}