package repository

import data_source.TransactionDataSource
import model.MonthlySummary
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
        allTransactions.forEach { it ->
            if (it.date.monthValue == month) {
                if (it.type == TransactionType.INCOME) {
                    totalIncome += it.amount
                } else {
                    totalExpense += it.amount
                }
            }
        }
        val balance = totalIncome - totalExpense

        return MonthlySummary(
            month,
            year,
            totalIncome,
            totalExpense,
            balance
        )
    }

// getBalanceReport

}