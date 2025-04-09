package model

import domain.model.Category

data class MonthlySummary(
    val month : Int,
    val year : Int,
    //val categories : List<Category>,
    val totalIncome : Double,
    val totalExpense : Double,
    val balance : Double,
    )

