package checks

import data_source.FileBasedTransactionsDataSource
import data_source.TransactionDataSource
import model.Transaction
import model.TransactionCategory
import model.TransactionType
import util.TransactionsFile
import java.time.LocalDate

fun main() {
    val transactionsFile = TransactionsFile()
    val dataSource = FileBasedTransactionsDataSource(transactionsFile)

    addTransactionsChecks(dataSource)

}

fun addTransactionsChecks(dataSource: TransactionDataSource) {
    check(
        "When add transaction to exist file it should return true",
        result = dataSource.addTransactions(
            Transaction(
                amount = 20000.0,
                type = TransactionType.INCOME,
                transactionCategory = TransactionCategory.SALARY,
                date = LocalDate.of(2025, 1, 1)
            )
        ),
        correctResult = true
    )
}

