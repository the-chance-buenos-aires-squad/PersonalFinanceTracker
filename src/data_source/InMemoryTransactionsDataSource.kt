package data_source

import model.Transaction
import java.util.UUID

class InMemoryTransactionsDataSource :TransactionDataSource{
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

    // Need to negotiation
    override fun updateTransaction(transaction: Transaction): Boolean {
        val existingTransaction = transactionList.find { it.id == transaction.id }
        return if (existingTransaction != null) {
            val index = transactionList.indexOf(existingTransaction)
            transactionList[index] = transaction
            true
        } else {
            false
        }
    }
    override fun getAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getTransactionById(id: UUID): Transaction? {
        TODO("Not yet implemented")
    }


}


