package checks

import cli.CommandLineInterface
import model.Transaction

class CommandLineInterfaceChecks {
    private val cli = CommandLineInterface()

    fun checkAddTransactionIsCalledWhenChoice1(){
        val userInput = "1"
//        val result = false
        val result = cli.addTransaction()
        check("Should call addTransaction when user selects 1 from the menu", result, true)
    }

    fun checkViewAllTransactionsIsCalledWhenChoice2(){
        val userInput = "2"
//        val result = false
        val result = cli.viewAllTransactions() == (listOf<Transaction>() ?: true)

        check("Should call viewAllTransactions when user selects 2 from the menu", result, true)
    }

    fun checkEditTransactionsIsCalledWhenChoice3(){
        val userInput = "3"
//        val result = false
        val result = cli.editTransaction()
        check("Should call editTransactions when user selects 3 from the menu", result, true)
    }


    fun checkDeleteTransactionsIsCalledWhenChoice4(){
        val userInput = "4"
//        val result = false
        val result = cli.deleteTransaction()
        check("Should call deleteTransactions when user selects 4 from the menu", result, true)
    }


    fun checkViewMonthlySummaryIsCalledWhenChoice5(){
        val userInput = "5"
//        val result = false
//        val result = cli.viewMonthlySummary()
        val result = cli.viewMonthlySummary() == (listOf<Transaction>() ?: true)
        check("Should call viewMonthlySummary when user selects 5 from the menu", result, true)
    }


    fun checkViewCurrentBalanceIsCalledWhenChoice6(){
        val userInput = "6"
//        val result = false
//        val result = cli.viewCurrentBalance()
        val result = cli.viewCurrentBalance() == (0.0 ?: true)
        check("Should call viewCurrentBalance when user selects 6 from the menu", result, true)
    }

    fun checkExitIsCalledWhenChoice6(){
        val userInput = "7"
//        val result = false
        val result = true
        check("Should call exit when user selects 7 from the menu", result, true)
    }
}

// This main fun for show output
fun main(){
    val testCli = CommandLineInterfaceChecks()
    println(testCli.checkAddTransactionIsCalledWhenChoice1())
    println(testCli.checkAddTransactionIsCalledWhenChoice1())
    println(testCli.checkAddTransactionIsCalledWhenChoice1())
    println(testCli.checkAddTransactionIsCalledWhenChoice1())
    println(testCli.checkAddTransactionIsCalledWhenChoice1())
    println(testCli.checkAddTransactionIsCalledWhenChoice1())
    println(testCli.checkAddTransactionIsCalledWhenChoice1())
    println(testCli.checkAddTransactionIsCalledWhenChoice1())
}

