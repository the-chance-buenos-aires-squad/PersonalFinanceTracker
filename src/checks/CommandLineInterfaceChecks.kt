package checks

import cli.CommandLineInterface

class CommandLineInterfaceChecks {
    val cli = CommandLineInterface()

    fun checkAddTransactionIsCalledWhenChoice1(){
        val userInput = "1"
        val result = false
//        val result = cli.addTransaction()
        check("Should call addTransaction when user selects 1 from the menu", result, true)
    }

    fun checkViewAllTransactionsIsCalledWhenChoice2(){
        val userInput = "2"
        val result = false
//        val result = cli.viewAllTransactions()
        check("Should call viewAllTransactions when user selects 2 from the menu", result, true)
    }

    fun checkEditTransactionsIsCalledWhenChoice3(){
        val userInput = "3"
        val result = false
//        val result = cli.editTransactions()
        check("Should call editTransactions when user selects 3 from the menu", result, true)
    }


    fun checkDeleteTransactionsIsCalledWhenChoice4(){
        val userInput = "4"
        val result = false
//        val result = cli.deleteTransactions()
        check("Should call deleteTransactions when user selects 4 from the menu", result, true)
    }


    fun checkViewMonthlySummaryIsCalledWhenChoice5(){
        val userInput = "5"
        val result = false
//        val result = cli.viewMonthlySummary()
        check("Should call viewMonthlySummary when user selects 5 from the menu", result, true)
    }


    fun checkViewCurrentBalanceIsCalledWhenChoice6(){
        val userInput = "6"
        val result = false
//        val result = cli.viewCurrentBalance()
        check("Should call viewCurrentBalance when user selects 6 from the menu", result, true)
    }

    fun checkExitIsCalledWhenChoice6(){
        val userInput = "7"
        val result = false
//        val result = cli.exit()
        check("Should call exit when user selects 7 from the menu", result, true)
    }

}