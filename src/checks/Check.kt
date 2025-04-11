package checks

import data_source.InMemoryTransactionsDataSource
import model.Transaction
import model.TransactionCategory
import model.TransactionType
import repository.TransactionManager
import java.time.LocalDate
import java.util.*

fun main() {
    checkManagerGetAllGetById()
    runBalanceChecks()

}

fun  check(
    message:String,
    result : Boolean,
    correctResult : Boolean
)
{
    println(message)
    if (result == correctResult) println("successful") else println("failed")
    println("--------")
}