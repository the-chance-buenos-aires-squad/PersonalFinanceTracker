package repository

import data_source.DefaultDataSource
import model.MonthlySummary
import model.Transaction
import model.TransactionType

class TransactionRepository(val dataSource: DefaultDataSource) {

    fun getMonthlySummaryReport(month: Int): MonthlySummary {
        val allTransactions = dataSource.getAllTransactions()


        return MonthlySummary(1, 2012, 2000.0, 1500.0, 500.0)
    }

}