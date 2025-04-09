package repository

import data_source.DefaultDataSource
import model.Transaction
import model.TransactionType

class TransactionRepository (val dataSource: DefaultDataSource){
    fun getBalanceReport(transactions: List<Transaction>): String {
//        val transactions = getAllTransactions()
        val income=transactions.filter { it.type ==TransactionType.INCOME}.sumOf { it.amount }
        val expense=transactions.filter { it.type ==TransactionType.EXPENSES}.sumOf { it.amount }
        val balance=income-expense
        return """
            ==== Balance Report ====
            Total Income: $income
            Total Expense: $expense
            Balance: $balance
        """.trimIndent()
    }


}