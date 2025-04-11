package checks

import util.Validator

fun validationChecks(){
val validator = Validator()
//  notOutOfRang checks
    check(
        message = "checking n 1 in the rang:5, return true",
        result = validator.notOutOfRang(n= 1, rang = 5),
        correctResult = true
    )
    check(
        message = "checking n 6 is out of the rang:5, return false",
        result = validator.notOutOfRang(n= 6, rang = 5),
        correctResult = false
    )

//    fun monthIsValid checks
    check(
        message = "checking month 3 is valid, return true",
        result = validator.monthIsValid(month = 6),
        correctResult = true
    )

    check(
        message = "checking month 13 is not valid, return false",
        result = validator.monthIsValid(month = 13),
        correctResult = false
    )


//    fun yearIsValid checks
    check(
        message = "checking year 2025 3 is valid, return true",
        result = validator.yearIsValid(2025),
        correctResult = true
    )

    check(
        message = "checking year 2030 3 is not valid, return false",
        result = validator.yearIsValid(2030),
        correctResult = false
    )



//    fun amountIsValid checks
    check(
        message = "checking amount 13.0 is valid, return true",
        result = validator.amountIsValid("13.0"),
        correctResult = true
    )

    check(
        message = "checking amount 12xs is not valid, return false",
        result = validator.amountIsValid("12xs"),
        correctResult = false
    )

}

