package data_source

import model.Transaction
import java.util.UUID



class DataSource : data_source.DefaultDataSource{
    val transactionList = mutableListOf<Transaction>()

    override fun addTransactions(transaction: Transaction): Transaction {
        TODO("Not yet implemented")

    }

    override fun deleteTransaction(id: UUID): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getTransactionById(id: UUID): Transaction {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(transaction: Transaction): Transaction {
        TODO("Not yet implemented")
    }

}


