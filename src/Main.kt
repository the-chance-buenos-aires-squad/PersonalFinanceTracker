import cli.CommandLineInterface
import data_source.InMemoryTransactionsDataSource
import repository.TransactionManager

fun main() {
    val dataSource = InMemoryTransactionsDataSource()
    val transactionManager = TransactionManager(dataSource)
    val cli = CommandLineInterface(transactionManager)

    cli.start()
}