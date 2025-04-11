package util

import java.time.Year
import java.util.*

class Validator {
    fun notOutOfRang(n:Int, rang:Int):Boolean{
       return (n in 1..rang)
    }

    fun monthIsValid(month:Int):Boolean{
        return (month in 1..12)
    }

    fun yearIsValid(year:Int):Boolean{
        val currentYear = Year.now().value
        return year != null && year in 1900..currentYear
    }

    fun amountIsValid(amount:String): Boolean {
        val number = amount.toDoubleOrNull()
        return number != null && number >= 0
    }
    fun isValidUUID(input: String): UUID? {
        return input.toUUIDOrNull()
    }

    fun transactionExists(id: UUID?, fetcher: (UUID) -> Boolean): Boolean {
        return id != null && fetcher(id)
    }
}
fun String.toUUIDOrNull(): UUID? = runCatching { UUID.fromString(this) }.getOrNull()
