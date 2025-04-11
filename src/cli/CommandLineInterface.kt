package cli

import model.Transaction
import model.TransactionCategory
import model.TransactionType
import repository.TransactionManager
import util.Validator
import java.util.*
import kotlin.system.exitProcess
import util.toUUIDOrNull


class CommandLineInterface() {
    private val validator = Validator()



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

     private fun addTransaction(): Boolean {
        // TODO: Implement transaction addition logic
         return false
    }

     private fun viewAllTransactions() : List<Transaction>{
        // TODO: Implement transaction listing logic
         return listOf()
    }

    private fun editTransaction(): Boolean {
        println("===== EDIT TRANSACTION =====")
        print("Enter transaction ID: ")
        val id = validator.isValidUUID(scanner.nextLine())

        if (!validator.transactionExists(id) { transactionManager.getTransactionById(it) != null }) {
            println("❌ Invalid or nonexistent transaction ID.")
            return false
        }

        val existing = transactionManager.getTransactionById(id!!) // safe because of the check above

        val tempTransaction = addTransaction(
            defaultAmount = existing.amount,
            defaultType = existing.type,
            defaultCategory = existing.transactionCategory
        )

        if (!tempTransaction) {
            println("❌ Transaction update canceled.")
            return false
        }

        val all = transactionManager.getAllTransactions()
        val last = all.lastOrNull() ?: return false
        val updatedTransaction = last.copy(id = existing.id, date = existing.date)

        return if (transactionManager.updateTransaction(updatedTransaction)) {
            println("✅ Transaction updated successfully.")
            true
        } else {
            println("❌ Failed to update transaction.")
            false
        }
    }




    private fun deleteTransaction(): Boolean {
        println("===== DELETE TRANSACTION =====")
        print("Enter transaction ID: ")
        val id = validator.isValidUUID(scanner.nextLine())

        if (!validator.transactionExists(id) { transactionManager.getTransactionById(it) != null }) {
            println("❌ Invalid or nonexistent transaction ID.")
            return false
        }

        return if (transactionManager.deleteTransaction(id!!)) {
            println("✅ Transaction deleted successfully.")
            true
        } else {
            println("❌ Failed to delete transaction.")
            false
        }
    }




    private fun viewMonthlySummary() : List<Transaction>{
        // TODO: Implement monthly summary logic
         return listOf()
    }

     private fun viewCurrentBalance(): Double {
        // TODO: Implement balance calculation logic
         return 0.00
    }

     private fun exit() {
        println("Exiting application... Goodbye!")
        exitProcess(0)
    }
}




