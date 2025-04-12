package data_source

import model.Transaction
import model.fromFileString
import model.toFileString
import util.TransactionsFileManager
import util.transformTransactionsToTextLines
import java.util.*

class FileBasedTransactionsDataSource(
    private val transactionsFileManager: TransactionsFileManager
) : TransactionDataSource {

    override fun addTransactions(transaction: Transaction): Boolean {
        return transactionsFileManager.appendToFile(transaction.toFileString())
    }

    override fun deleteTransaction(id: UUID): Boolean {
        val transactions = getAllTransactions().toMutableList()
        val indexOfTransaction = transactions.indexOfFirst { it.id == id }
        if(indexOfTransaction == -1) return false

        transactions.removeAt(indexOfTransaction)
        val transactionsTextLines = transformTransactionsToTextLines(transactions)
        return transactionsFileManager.writeToFile(transactionsTextLines)
    }

    override fun getAllTransactions(): List<Transaction> {
        return transactionsFileManager.readLinesFromFile().map { transactionText -> transactionText.fromFileString() }
    }

    override fun getTransactionById(id: UUID): Transaction? {
        val transactions = getAllTransactions()
        return transactions.find { it.id == id }
    }

    override fun updateTransaction(transaction: Transaction): Boolean {
        val transactions = getAllTransactions().toMutableList()
        val indexOfTransaction = transactions.indexOfFirst { it.id == transaction.id }
        if (indexOfTransaction == -1) return false

        transactions[indexOfTransaction] = transaction
        val transactionsTextLines = transformTransactionsToTextLines(transactions)
        return transactionsFileManager.writeToFile(transactionsTextLines)
    }
}