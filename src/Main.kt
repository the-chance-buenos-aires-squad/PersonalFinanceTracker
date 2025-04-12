import cli.CommandLineInterface
import data_source.InMemoryTransactionsDataSource
import repository.TransactionManager
import util.Validator

fun main() {
    val dataSource = InMemoryTransactionsDataSource()
    val transactionManager = TransactionManager(dataSource)
    val validator = Validator()
    val cli = CommandLineInterface(transactionManager, validator)

    cli.start()
}