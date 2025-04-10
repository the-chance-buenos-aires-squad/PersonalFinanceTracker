package repository

import data_source.TransactionDataSource
import model.MonthlySummary
import model.TopCategory
import model.Transaction
import model.TransactionType
import java.time.LocalDate

class TransactionManager(val dataSource: TransactionDataSource) {
// add delete update


// getAll getBy all data source functions

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
        val highestIncome = incomeList.maxBy { it.amount }
        val highestExpense: Transaction? = null
        if (expanseList.isNotEmpty()) expanseList.maxBy { it.amount }
        val topIncomeCategory = TopCategory(highestIncome.amount, highestIncome.transactionCategory)
        var topExpenseCategory: TopCategory? = null

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

// getBalanceReport

}