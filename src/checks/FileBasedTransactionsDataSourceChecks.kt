package checks

import data_source.FileBasedTransactionsDataSource
import data_source.TransactionDataSource
import model.Transaction
import model.TransactionCategory
import model.TransactionType
import util.TransactionsFileManager
import java.time.LocalDate
import java.util.*

fun main() {
    val transactionsFileManager = TransactionsFileManager()
    val dataSource = FileBasedTransactionsDataSource(transactionsFileManager)

    addTransactionsChecks(dataSource)
    deleteTransactionChecks(dataSource)
    getAllTransactionsChecks(dataSource)
    getTransactionByIdChecks(dataSource)
    updateTransactionChecks(dataSource)
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

fun getAllTransactionsChecks(dataSource: TransactionDataSource) {
    check(
        "When file doesn't exist it should return true",
        result = dataSource.getAllTransactions().isEmpty(),
        correctResult = true
    )
    check(
        "When file exist and contains no transactions it should return true",
        result = dataSource.getAllTransactions().isEmpty(),
        correctResult = true
    )
    check(
        "When file exist and contains transactions it should return true",
        result = dataSource.getAllTransactions().isNotEmpty(),
        correctResult = true
    )
}

fun deleteTransactionChecks(dataSource: TransactionDataSource) {
    val transactionId = UUID.randomUUID()
    check(
        "When file doesn't exist it should return false",
        result = dataSource.deleteTransaction(id = transactionId),
        correctResult = false
    )
    check(
        "When file exist and contains no transaction with this id it should return false",
        result = dataSource.deleteTransaction(id = transactionId),
        correctResult = false
    )
    check(
        "When file exist and contains transaction with this id it should return true",
        result = dataSource.deleteTransaction(id = transactionId),
        correctResult = true
    )
}

fun getTransactionByIdChecks(dataSource: TransactionDataSource) {
    val transactionId = UUID.randomUUID()
    check(
        "When file doesn't exist it should return false",
        result = dataSource.getTransactionById(id = transactionId) == null,
        correctResult = true
    )
    check(
        "When file exist and contains no transaction with this id it should return true",
        result = dataSource.getTransactionById(id = transactionId) == null,
        correctResult = true
    )
    check(
        "When file exist and contains transaction with this id it should return true",
        result = dataSource.getTransactionById(id = transactionId) != null,
        correctResult = true
    )
}

fun updateTransactionChecks(dataSource: TransactionDataSource) {
    val transactionId = UUID.randomUUID()
    val transaction = Transaction(
        id = transactionId,
        amount = 200.0,
        transactionCategory = TransactionCategory.RENT,
        type = TransactionType.EXPENSES,
        date = LocalDate.now()
    )
    check(
        "When file doesn't exist it should return false",
        result = dataSource.updateTransaction(transaction),
        correctResult = false
    )
    check(
        "When file exist and contains no transaction with this id it should return true",
        result = dataSource.updateTransaction(transaction),
        correctResult = false
    )
    check(
        "When file exist and contains transaction with this id it should return true",
        result = dataSource.updateTransaction(transaction),
        correctResult = true
    )
}

