package cli


import model.Transaction
import model.TransactionCategory
import model.TransactionType
import repository.TransactionManager
import java.time.LocalDate
import java.util.*

class CommandLineInterface(private val transactionManager: TransactionManager) {

    private val scanner= Scanner(System.`in`)

    fun addTransaction(){
        println("===== ADD TRANSACTION =====")

        var amount:Double?=null
        while (amount==null){
            println("Enter amount: ")
            val input = scanner.nextLine()
            amount=input.toDoubleOrNull()
            if (amount==null || amount<=0){
                println("Invalid amount. please enter a valid amount: ")
                amount=null
            }

        }
        println("Choose transaction type:")
        val types=TransactionType.values()
        for ((index,t) in types.withIndex()) {
            println("${index + 1}. ${t.name.capitalize()}")
        }
            var type:TransactionType?=null
            while (type==null){
                println("your choice (1-${types.size}): ")
                val input = scanner.nextLine().toIntOrNull()
            if (input!=null && input in 1..types.size) {
                type = types[input - 1]
            }else{
                println("Invalid choice please Enter valid choice between 1 and ${types.size}")
            }
        }

        println("Choose category: ")
        val categories =TransactionCategory.values()
        for ((index,cat) in categories.withIndex()){
            println("${index+1}. ${cat.name.capitalize()}")
        }

        var category:TransactionCategory?=null
        while (category==null){
            println("Your choice (1-${categories.size}): ")
            val input =scanner.nextLine().toIntOrNull()
            if(input!=null && input in 1..categories.size){
                category=categories[input-1]
            }else{
                println("Invalid choice. Please enter a number between 1 and ${categories.size}.")
            }
        }
        val transaction = Transaction(
            amount = amount,
            transactionCategory = category,
            type = type,
            date = LocalDate.now()
        )

            transactionManager.addTransaction(transaction)
            println("✅ Transaction added successfully!")

    }
    fun viewTransactions() {
        val transactions = transactionManager.getAllTransactions()

        if (transactions.isEmpty()) {
            println("No transactions found.")
            return
        }

        println("===== VIEW ALL TRANSACTIONS =====")
        println("ID | Date | Amount | Category | Type")
        println("-------------------------------------------------------------")

        for (t in transactions) {
            println("${t.id} | ${t.date} | ${t.amount} | ${t.transactionCategory.name.capitalize()} | ${t.type.name.capitalize()}")
        }
    }

}

