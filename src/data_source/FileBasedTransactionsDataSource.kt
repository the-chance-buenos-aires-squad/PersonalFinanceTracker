package data_source

import model.Transaction
import model.toFileString
import util.TransactionsFile
import java.util.*

class FileBasedTransactionsDataSource(
    private val transactionsFile: TransactionsFile
) : TransactionDataSource {

    override fun addTransactions(transaction: Transaction): Boolean {
        return transactionsFile.appendToFile(transaction.toFileString())
    }

    override fun deleteTransaction(id: UUID): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getTransactionById(id: UUID): Transaction? {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(transaction: Transaction): Boolean {
        TODO("Not yet implemented")
    }
}