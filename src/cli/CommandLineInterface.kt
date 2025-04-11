package cli

import model.Transaction
import model.TransactionCategory
import model.TransactionType
import repository.TransactionManager
import java.util.*
import kotlin.system.exitProcess
import util.toUUIDOrNull


class CommandLineInterface() {


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
        val idInput = scanner.nextLine()
        val id = idInput.toUUIDOrNull()

        if (id == null) {
            println("❌ Invalid ID format.")
            return false
        }

        val existing = transactionManager.getTransactionById(id)
        if (existing == null) {
            println("❌ Transaction not found.")
            return false
        }

        val tempTransaction = addTransaction(
            defaultAmount = existing.amount,
            defaultType = existing.type,
            defaultCategory = existing.transactionCategory
        )

        // Because addTransaction() returns Boolean, this check must be simplified
        if (!tempTransaction) {
            println("❌ Transaction update canceled.")
            return false
        }

        // You'll need to fetch the last transaction added
        val all = transactionManager.getAllTransactions()
        val last = all.lastOrNull() ?: return false
        val updatedTransaction = last.copy(id = existing.id, date = existing.date)

        val success = transactionManager.updateTransaction(updatedTransaction)

        if (success) {
            println("✅ Transaction updated successfully.")
            return true
        } else {
            println("❌ Failed to update transaction.")
            return false
        }
    }




    private fun deleteTransaction(): Boolean {
        println("===== DELETE TRANSACTION =====")
        print("Enter transaction ID: ")
        val idInput = scanner.nextLine()
        val id = idInput.toUUIDOrNull()

        if (id == null) {
            println("❌ Invalid ID format.")
            return false
        }

        val transaction = transactionManager.getTransactionById(id)
        if (transaction == null) {
            println("❌ Transaction not found.")
            return false
        }

        val deleted = transactionManager.deleteTransaction(id)
        if (deleted) {
            println("✅ Transaction deleted successfully.")
            return true
        } else {
            println("❌ Failed to delete transaction.")
            return false
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




