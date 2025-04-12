package util

import model.Transaction

fun List<Transaction>.displayOnScreen() {
    println("No | Date | Amount | Type | Category")
    println("-------------------------------------------------------------")
    forEach { transaction ->
        println("${indexOf(transaction) + 1} |${transaction.date} |${transaction.amount} |${transaction.type.name.lowercase()} |${(transaction.transactionCategory.name).lowercase()} ")
    }
}

fun printSectionHeader(title: String) {
    println("===== $title =====")
}