package checks


fun main() {

}

fun  check(
    message:String,
    result : Boolean,
    correctResult : Boolean
){
    println(message)
    if (result == correctResult) println("successful") else println("fiald")
    println("--------")
}