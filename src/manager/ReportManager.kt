package manager

import data_source.TransactionDataSource
import model.MonthlySummary
import model.TopCategory
import model.Transaction
import model.TransactionType
import java.time.LocalDate

class ReportManager(private val dataSource: TransactionDataSource) {

    fun getBalance(): Double {
        val transactions = dataSource.getAllTransactions()
        val income = transactions.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val expense = transactions.filter { it.type == TransactionType.EXPENSES }.sumOf { it.amount }
        val balance = income - expense
        return balance
    }

    fun getMonthlySummaryReport(month: Int, year: Int = LocalDate.now().year): MonthlySummary? {
        val allTransactions = dataSource.getAllTransactions()
        val monthlyTransaction = filterMonthlyTransactions(allTransactions, month, year)
        if (monthlyTransaction.isEmpty()) return null

        val incomeList = mutableListOf<Transaction>()
        val expanseList = mutableListOf<Transaction>()
        val totalIncome = calculateIncome(monthlyTransaction, incomeList)
        val totalExpense = calculateExpense(monthlyTransaction, expanseList)

        val highestIncome = incomeList.maxByOrNull { it.amount }
        val highestExpense = expanseList.maxByOrNull { it.amount }

        val topIncomeCategory = highestIncome?.let { TopCategory(it.amount, it.transactionCategory) }
        val topExpenseCategory = highestExpense?.let { TopCategory(it.amount, it.transactionCategory) }

        return MonthlySummary(
            totalIncome = totalIncome,
            totalExpense = totalExpense,
            incomeList = incomeList,
            expenseList = expanseList,
            highestIncomeCategory = topIncomeCategory,
            highestExpenseCategory = topExpenseCategory
        )
    }

    private fun filterMonthlyTransactions(
        allTransactions: List<Transaction>,
        month: Int,
        year: Int
    ): List<Transaction> {
        return allTransactions.filter { it.date.monthValue == month && it.date.year == year }
    }

    private fun calculateIncome(
        transactions: List<Transaction>,
        incomeList: MutableList<Transaction>
    ): Double {
        var totalIncome = 0.0
        transactions.forEach {
            if (it.type == TransactionType.INCOME) {
                incomeList.add(it)
                totalIncome += it.amount
            }
        }
        return totalIncome
    }

    private fun calculateExpense(
        transactions: List<Transaction>,
        expanseList: MutableList<Transaction>
    ): Double {
        var totalExpense = 0.0
        transactions.forEach {
            if (it.type == TransactionType.EXPENSES) {
                expanseList.add(it)
                totalExpense += it.amount
            }
        }
        return totalExpense
    }


}