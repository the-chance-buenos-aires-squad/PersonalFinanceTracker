package checks

import model.Transaction

fun main() {

}

fun <T> check(
    message:String,
    result : T,
    correctResult : T
){
    println(message)
    if (result == correctResult) println("successful") else println("fiald")
    println("--------")
}