package checks

import data_source.InMemoryTransactionsDataSource
import model.Transaction
import model.TransactionCategory
import model.TransactionType
import java.time.LocalDate
import java.util.UUID
import javax.sql.DataSource

fun main() {
    checkGetByIdReturnsCorrectTransaction()
    checkGetByIdReturnsNullIfNotFound()
    checkGetAllReturnsCorrectTransaction()
    checkGetAllreturnsEmptyListIfNoTransaction()
}

fun checkGetByIdReturnsCorrectTransaction() {
    val dataSource = InMemoryTransactionsDataSource()
    val id = UUID.randomUUID()
    val tx = Transaction( id = id,amount = 100.0, transactionCategory = TransactionCategory.FOOD, type = TransactionType.EXPENSES, date = LocalDate.now())
    dataSource.addTransactions(tx)
    val result = dataSource.getTransactionById(id)
    check ("Should return transaction with the same id",result?.id==id,true)
}

fun  checkGetByIdReturnsNullIfNotFound() {
    val dataSource = InMemoryTransactionsDataSource()
    val fakeId= UUID.randomUUID()
    val result = dataSource.getTransactionById(fakeId)
    check("Should return null if transaction not found", result==null,true)
}

fun checkGetAllReturnsCorrectTransaction() {
    val dataSource = InMemoryTransactionsDataSource()
    val tx1 = Transaction(id= UUID.randomUUID(),amount = 100.0,TransactionCategory.FOOD, type = TransactionType.EXPENSES,date = LocalDate.now())
    val tx2 = Transaction(id = UUID.randomUUID(), amount = 200.0, TransactionCategory.FOOD, type = TransactionType.INCOME,date = LocalDate.now())
    dataSource.addTransactions(tx1)
    dataSource.addTransactions(tx2)
    val result = dataSource.getAllTransactions()
    check( "Should return list of size 2",result.size ==2,true)
}

fun checkGetAllreturnsEmptyListIfNoTransaction() {
    val dataSource = InMemoryTransactionsDataSource()
    val result = dataSource.getAllTransactions()
    check( "Should return empty list when no transaction", result.isEmpty(),true)
}
