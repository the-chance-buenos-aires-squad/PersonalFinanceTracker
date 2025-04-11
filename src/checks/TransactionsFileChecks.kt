package checks

import data_source.TransactionsFile

fun main() {
    val transactionsFile = TransactionsFile()

    check(
        "When append to exist file",
        result = transactionsFile.appendToFile("data"),
        correctResult = true
    )

    check(
        "When append to exist file with empty string",
        result = transactionsFile.appendToFile(""),
        correctResult = true
    )
}



