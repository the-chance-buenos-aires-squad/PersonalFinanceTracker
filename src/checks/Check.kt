package checks

import data_source.InMemoryTransactionsDataSource
import model.Transaction
import model.TransactionCategory
import model.TransactionType
import java.time.LocalDate

fun main() {

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
        message = "Add transaction should return true",
        result = dataSource.addTransactions(transaction1),
        correctResult = true
    )

    check(
        message = "Add existing transaction should return false",
        result = dataSource.addTransactions(transaction1),
        correctResult = false
    )

    check(
        message = "Delete transaction should return true",
        result = dataSource.deleteTransaction(transaction1.id),
        correctResult = true
    )

    check(
        message = "Delete non-existing transaction should return false",
        result = dataSource.deleteTransaction(transaction1.id),
        correctResult = false
    )

    // Need to negotiation
    check(
        message = "Update transaction should return true",
        result = dataSource.updateTransaction(transaction2),
        correctResult = true
    )

    check(
        message = "Update non-existing transaction should return false",
        result = dataSource.updateTransaction(transaction1),
        correctResult = false
    )

}

fun  check(
    message:String,
    result : Boolean,
    correctResult : Boolean
){
    println(message)
    if (result == correctResult) println("successful") else println("fiald")
    println("--------")
}