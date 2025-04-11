package repository

import data_source.TransactionDataSource
import model.Transaction
import java.util.*

public class TransactionManager (val dataSource: TransactionDataSource){


    fun addTransaction(transaction: Transaction): Boolean{
        TODO()
    }

// getAll getBy all data source functions


    fun getAllTransactions(): List<Transaction> {
       TODO()
    }
    fun getTransactionById(id: UUID): Transaction?{
      TODO()
    }


// getBalanceReport

}

