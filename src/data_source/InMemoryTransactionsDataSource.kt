package data_source

import model.Transaction
import java.util.UUID



class InMemoryTransactionsDataSource :TransactionDataSource{
    val transactionList = mutableListOf<Transaction>()

    override fun addTransactions(transaction: Transaction): Boolean {
       return transactionList.add(transaction)



    }

    override fun deleteTransaction(id: UUID): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllTransactions(): List<Transaction> {
        return transactionList

    }

    override fun getTransactionById(id: UUID): Transaction? {
        return transactionList.find { it.id==id}

    }

    override fun updateTransaction(transaction: Transaction): Boolean {
        TODO("Not yet implemented")
    }

}

