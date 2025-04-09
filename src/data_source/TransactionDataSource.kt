package data_source

import model.Transaction
import java.util.*

interface TransactionDataSource{
    fun addTransactions(transaction: Transaction): Boolean
    fun deleteTransaction(id: UUID):Boolean
    fun getAllTransactions():List<Transaction>
    fun getTransactionById(id: UUID): Transaction?
    fun updateTransaction(transaction: Transaction): Boolean
}