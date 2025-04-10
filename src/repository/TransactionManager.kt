package repository

import data_source.TransactionDataSource
import model.Transaction
import java.util.*

class TransactionManager (val dataSource: TransactionDataSource){


fun addTransaction(transaction : Transaction) : Boolean = dataSource.addTransactions(transaction)

fun deleteTransaction(id : UUID?) : Boolean {
    if(id != null) {

        return dataSource.deleteTransaction(id)
    }
    else{
        return  false
    }
}
fun updateTransaction(transaction : Transaction) : Boolean = dataSource.updateTransaction(transaction)

}