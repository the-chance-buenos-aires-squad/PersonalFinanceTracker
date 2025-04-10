package checks

import data_source.DataSource
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
   val dataSource = DataSource()
    val id = UUID.randomUUID()
    val tx = Transaction( id = id,amount = 100.0, category = Category.FOOD, type = TransactionType.EXPENSE, date = LocalDate.now())
    dataSource.addTransactions(tx)
    val result = dataSource.getTransactionById(id)
    check ("Should return transaction with the same id",result?.id==id,true)
 }

fun  checkGetByIdReturnsNullIfNotFound() {
    val dataSource = DataSource()
    val fakeId= UUID.randomUUID()
    val result = dataSource.getTransactionById(fakeId)
    check("Should return null if transaction not found", result==null,true)
}

fun checkGetAllReturnsCorrectTransaction() {
    val dataSource = DataSource()
    val tx1 = Transaction(id= UUID.randomUUID(),amount = 100.0,category = Category.FOOD, type = TransactionType.EXPENSE,date = LocalDate.now())
    val tx2 = Transaction(id = UUID.randomUUID(), amount = 200.0, category = Category.FOOD, type = TransactionType.INCOME,date = LocalDate.now())
    dataSource.addTransactions(tx1)
    dataSource.addTransactions(tx2)
    val result = dataSource.getAllTransactions()
    check( "Should return list of size 2",result.size ==2,true)
}

fun checkGetAllreturnsEmptyListIfNoTransaction() {
    val dataSource = DataSource()
    val result = dataSource.getAllTransactions()
    check( "Should return empty list when no transaction", result.isEmpty(),true)
}

