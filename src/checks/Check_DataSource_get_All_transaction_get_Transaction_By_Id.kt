package checks

import model.Transaction
import model.TransactionType
import java.time.LocalDate
import java.util.UUID

fun main() {
    checkGetByIdReturnsCorrectTransaction()
    checkGetByIdReturnsNullIfNotFound()
    checkGetAllReturnsCorrectTransaction()
    checkGetAllreturnsEmptyListIfNoTransaction()


}
// لما ابحث عن ID موجود يرجعلي Transaction صح
fun checkGetByIdReturnsCorrectTransaction() {
   val transactionList = mutableListOf<Transaction>()
    val tx = Transaction( id = 1,amount = 100.0, category = "Food", type = TransactionType.EXPENSE, date = LocalDate.now())
    transactionList.add(tx)
    val result = transactionList.find { it.id == 1}
    check ("Should return transaction with ID 1",result?.id==1,true)
 }
//لما ابحث عن ID غير موجود يطلعلي Null
fun  checkGetByIdReturnsNullIfNotFound() {
    val transactionList = mutableListOf<Transaction>()
    val tx = Transaction( id = UUID.randomUUID(),amount = 100.0,category = "Food", type = TransactionType.EXPENSE,   date = LocalDate.now())
    transactionList.add(tx)
    val result = transactionList.find {it.id.toString()=="99"}
    check("Should return null if transaction not found", result==null,true)
}
// يرجع كل المعاملات المضافة
fun checkGetAllReturnsCorrectTransaction() {
    val transactionList = mutableListOf<Transaction>()
    val tx1 = Transaction(id= 80,amount = 100.0,category = "Food", type = TransactionType.EXPENSE,date = LocalDate.now())
    val tx2 = Transaction(id = 90, amount = 200.0, category = "Food", type = TransactionType.INCOME,date = LocalDate.now())
    transactionList.add(tx1)
    transactionList.add(tx2)
    val result = transactionList.getAll()
    check( "Should return list of size 2",result.size ==2,true)
}
// اذا ماكو معاملات يرجع كل القيم فارغة
fun checkGetAllreturnsEmptyListIfNoTransaction() {
    val transactionList = mutableListOf<Transaction>()
    val result = transactionList.getAll()
    check( "Should return empty list when no tansaction", transactionList.isEmpty(),true)
}

