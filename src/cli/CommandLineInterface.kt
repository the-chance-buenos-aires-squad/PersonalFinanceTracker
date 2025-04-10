package cli

import model.Transaction
import kotlin.system.exitProcess

class CommandLineInterface {

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

     private fun editTransaction() : Boolean{
        // TODO: Implement transaction editing logic
         return false
    }

     private fun deleteTransaction(): Boolean {
        // TODO: Implement transaction deletion logic
         return false
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

// THis main fun for show output
fun main(){
    val cli = CommandLineInterface()
    cli.start()
}

