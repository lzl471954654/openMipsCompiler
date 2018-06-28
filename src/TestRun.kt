import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val file = File("test.s")
    val scanner = Scanner(file.inputStream())
    val temp = scanner.nextLine()
    temp.split(" ").forEach {
        println(it)
    }
}