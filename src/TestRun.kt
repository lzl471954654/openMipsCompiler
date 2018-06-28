import java.io.File
import java.util.*

val map = hashMapOf(
        "123" to 6
)

fun main(args: Array<String>) {

    val oct = "+55111"
    println(oct.substring(1 until oct.length))
    println(oct.isOct())

    val spaceDemo = "    abc"
    println(spaceDemo.trimStart(' '))

    val hex = "0xff"
    println(Integer.parseInt(hex.toLowerCase().replace("0x",""),16))

    val bin = "11101100100000100000000000000000"
    println(bin.toLong(2))
    val l = bin.toLong(2)
    println(l.toInt().toString(2))
    println(bin.toLong().toString(2).toInt(2))
    println(bin.toInt(2))
    println(bin.toInt(2).toString(2).toInt(2))


    val file = File("test.s")
    val scanner = Scanner(file.inputStream())
    val temp = scanner.nextLine()
    println(temp)
    scanner.reset()
    println(scanner.nextLine())
    temp.split(" ").forEach {
        println(it)
    }

    val temp1 = "$3"
    val value = temp1.substring(1 until temp1.length)
    println(value)
    println( 0x03.toByte() == value.toByte() )


    val arg = "$8"
    val number = arg.substring(1 until arg.length).toInt()
    val byteNumber = number.toString(2)
    println(byteNumber)
    val builder = StringBuilder()
    var i = byteNumber.length
    while (i<6){
        builder.append('0')
        i++
    }
    builder.append(byteNumber)
    println(builder.toString())
}
