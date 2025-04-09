package checks

import domain.model.Category
import model.Transaction
import model.TransactionType
import java.time.LocalDate
import java.util.UUID

fun main() {
    checkGetByIdReturnsCorrectTransaction()
    checkGetByIdReturnsNullIfNotFound()
    checkGetAllReturnsCorrectTransaction()
    checkGetAllreturnsEmptyListIfNoTransaction()
}

fun checkGetByIdReturnsCorrectTransaction() {
   val transactionList = mutableListOf<Transaction>()
    val id = UUID.randomUUID()
    val tx = Transaction( id = id,amount = 100.0, category = Category.FOOD, type = TransactionType.EXPENSE, date = LocalDate.now())
    transactionList.add(tx)
    val result = transactionList.find { it.id == id}
    check ("Should return transaction with the same id",result?.id==id,true)
 }

fun  checkGetByIdReturnsNullIfNotFound() {
    val transactionList = mutableListOf<Transaction>()
    val tx = Transaction( id = UUID.randomUUID(),amount = 100.0,category = Category.FOOD, type = TransactionType.EXPENSE,   date = LocalDate.now())
    transactionList.add(tx)
    val result = transactionList.find {it.id.toString()=="99"}
    check("Should return null if transaction not found", result==null,true)
}

fun checkGetAllReturnsCorrectTransaction() {
    val transactionList = mutableListOf<Transaction>()
    val tx1 = Transaction(id= UUID.randomUUID(),amount = 100.0,category = Category.FOOD, type = TransactionType.EXPENSE,date = LocalDate.now())
    val tx2 = Transaction(id = UUID.randomUUID(), amount = 200.0, category = Category.FOOD, type = TransactionType.INCOME,date = LocalDate.now())
    transactionList.add(tx1)
    transactionList.add(tx2)
    val result = transactionList
    check( "Should return list of size 2",result.size ==2,true)
}

fun checkGetAllreturnsEmptyListIfNoTransaction() {
    val transactionList = mutableListOf<Transaction>()
    val result = transactionList
    check( "Should return empty list when no transaction", transactionList.isEmpty(),true)
}

