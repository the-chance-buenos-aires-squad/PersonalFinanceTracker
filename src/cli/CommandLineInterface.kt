package cli

import model.Transaction
import model.TransactionCategory
import model.TransactionType
import repository.TransactionManager
import java.time.LocalDate
import java.util.*
import kotlin.system.exitProcess

class CommandLineInterface(private val transactionManager: TransactionManager) {
    private val scanner = Scanner(System.`in`)
    fun start() {
        while (true) {
            displayMenu()
            handleUserInput(readlnOrNull())
        }
    }

    fun handleUserInput(input: String?) {
        when (input?.trim()) {
            "1" -> addTransaction()
            "2" -> viewAllTransactions()
            "3" -> editTransaction()
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

    private fun addTransaction(
        defaultAmount: Double? = null,
        defaultType: TransactionType? = null,
        defaultCategory: TransactionCategory? = null
    ): Boolean {
        println("===== ${if (defaultAmount != null) "EDIT" else "ADD"} TRANSACTION =====")

        // Amount
        var amount: Double? = null
        while (amount == null) {
            print("Enter amount${if (defaultAmount != null) " (default: $defaultAmount)" else ""}: ")
            val input = scanner.nextLine()
            amount = input.toDoubleOrNull() ?: defaultAmount
            if (amount == null || amount <= 0) {
                println("❌ Invalid amount. Please enter a valid number.")
                amount = null
            }
        }

        // Transaction type
        println("Choose transaction type:")
        val types = TransactionType.values()
        types.forEachIndexed { index, type ->
            println("${index + 1}. ${type.name.lowercase().replaceFirstChar { it.uppercase() }}")
        }

        var type: TransactionType? = null
        while (type == null) {
            print("Your choice (or press Enter to keep ${defaultType ?: "none"}): ")
            val input = scanner.nextLine()
            type = if (input.isBlank()) defaultType
            else input.toIntOrNull()?.let { if (it in 1..types.size) types[it - 1] else null }
            if (type == null) println("❌ Invalid type.")
        }

        // Category
        println("Choose category:")
        val categories = TransactionCategory.values()
        categories.forEachIndexed { index, cat ->
            println("${index + 1}. ${cat.name.lowercase().replaceFirstChar { it.uppercase() }}")
        }

        var category: TransactionCategory? = null
        while (category == null) {
            print("Your choice (or press Enter to keep ${defaultCategory ?: "none"}): ")
            val input = scanner.nextLine()
            category = if (input.isBlank()) defaultCategory
            else input.toIntOrNull()?.let { if (it in 1..categories.size) categories[it - 1] else null }
            if (category == null)
                println("❌ Invalid category.")
        }

        val transaction = Transaction(
            amount = amount,
            transactionCategory = category,
            type = type,
            date = LocalDate.now()
        )

        transactionManager.addTransaction(transaction)
        println("✅ Transaction added successfully!")
        return true
    }


    private fun viewAllTransactions(): List<Transaction> {
        val transactions = transactionManager.getAllTransactions()

        if (transactions.isEmpty()) {
            println("No transactions found.")
        } else {
            println("===== VIEW ALL TRANSACTIONS =====")
            println("ID | Date | Amount | Category | Type")
            println("-------------------------------------------------------------")
            transactions.forEach {
                println(
                    "${it.id} | ${it.date} | ${it.amount} | ${
                        it.transactionCategory.name.lowercase().replaceFirstChar { c -> c.uppercase() }
                    } | ${it.type.name.lowercase().replaceFirstChar { c -> c.uppercase() }}"
                )
            }
        }

        return transactions
    }

    private fun editTransaction(): Boolean {
        // TODO: Implement transaction editing logic
        return false
    }

    private fun deleteTransaction(): Boolean {
        // TODO: Implement transaction deletion logic
        return false
    }

    private fun viewMonthlySummary(): List<Transaction> {
        // TODO: Implement monthly summary logic
        return listOf()
    }

    private fun viewCurrentBalance(): String {
        return "Total Balance: ${transactionManager.getBalance()}"
    }

    private fun exit() {
        println("Exiting application... Goodbye!")
        exitProcess(0)
    }
}

