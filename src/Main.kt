import cli.CommandLineInterface
import data_source.InMemoryTransactionsDataSource
import Manager.ReportManager
import Manager.TransactionManager
import util.Validator

fun main() {
    val dataSource = InMemoryTransactionsDataSource()
    val transactionManager = TransactionManager(dataSource)
    val reportManager = ReportManager(dataSource)
    val validator = Validator()
    val cli = CommandLineInterface(transactionManager, reportManager, validator)

    cli.start()
}