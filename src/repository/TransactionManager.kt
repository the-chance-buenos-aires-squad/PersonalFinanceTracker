package repository

import data_source.TransactionDataSource
import model.Transaction
import java.util.*

public class TransactionManager (val dataSource: TransactionDataSource){


    fun addTransaction(transaction: Transaction): Boolean{
        return dataSource.addTransactions(transaction)
    }

// getAll getBy all data source functions


    fun getAllTransactions(): List<Transaction> {
        return dataSource.getAllTransactions()
    }
    fun getTransactionById(id: UUID): Transaction?{
        return dataSource.getTransactionById(UUID.randomUUID())
    }


// getBalanceReport

}

