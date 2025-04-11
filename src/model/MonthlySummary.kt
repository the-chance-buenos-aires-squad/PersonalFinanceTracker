package model

data class MonthlySummary(
    val totalIncome: Double,
    val totalExpense: Double,
    val incomeList: List<Transaction>,
    val expenseList: List<Transaction>?,
    val highestIncomeCategory: TopCategory?,
    val highestExpenseCategory: TopCategory?,
)
