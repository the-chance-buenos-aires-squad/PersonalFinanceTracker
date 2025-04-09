package checks

import data_source.DataSource
import model.Transaction
import model.TransactionType
import domain.model.Category
import java.time.LocalDate
import java.util.*

fun main() {
    val dataSource = DataSource()

    // Test 1: Add income transaction
    val incomeTransaction = Transaction(
        amount = 1000.0,
        category = Category.SALARY,
        type = TransactionType.INCOME,
        date = LocalDate.now()
    )
    try {
        val addedTransaction = dataSource.addTransactions(incomeTransaction)
        check(
            "Test 1: Add income transaction",
            addedTransaction,
            incomeTransaction
        )
    } catch (e: Exception) {
        println("Test 1 failed: ${e.message}")
    }

    // Test 2: Add expense transaction with sufficient balance
    val expenseTransaction = Transaction(
        amount = 500.0,
        category = Category.FOOD,
        type = TransactionType.EXPENSES,
        date = LocalDate.now()
    )
    try {
        val addedExpense = dataSource.addTransactions(expenseTransaction)
        check(
            "Test 2: Add expense transaction with sufficient balance",
            addedExpense,
            expenseTransaction
        )
    } catch (e: Exception) {
        println("Test 2 failed: ${e.message}")
    }

    // Test 3: Add expense transaction with insufficient balance
    val largeExpense = Transaction(
        amount = 2000.0,
        category = Category.RENT,
        type = TransactionType.EXPENSES,
        date = LocalDate.now()
    )
    try {
        dataSource.addTransactions(largeExpense)
        println("Test 3 failed: Should have thrown Insufficient balance exception")
    } catch (e: IllegalStateException) {
        check(
            "Test 3: Add expense transaction with insufficient balance",
            e.message,
            "Insufficient balance for this transaction"
        )
    }

    // Test 4: Add transaction with invalid amount
    val invalidTransaction = Transaction(
        amount = -100.0,
        category = Category.FOOD,
        type = TransactionType.EXPENSES,
        date = LocalDate.now()
    )
    try {
        dataSource.addTransactions(invalidTransaction)
        println("Test 4 failed: Should have thrown Invalid amount exception")
    } catch (e: IllegalArgumentException) {
        check(
            "Test 4: Add transaction with invalid amount",
            e.message,
            "Transaction amount must be positive"
        )
    }

    // Test 5: Update transaction amount
    val updatedExpense = expenseTransaction.copy(amount = 300.0)
    try {
        val result = dataSource.updateTransaction(updatedExpense)
        check(
            "Test 5: Update transaction amount",
            result,
            updatedExpense
        )
    } catch (e: Exception) {
        println("Test 5 failed: ${e.message}")
    }

    // Test 6: Update transaction type
    val typeChangedTransaction = expenseTransaction.copy(
        type = TransactionType.INCOME,
        amount = 400.0
    )
    try {
        val result = dataSource.updateTransaction(typeChangedTransaction)
        check(
            "Test 6: Update transaction type",
            result,
            typeChangedTransaction
        )
    } catch (e: Exception) {
        println("Test 6 failed: ${e.message}")
    }

    // Test 7: Update non-existent transaction
    val nonExistentTransaction = Transaction(
        id = UUID.randomUUID(),
        amount = 100.0,
        category = Category.FOOD,
        type = TransactionType.EXPENSES,
        date = LocalDate.now()
    )
    try {
        dataSource.updateTransaction(nonExistentTransaction)
        println("Test 7 failed: Should have thrown Transaction not found exception")
    } catch (e: NoSuchElementException) {
        check(
            "Test 7: Update non-existent transaction",
            e.message?.contains("Transaction with id") ?: false,
            true
        )
    }

    // Test 8: Delete existing transaction
    try {
        val deleteResult = dataSource.deleteTransaction(expenseTransaction.id)
        check(
            "Test 8: Delete existing transaction",
            deleteResult,
            true
        )
    } catch (e: Exception) {
        println("Test 8 failed: ${e.message}")
    }

    // Test 9: Delete non-existent transaction
    try {
        val deleteResult = dataSource.deleteTransaction(UUID.randomUUID())
        check(
            "Test 9: Delete non-existent transaction",
            deleteResult,
            false
        )
    } catch (e: Exception) {
        println("Test 9 failed: ${e.message}")
    }

    // Test 10: Multiple operations sequence
    try {
        // Add initial income
        val initialIncome = Transaction(
            amount = 2000.0,
            category = Category.SALARY,
            type = TransactionType.INCOME,
            date = LocalDate.now()
        )
        dataSource.addTransactions(initialIncome)

        // Add expense
        val expense = Transaction(
            amount = 500.0,
            category = Category.RENT,
            type = TransactionType.EXPENSES,
            date = LocalDate.now()
        )
        dataSource.addTransactions(expense)

        // Update expense
        val updatedExpense = expense.copy(amount = 600.0)
        dataSource.updateTransaction(updatedExpense)

        // Delete expense
        val deleteResult = dataSource.deleteTransaction(expense.id)

        check(
            "Test 10: Multiple operations sequence",
            deleteResult,
            true
        )
    } catch (e: Exception) {
        println("Test 10 failed: ${e.message}")
    }
}

fun <T> check(
    message: String,
    result: T,
    correctResult: T
) {
    println(message)
    if (result == correctResult) println("successful") else println("failed")
    println("--------")
}
