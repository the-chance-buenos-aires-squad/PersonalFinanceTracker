package checks

import cli.CommandLineInterface
import model.Transaction

class CommandLineInterfaceChecks {
    private val cli = CommandLineInterface()

    fun checkAddTransactionIsCalledWhenChoice1(){
        cli.handleUserInput("1")
        val result = cli.addTransactionCalled
        checkChoices("Should call addTransaction when user selects 1 from the menu", result, true,)  // expects true
    }

    fun checkViewAllTransactionsIsCalledWhenChoice2(){
        cli.handleUserInput("2")
        val result = cli.viewAllTransactionsCalled
        checkChoices("Should call viewAllTransactions when user selects 2 from the menu", result, true)
    }

    fun checkEditTransactionsIsCalledWhenChoice3(){
        cli.handleUserInput("3")
        val result = cli.editTransactionCalled
        checkChoices("Should call editTransactions when user selects 3 from the menu", result, true)
    }


    fun checkDeleteTransactionsIsCalledWhenChoice4(){
        cli.handleUserInput("4")
        val result = cli.deleteTransactionCalled
        checkChoices("Should call deleteTransactions when user selects 4 from the menu", result, true)
    }


    fun checkViewMonthlySummaryIsCalledWhenChoice5(){
        cli.handleUserInput("5")
        val result = cli.viewMonthlySummaryCalled
        checkChoices("Should call viewMonthlySummary when user selects 5 from the menu", result, true)
    }


    fun checkViewCurrentBalanceIsCalledWhenChoice6(){
        cli.handleUserInput("6")
        val result = cli.viewCurrentBalanceCalled
        checkChoices("Should call viewCurrentBalance when user selects 6 from the menu", result, true)
    }

    fun checkExitIsCalledWhenChoice7(){
        cli.handleUserInput("7")
        val result = cli.exitCalled
        checkChoices("Should call exit when user selects 7 from the menu", result, true)
    }

    fun checkInvalidInput(){
        cli.handleUserInput("invalid")
        val result = cli.exitCalled
        checkChoices("Should return false when input letter or invalid number", result,false)
    }

}

fun checkChoices(name: String, result: Boolean, correctResult: Boolean){
    if(result == correctResult){
        println("Success - $name")
    }else{
        println("Failed - $name")
    }
}


// This main fun for show output
fun main(){
    val commandLineInterfaceChecks = CommandLineInterfaceChecks()
    println(commandLineInterfaceChecks.checkAddTransactionIsCalledWhenChoice1())
    println(commandLineInterfaceChecks.checkViewAllTransactionsIsCalledWhenChoice2())
    println(commandLineInterfaceChecks.checkEditTransactionsIsCalledWhenChoice3())
    println(commandLineInterfaceChecks.checkDeleteTransactionsIsCalledWhenChoice4())
    println(commandLineInterfaceChecks.checkViewMonthlySummaryIsCalledWhenChoice5())
    println(commandLineInterfaceChecks.checkViewCurrentBalanceIsCalledWhenChoice6())
    println(commandLineInterfaceChecks.checkExitIsCalledWhenChoice7())
}

