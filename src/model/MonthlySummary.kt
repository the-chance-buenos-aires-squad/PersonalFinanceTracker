package model

import domain.model.Category

data class MonthlySummary(
    val month : Int,
    val year : Int,
    val totalIncome : Double,
    val totalExpense : Double,
    val balance : Double,
    )

