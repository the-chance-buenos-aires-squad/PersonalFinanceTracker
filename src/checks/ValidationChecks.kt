package checks

import util.Validator

fun main() {
    validationChecks()
}

fun validationChecks() {
    val validator = Validator()
    check(
        message = "checking n 1 in the rang:5, return true",
        result = validator.isInRange(n= 1, rang = 5),
        correctResult = true
    )
    check(
        message = "checking n 6 is out of the rang:5, return false",
        result = validator.isInRange(n= 6, rang = 5),
        correctResult = false
    )
    check(
        message = "checking month 3 is valid, return true",
        result = validator.isValidMonth(month = 6),
        correctResult = true
    )
    check(
        message = "checking month 13 is not valid, return false",
        result = validator.isValidMonth(month = 13),
        correctResult = false
    )
    check(
        message = "checking year 2025 3 is valid, return true",
        result = validator.isValidYear(2025),
        correctResult = true
    )
    check(
        message = "checking year 2030 3 is not valid, return false",
        result = validator.isValidYear(2030),
        correctResult = false
    )
    check(
        message = "checking amount 13.0 is valid, return true",
        result = validator.isValidAmount("13.0"),
        correctResult = true
    )
    check(
        message = "checking amount 12xs is not valid, return false",
        result = validator.isValidAmount("12xs"),
        correctResult = false
    )
}

