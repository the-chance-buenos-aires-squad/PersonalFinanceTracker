package repository

import data_source.TransactionDataSource
import model.MonthlySummary
import model.TopCategory
import model.Transaction
import model.TransactionType
import java.time.LocalDate
import java.util.*

class TransactionManager(private val dataSource: TransactionDataSource) {

    fun getMonthlySummaryReport(month: Int, year: Int = LocalDate.now().year): MonthlySummary? {
        val allTransactions = dataSource.getAllTransactions()
        val monthlyTransaction = allTransactions.filter { it.date.monthValue == month && it.date.year == year }
        if (monthlyTransaction.isEmpty()) return null
        var totalIncome = 0.0
        var totalExpense = 0.0
        val incomeList = mutableListOf<Transaction>()
        val expanseList = mutableListOf<Transaction>()
        allTransactions.forEach { it ->
            if (it.date.monthValue == month) {
                if (it.type == TransactionType.INCOME) {
                    incomeList.add(it)
                    totalIncome += it.amount
                } else {
                    expanseList.add(it)
                    totalExpense += it.amount
                }
            }
        }
        val highestIncome: Transaction? = incomeList.maxByOrNull { it.amount }
        val highestExpense: Transaction? = expanseList.maxByOrNull { it.amount }
        var topIncomeCategory: TopCategory? = null
        var topExpenseCategory: TopCategory? = null
        if (highestIncome != null) {
            topIncomeCategory = TopCategory(highestIncome.amount, highestIncome.transactionCategory)
        }
        if (highestExpense != null) {
            topExpenseCategory = TopCategory(highestExpense.amount, highestExpense.transactionCategory)
        }
        return MonthlySummary(
            totalIncome = totalIncome,
            totalExpense = totalExpense,
            incomeList = incomeList,
            expenseList = expanseList,
            highestIncomeCategory = topIncomeCategory,
            highestExpenseCategory = topExpenseCategory
        )
    }

    fun getAllTransactions(): List<Transaction> {
        return dataSource.getAllTransactions()
    }


    fun getTransactionById(id: UUID): Transaction? {
        return dataSource.getTransactionById(id)
    }

    fun addTransaction(transaction: Transaction): Boolean = dataSource.addTransactions(transaction)
    fun deleteTransaction(id: UUID): Boolean = dataSource.deleteTransaction(id)


    fun updateTransaction(newTransaction: Transaction): Boolean {
        return dataSource.updateTransaction(newTransaction)
    }

    fun getBalance(): Double {
        val transactions = dataSource.getAllTransactions()
        val income = transactions.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val expense = transactions.filter { it.type == TransactionType.EXPENSES }.sumOf { it.amount }
        val balance = income - expense
        return balance
    }

}

