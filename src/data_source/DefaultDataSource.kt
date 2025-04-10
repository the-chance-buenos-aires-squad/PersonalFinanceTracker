package data_source

import model.Transaction
import java.util.*

interface DefaultDataSource{
    fun addTransactions(transaction: Transaction): Transaction
    fun deleteTransaction(id: UUID):Boolean
    fun getAllTransactions():List<Transaction>
    fun getTransactionById(id: UUID): Transaction?
    fun updateTransaction(transaction: Transaction): Transaction
}