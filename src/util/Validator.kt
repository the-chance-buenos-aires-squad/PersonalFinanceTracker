package util

import java.util.*

class Validator {
    fun notOutOfRang(n:Int, rang:Int):Boolean{
        TODO("n should be in rang")
    }

    fun monthIsValid(month:Int):Boolean{
        TODO("should be in the rang 1..12")
    }

    fun yearIsValid(year:Int):Boolean{
        TODO(" not after 2025")
    }

    fun amountIsValid(amount:String):Boolean{
        TODO("the amount should be")
    }
}
fun String.toUUIDOrNull(): UUID? = runCatching { UUID.fromString(this) }.getOrNull()
