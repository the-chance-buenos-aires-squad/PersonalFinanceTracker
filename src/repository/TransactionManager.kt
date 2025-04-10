package repository

import data_source.TransactionDataSource
import model.Transaction
import java.util.*

class TransactionManager (val dataSource: TransactionDataSource){


fun addTransaction(transaction : Transaction) : Boolean = dataSource.addTransactions(transaction)
fun deleteTransaction(id : UUID) : Boolean = dataSource.deleteTransaction(id)
fun updateTransaction(transaction : Transaction) : Boolean = dataSource.updateTransaction(transaction)

}