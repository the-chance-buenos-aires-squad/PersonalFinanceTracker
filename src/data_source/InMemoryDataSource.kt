package data_source

import model.Transaction
import java.util.UUID



class DataSource : data_source.DefaultDataSource{
    val transactionList = mutableListOf<Transaction>()

    override fun addTransactions(transaction: Transaction): Transaction {
        transactionList.add(transaction)
        return transaction


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

    override fun updateTransaction(transaction: Transaction): Transaction {
        TODO("Not yet implemented")
    }

}


