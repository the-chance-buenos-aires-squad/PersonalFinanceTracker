package data_source

import java.io.File

const val FILE_NAME = "transactions_file.txt"

class TransactionsFile {

    private var file: File = File(FILE_NAME)

    fun appendToFile(data: String): Boolean {
        try {
            file.appendText(data + "\n")
            return true
        }
        catch (e: Exception) {
            return false
        }
    }

    fun writeToFile(data: String): Boolean {
        try {
            file.writeText(data)
            return true
        }
        catch (e: Exception) {
            file.createNewFile()
            return false
        }
    }
}