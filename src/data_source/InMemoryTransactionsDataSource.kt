package data_source

import model.Transaction
import model.TransactionCategory
import model.TransactionType
import java.time.LocalDate
import java.util.UUID

class InMemoryTransactionsDataSource : TransactionDataSource {
    private val transactionList = mutableListOf<Transaction>()

    override fun addTransactions(transaction: Transaction): Boolean {
        return if (transactionList.any { it.id == transaction.id }) {
            false
        } else {
            transactionList.add(transaction)
            true
        }
    }

    override fun deleteTransaction(id: UUID): Boolean {
        return transactionList.removeIf { it.id == id }
    }

    override fun updateTransaction(transaction: Transaction): Boolean {
        val indexOfExistingTransaction = transactionList.indexOfFirst { it.id == transaction.id  }
        return if (indexOfExistingTransaction != -1) {
            transactionList[indexOfExistingTransaction] = transaction
            true
        } else {
            false
        }
    }
    override fun getAllTransactions(): List<Transaction> {
        return transactionList
    }

    override fun getTransactionById(id: UUID): Transaction? {
        return transactionList.find { it.id == id }
    }

    override fun updateTransaction(transaction: Transaction): Boolean {
        TODO("Not yet implemented")
    }
}
