package cli

import model.Transaction
import model.TransactionCategory
import model.TransactionType
import manager.ReportManager
import manager.TransactionManager
import model.MonthlySummary
import util.*
import java.time.LocalDate
import java.util.*
import kotlin.system.exitProcess


class CommandLineInterface(
    private val transactionManager: TransactionManager,
    private val reportManager: ReportManager,
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
        println(
            """
            
            ======= PERSONAL FINANCE TRACKER =======
            	1. Add Transaction
            	2. View All Transactions
                3. Edit Transaction
                4. Delete Transaction
                5. View Monthly Summary
                6. View Current Balance
                7. Exit
            =======================================
            Enter your choice: 
        """.trimIndent()
        )
    }

    //region  addTransaction
    private fun addTransaction() {
        printSectionHeader("ADD TRANSACTION")

        val amount = enterTransactionAmount(validator)
        val type = chooseTransactionType()
        val category = chooseTransactionCategory()

        val transaction = Transaction(
            amount = amount, transactionCategory = category, type = type, date = LocalDate.now()
        )

        transactionManager.addTransaction(transaction)
        println("Transaction added successfully!\n")
        singleTransaction(transaction)

    }

    private fun enterTransactionAmount(validator: Validator): Double {
        var amount: Double? = null
        while (amount == null) {
            print("Enter amount: ")
            val input = scanner.nextLine()
            if (validator.isValidAmount(input)) {
                amount = input.toDouble()
            } else {
                println("Invalid amount. Please enter a valid number.")
            }
        }
        return amount
    }

    private fun chooseTransactionType(): TransactionType {
        val types = TransactionType.values()
        println("Transaction Type:")
        types.forEachIndexed { index, type ->
            println("${index + 1}. ${type.name.lowercase().replaceFirstChar { it.uppercase() }}")
        }

        var type: TransactionType? = null
        while (type == null) {
            print("Your choice: ")
            val input = scanner.nextLine()
            type = getValidTransactionTypeFromInput(input)
            if (type == null) println("Invalid type.")
        }
        return type
    }

    private fun chooseTransactionCategory(): TransactionCategory {
        val categories = TransactionCategory.values()
        println("Transaction Category:")
        categories.forEachIndexed { index, category ->
            println("${index + 1}. ${category.name.lowercase().replaceFirstChar { it.uppercase() }}")
        }

        var category: TransactionCategory? = null
        while (category == null) {
            print("Your choice: ")
            val input = scanner.nextLine()
            category = getValidCategoryFromInput(input)
            if (category == null) println("Invalid category.")
        }
        return category
    }

    private fun singleTransaction(transaction: Transaction) {
        println("Your Transaction:")
        println("Amount | Category | Type | Date")
        println("----------------------------------------")
        println("${transaction.amount} | ${transaction.transactionCategory.name.lowercase()} | ${transaction.type.name.lowercase()} | ${transaction.date}")
    }

    private fun viewAllTransactions(): List<Transaction> {
        val transactions = transactionManager.getAllTransactions()

        if (transactions.isEmpty()) {
            println("No transactions found.")
        } else {
            printSectionHeader("VIEW ALL TRANSACTIONS")
            transactions.displayAllTransaction()
        }
        return transactions
    }
    //endregion

    //region  updateTransaction
    private fun updateTransaction() {
        printSectionHeader("THIS IS ALL TRANSACTIONS")
        val transactionsList = transactionManager.getAllTransactions()
        transactionsList.displayAllTransaction()

        print("\nEnter the number of the transaction you want to edit: ")
        val indexInput = readLine()
        val index = validator.getValidIndexFromInput(indexInput, transactionsList.size)
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
            amount = newAmount, type = newType, transactionCategory = newCategory
        )

        transactionManager.updateTransaction(updatedTransaction)
        println("Transaction Updated successfully!")
    }

    private fun chooseTransactionType(default: TransactionType): TransactionType {
        println("Choose new type")
        TransactionType.entries.forEachIndexed { index, type ->
            println("${index + 1} - $type")
        }
        print("Your choice (or press Enter to keep none) : ")
        val input = readLine()
        return getValidTransactionTypeFromInput(input) ?: default
    }

    private fun chooseTransactionCategory(default: TransactionCategory): TransactionCategory {
        println("Choose new category:")
        TransactionCategory.entries.forEachIndexed { index, category ->
            println("${index + 1} - $category")
        }
        print("Your choice (or press Enter to keep none) : ")
        val input = readLine()
        return getValidCategoryFromInput(input) ?: default
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
        printSectionHeader("DELETE TRANSACTION")
        val transactionsList = transactionManager.getAllTransactions()
        if (transactionsList.isEmpty()) {
            return
        }
        transactionsList.displayAllTransaction()
        println("Enter the number of the transaction you want to delete: ")
        val indexInput = readLine()
        val index = validator.getValidIndexFromInput(indexInput, transactionsList.size)
        if (index == null) {
            println("Invalid choice.")
            return
        }
        val selectedTransaction = transactionsList[index]
        transactionManager.deleteTransaction(selectedTransaction.id)
        println("Transaction deleted successfully!")
    }
    //endregion

    //region  viewMonthlySummary
    private fun viewMonthlySummary(): List<Transaction> {
        printSectionHeader("VIEW MONTHLY SUMMARY")
        val yearInput = getYearInput()
        val monthInput = getMonthInput()

        if (!validator.isValidYear(yearInput) || !validator.isValidMonth(monthInput)) {
            println("❌ Invalid year or month.")
            return listOf()
        }

        val summary = reportManager.getMonthlySummaryReport(monthInput!!, yearInput!!)
        if (summary == null) {
            println("⚠️ No transactions found for $monthInput/$yearInput.")
            return listOf()
        }

        printSummary(summary, monthInput, yearInput)
        return emptyList()
    }


    private fun getYearInput(): Int {
        print("Enter year (e.g. 2025 or press Enter to keep current year): ")
        return scanner.nextLine().toIntOrNull() ?: LocalDate.now().year
    }

    private fun getMonthInput(): Int? {
        print("Enter month (1-12): ")
        return scanner.nextLine().toIntOrNull()
    }

    private fun printSummary(summary: MonthlySummary, month: Int, year: Int) {
        println("\n📊 Summary for $month/$year:")
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
    }
    //endregion

    private fun viewCurrentBalance() {
        println("Total Balance: ${reportManager.getBalance()}")
    }
    //endregion

    //region  exit
    private fun exit() {
        println("Exiting application... Goodbye!")
        exitProcess(0)
    }
    //endregion
}