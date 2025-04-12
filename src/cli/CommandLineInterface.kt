package cli

import model.Transaction
import model.TransactionCategory
import model.TransactionType
import repository.TransactionManager
import util.displayOnScreen
import util.Validator
import java.util.*
import kotlin.system.exitProcess


class CommandLineInterface(
    private val transactionManager: TransactionManager,
    private val validator: Validator
) {
    private val scanner = Scanner(System.`in`)
    fun start() {
        while (true) {
            displayMenu()
            handleUserInput(readlnOrNull())
        }
    }

    private fun handleUserInput(input: String?) {
        when (input?.trim()) {
            "1" -> addTransaction()
            "2" -> viewAllTransactions()
            "3" -> updateTransaction()
            "4" -> deleteTransaction()
            "5" -> viewMonthlySummary()
            "6" -> viewCurrentBalance()
            "7" -> exit()
            else -> println("Invalid choice. Please try again.")
        }
    }

    private fun displayMenu() {
        println()
        println("======= PERSONAL FINANCE TRACKER =======")
        println("\t1. Add Transaction")
        println("\t2. View All Transactions")
        println("\t3. Edit Transaction")
        println("\t4. Delete Transaction")
        println("\t5. View Monthly Summary")
        println("\t6. View Current Balance")
        println("\t7. Exit")
        println("=======================================")
        print("Enter your choice: ")
    }

    private fun addTransaction(): Boolean {
        // TODO: Implement transaction addition logic
        return false
    }


    private fun viewAllTransactions(): List<Transaction> {
        val transactions = transactionManager.getAllTransactions()

        if (transactions.isEmpty()) {
            println("No transactions found.")
        } else {
            println("===== VIEW ALL TRANSACTIONS =====")
            transactions.displayOnScreen()
        }
        return transactions
    }

    //region  UpdateTransaction
    private fun updateTransaction() {
        val transactionsList = transactionManager.getAllTransactions()
        transactionsList.displayOnScreen()

        print("\nEnter the number of the transaction you want to edit: ")
        val indexInput = readLine()
        val index = validator.getValidIndex(indexInput, transactionsList.size)
        if (index == null) {
            println("Invalid choice.")
            return
        }

        val selectedTransaction = transactionsList[index]
        println("Leave any field blank to keep current value.\n")

        val newAmount = enterTransactionAmount(selectedTransaction)
        val newType = chooseTransactionType(selectedTransaction.type)
        val newCategory = chooseTransactionCategory(selectedTransaction.transactionCategory)

        val updatedTransaction = selectedTransaction.copy(
            amount = newAmount,
            type = newType,
            transactionCategory = newCategory
        )
        transactionManager.updateTransaction(index, updatedTransaction)
        println("Transaction Updated successfully!")
    }

    private fun chooseTransactionType(default: TransactionType): TransactionType {
        println("Choose new type")
        TransactionType.entries.forEachIndexed { index, type ->
            println("${index + 1} - $type")
        }
        print("Your choice (or press Enter to keep none) : ")
        val input = readLine()
        return validator.getValidTransactionType(input) ?: default
    }

    private fun chooseTransactionCategory(default: TransactionCategory): TransactionCategory {
        println("Choose new category:")
        TransactionCategory.entries.forEachIndexed { index, category ->
            println("${index + 1} - $category")
        }
        print("Your choice (or press Enter to keep none) : ")
        val input = readLine()
        return validator.getValidCategory(input) ?: default
    }

    private fun enterTransactionAmount(selectedTransaction: Transaction): Double {
        print("New amount (current: ${selectedTransaction.amount}): ")
        val newAmountInput = readLine()
        val newAmount = newAmountInput?.toDoubleOrNull() ?: selectedTransaction.amount
        return newAmount
    }

    //endregion

    //region deleteTransaction
    private fun deleteTransaction() {
        println("===== DELETE TRANSACTION =====")
        val transactionsList = transactionManager.getAllTransactions()
        transactionsList.displayOnScreen()
        println("Enter the number of the transaction you want to delete: ")
        val indexInput = readLine()
        val index = validator.getValidIndex(indexInput, transactionsList.size)
        if (index == null) {
            println("Invalid choice.")
            return
        }
        val selectedTransaction = transactionsList[index]
        transactionManager.deleteTransaction(selectedTransaction.id)
        println("Transaction deleted successfully!")
    }
    //endregion


    private fun viewMonthlySummary(): List<Transaction> {
        println("===== VIEW MONTHLY SUMMARY =====")

        print("Enter year (e.g. 2025): ")
        val yearInput = scanner.nextLine().toIntOrNull()

        print("Enter month (1-12): ")
        val monthInput = scanner.nextLine().toIntOrNull()

        if (!validator.yearIsValid(yearInput) || !validator.monthIsValid(monthInput)) {
            println("❌ Invalid year or month.")
            return listOf()
        }

        val summary = transactionManager.getMonthlySummaryReport(monthInput!!, yearInput!!)
        if (summary == null) {
            println("⚠️ No transactions found for $monthInput/$yearInput.")
            return listOf()
        }
        println("\n📊 Summary for $monthInput/$yearInput:")
        println("Total Income: \$${summary.totalIncome}")
        println("Total Expense: \$${summary.totalExpense}")
        println("Net: \$${summary.totalIncome - summary.totalExpense}")

        println("\n🔝 Highest Income Category:")
        summary.highestIncomeCategory?.let {
            println("- ${it.category}: \$${it.amount}")
        } ?: println("- None")

        println("\n🔝 Highest Expense Category:")
        summary.highestExpenseCategory?.let {
            println("- ${it.category}: \$${it.amount}")
        } ?: println("- None")
        return emptyList()
    }

    private fun viewCurrentBalance(): String {
        return "Total Balance: ${transactionManager.getBalance()}"
    }

    private fun exit() {
        println("Exiting application... Goodbye!")
        exitProcess(0)
    }
}




