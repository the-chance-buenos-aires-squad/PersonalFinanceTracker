package data_source

import model.Transaction
import model.TransactionCategory
import model.TransactionType
import java.time.LocalDate
import java.util.UUID

class InMemoryTransactionsDataSource :TransactionDataSource{
    private val transactionList = mutableListOf<Transaction>()
    override fun addTransactions(transaction: Transaction): Boolean {
        TODO("Not yet implemented")

    }
    override fun deleteTransaction(id: UUID): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getTransactionById(id: UUID): Transaction? {
        TODO()
    }

    override fun updateTransaction(transaction: Transaction): Boolean {
        TODO("Not yet implemented")
    }


}


