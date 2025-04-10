package cli

import model.Transaction
import java.util.*
import kotlin.system.exitProcess

class CommandLineInterface {

    fun start() {
        while (true) {
            displayMenu()
            when (readlnOrNull()?.trim()) {
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
    }

     fun displayMenu() {
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

     fun addTransaction(): Boolean {
        println("\n=== Add Transaction ===")
        // TODO: Implement transaction addition logic
        println("Transaction added successfully!")
         return true
    }

     fun viewAllTransactions() : List<Transaction>{
        println("\n=== All Transactions ===")
        // TODO: Implement transaction listing logic
        println("No transactions available yet.")
         return listOf()
    }

     fun editTransaction() : Boolean{
        println("\n=== Edit Transaction ===")
        // TODO: Implement transaction editing logic
        println("Transaction edited successfully!")
         return true
    }

     fun deleteTransaction(): Boolean {
        println("\n=== Delete Transaction ===")
        // TODO: Implement transaction deletion logic
        println("Transaction deleted successfully!")
         return true
    }

     fun viewMonthlySummary() : List<Transaction>{
        println("\n=== Monthly Summary ===")
        // TODO: Implement monthly summary logic
        println("No summary available yet.")
         return listOf()
    }

     fun viewCurrentBalance(): Double {
        println("\n=== Current Balance ===")
        // TODO: Implement balance calculation logic
        println("Current balance: \$0.00")
         return 0.00
    }

     fun exit() {
        println("Exiting application... Goodbye!")
        exitProcess(0)
    }
}

// THis main fun for show output
fun main(){
    val cli = CommandLineInterface()
    cli.start()
}

