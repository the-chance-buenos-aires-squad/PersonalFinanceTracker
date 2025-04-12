package model

enum class TransactionCategory(val id: Int) {
    FOOD(1),
    RENT(2),
    SALARY(3),
    ENTERTAINMENT(4),
    UTILITIES(5),
    TRANSPORT(6),
    OTHER(7)
}
