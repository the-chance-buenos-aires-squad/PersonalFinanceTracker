package repository

import data_source.TransactionDataSource
import model.MonthlySummary
import model.TopCategory
import model.Transaction
import model.TransactionType
import java.time.LocalDate
import java.util.*

class TransactionManager(private val dataSource: TransactionDataSource) {

    fun getAllTransactions(): List<Transaction> {
        return dataSource.getAllTransactions()
    }


    fun getTransactionById(id: UUID): Transaction? {
        return dataSource.getTransactionById(id)
    }

    fun addTransaction(transaction: Transaction): Boolean = dataSource.addTransactions(transaction)
    fun deleteTransaction(id: UUID): Boolean = dataSource.deleteTransaction(id)


    fun updateTransaction(newTransaction: Transaction): Boolean {
        return dataSource.updateTransaction(newTransaction)
    }

}

