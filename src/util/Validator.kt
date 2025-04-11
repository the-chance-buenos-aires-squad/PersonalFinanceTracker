package util

import java.util.*

class Validator {
    fun notOutOfRang(n:Int, rang:Int):Boolean{
       return (n in 1..rang)
    }

    fun monthIsValid(month:Int):Boolean{
        return (month in 1..12)
    }

    fun yearIsValid(year:Int):Boolean{
        return (year < 2026)
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
