package repository

import data_source.TransactionDataSource
import model.Transaction
import model.TransactionType

class TransactionManager(private val dataSource: TransactionDataSource) {
// add delete update


// getAll getBy all data source functions


// getMonthlySummaryReport


    fun getBalance(): Double {
        val transactions = dataSource.getAllTransactions()
        val income = transactions.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val expense = transactions.filter { it.type == TransactionType.EXPENSES }.sumOf { it.amount }
        val balance = income - expense
        return balance
    }

}